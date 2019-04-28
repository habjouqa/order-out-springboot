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
import com.orderout.orderout.constants.Constants;
import com.orderout.orderout.domain.User;
import com.orderout.orderout.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

	@Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	    @Autowired
	    private UserService userService;

	    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
	    public ApiResponse<AuthToken> register(@RequestBody User loginUser) throws AuthenticationException {
	    	try {
	    		 final User user = userService.findByStatus(loginUser.getEmail().toLowerCase(), Constants.ACTIVE);
	    		 if(user!=null) {
	 	        final String token = jwtTokenUtil.generateToken(user);
	 	       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail().toLowerCase(), loginUser.getPassword()));
	 	      return new ApiResponse<>(200, "success",new AuthToken(token, user));
	    		 }else {
	    			 return new ApiResponse<>(500, "Faild",null);
	    		 }
		     
		       
	    	}catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
	    	return new ApiResponse<>(500, "Faild",null);
	    }

}
