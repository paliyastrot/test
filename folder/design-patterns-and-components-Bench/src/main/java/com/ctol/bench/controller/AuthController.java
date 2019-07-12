package com.ctol.bench.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctol.bench.exception.AuthenticationException;
import com.ctol.bench.model.User;
import com.ctol.bench.payload.AuthResponse;
import com.ctol.bench.payload.JWTRequest;
import com.ctol.bench.payload.JWTResponse;
import com.ctol.bench.service.UserService;
import com.ctol.bench.utils.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTUtil jwtTokenUtil;
	@Autowired
	private UserService userService;

//	@PostMapping("/login")
//	public AuthResponse loginUser(@Valid @RequestBody User user) throws AuthenticationException {
//		User authenticatedUser = userService.authenticate(user);
//		return new AuthResponse(authenticatedUser, "Success!");
//	}
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JWTResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PostMapping("/signup")
	public AuthResponse registerUser(@Valid @RequestBody User user) throws AuthenticationException {
		User createdUser = userService.create(user);
		return new AuthResponse(createdUser, "Success!");
	}
}
