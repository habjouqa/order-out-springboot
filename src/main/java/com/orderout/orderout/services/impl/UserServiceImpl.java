package com.orderout.orderout.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.orderout.orderout.dao.UserDao;
import com.orderout.orderout.domain.User;
import com.orderout.orderout.domain.UserDto;
import com.orderout.orderout.services.MailService;
import com.orderout.orderout.services.UserService;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	private static final String INACTIVE = "0";
	private static final String ACTIVE = "1";

	@Autowired
	private UserDao userDao;
	@Autowired
	private MailService mail;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

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
	public User findByEmail(String username, String active) {
		return userDao.findByEmail(username, active);
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
    public UserDto update(UserDto userDto) {
        User user = findByEmail(userDto.getEmail(), ACTIVE);
        if(user != null) {
            BeanUtils.copyProperties(userDto, user, "password");
            userDao.save(user);
        }
        return userDto;
    }

    @Override
    public void activate(String email) {
        User user = findByEmail(email, INACTIVE);
    	System.out.println(" >>>**************** <<< " + INACTIVE + "[" + email + "]");

        if(user != null) {
        	user.setActive(ACTIVE);
//            BeanUtils.copyProperties(userDto, user, "password");
        	System.out.println(" >>>>>>>>>>>>>>>>> <<<<<<<<<<<<<<<<<<<<<< " + user.getEmail());
            userDao.save(user);
        }
        else {
			throw new UsernameNotFoundException("User not found.");
        }
//        return userDto;
    }

    @Override
    public User save(UserDto user) {
    	
 
	    User newUser = new User();
	    newUser.setFirstName(user.getFirstName());
	    newUser.setLastName(user.getLastName());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setAge(user.getAge());
		newUser.setSalary(user.getSalary());
		newUser.setEmail(user.getEmail());
		newUser.setPhoneNumber(user.getPhoneNumber());
		try {
			
			Email from = new Email("test@example.com");
		    String subject = "Hello World from the SendGrid Java Library!";
		    Email to = new Email("anashijazi1990@gmail.com");
		    Content content = new Content("text/plain", "Hello, Email!");
		    Mail mail = new Mail(from, subject, to, content);

		    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		    Request request = new Request();
		    try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		      System.out.println(response.getStatusCode());
		      System.out.println(response.getBody());
		      System.out.println(response.getHeaders());
		    } catch (IOException ex) {
		      throw ex;
		    }
		
		}catch (Exception e) {
			System.err.println(" ########### SEND EMAIL FAILD ###########3");
			e.printStackTrace();
		}
		
		return userDao.save(newUser);
    }

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email.toLowerCase(), ACTIVE);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority());

	}

}
