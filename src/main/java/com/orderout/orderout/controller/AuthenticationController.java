package com.orderout.orderout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orderout.orderout.config.JwtTokenUtil;
import com.orderout.orderout.domain.ApiResponse;
import com.orderout.orderout.domain.AuthToken;
import com.orderout.orderout.domain.LoginUser;
import com.orderout.orderout.domain.User;
import com.orderout.orderout.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

	private static final String ACTIVE = "1";

	@Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	    @Autowired
	    private UserService userService;

	    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
	    public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
	        
	        final User user = userService.findByEmail(loginUser.getEmail(), ACTIVE);
	        final String token = jwtTokenUtil.generateToken(user);
	        return new ApiResponse<>(200, "success",new AuthToken(token, user.getEmail()));
	    }

}
