package com.orderout.orderout.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.orderout.orderout.constants.Constants;
import com.orderout.orderout.constants.Template;
import com.orderout.orderout.dao.UserDao;
import com.orderout.orderout.domain.ConfirmationToken;
import com.orderout.orderout.domain.EmailDto;
import com.orderout.orderout.domain.User;
import com.orderout.orderout.domain.UserDto;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	SendGridService sendGridService;

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(String id) {
		userDao.deleteById(id);
	}

	@Override
	public User findByEmail(String username) {
		return userDao.findByEmail(username);
	}

	@Override
	public User findByStatus(String username, boolean active) {
		return userDao.findByStatus(username, active);
	}

//	@Override
//	public User findInactiveByEmail(String email) {
//		return userDao.findInactiveByEmail(email);
//	}

//	@Override
//	public User findById(String id) {
//		Optional<User> optionalUser = userDao.findById(id);
//		return optionalUser.isPresent() ? optionalUser.get() : null;
//	}

	@Override
	public User update(User userDto) {
		User user = findByEmail(userDto.getEmail());
		if (user != null) {
			BeanUtils.copyProperties(userDto, user, "password");
			userDao.save(user);
		}
		return userDto;
	}

	@Override
	public void activate(String email) {
		User user = findByStatus(email, Constants.INACTIVE); // find inactive email
		if (user != null) {
			user.setActive(Constants.ACTIVE);
			userDao.save(user);

		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}

	@Value("${domain.url}")
	private String domainUrl;

	@Override
	public User save(UserDto user) {
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEmail(user.getEmail().toLowerCase());
		newUser.setPhoneNumber(user.getPhoneNumber());
		userDao.save(newUser);

		ConfirmationToken confirmationToken = new ConfirmationToken(newUser);
		confirmationTokenRepository.save(confirmationToken);

		try {

			EmailDto emailDto = new EmailDto();
			emailDto.setEmailSubject(Template.SUBJECT_ACTIVATION_MESSAGE);
			emailDto.setFromEmail("userspsdev2@gmail.com");
			emailDto.setFromName("Order Out");

			emailDto.setToEmail(user.getEmail());
			emailDto.setMessage(Template.ACTIVATION_MESSAGE + domainUrl + " Please click on below link : \n"+
					domainUrl+ "/activate_user" + "?token="
					+ confirmationToken.getConfirmationToken());
			sendGridService.sendMail(emailDto);

		} catch (Exception e) {
			System.err.println(" ########### SEND EMAIL FAILD ###########");
			e.printStackTrace();
		}

		return newUser;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email.toLowerCase());
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthority());

	}

	public void sendVerification(String email) {
		User user = new User();
		user.setEmail(email);
		ConfirmationToken confirmationToken = new ConfirmationToken(user);
		confirmationTokenRepository.save(confirmationToken);
		try {

			EmailDto emailDto = new EmailDto();
			emailDto.setEmailSubject(Template.RESET_PASSWORD);
			emailDto.setFromEmail("userspsdev2@gmail.com");
			emailDto.setFromName("Order Out");

			emailDto.setToEmail(user.getEmail());
			emailDto.setMessage(Template.RESET_PASSWORD_MASSAGE + " Please click on below link : \n"+
					domainUrl+ "/forget-password?token="
					+ confirmationToken.getConfirmationToken());

			sendGridService.sendMail(emailDto);

		} catch (Exception e) {
			System.err.println(" ########### SEND EMAIL FAILD ###########3");
			e.printStackTrace();
		}
		System.out.println(">> >> >> >> Send Verfication ");

	}

}
