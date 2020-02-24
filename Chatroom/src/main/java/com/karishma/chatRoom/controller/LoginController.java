package com.karishma.chatRoom.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.karishma.chatRoom.model.Credentials;
import com.karishma.chatRoom.model.Token;
import com.karishma.chatRoom.model.User;
import com.karishma.chatRoom.repository.TokenRepository;
import com.karishma.chatRoom.util.JwtTokenUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenUtil jwtTokenutil;
	
	@Autowired
	TokenRepository tokenrepository;

	@GetMapping("/home")
	public String helloworld(@RequestHeader(value="Authorization") String token) {
		String jwtToken = token.substring(7);
		
		Token tokenObj = tokenrepository.findByJwttoken(jwtToken);
		
		tokenrepository.deleteById(tokenObj.getTokenId());
		return "Thank you! You are now logged out.";
	}

	
	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@Valid @RequestBody Credentials authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());


		UserDetails user = userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getUsername());


		String token = jwtTokenutil.generateToken(user);

		Token jwttoken = new Token();
		jwttoken.setJwttoken(token);
		tokenrepository.save(jwttoken);
		
		return ResponseEntity.ok(token);

	}

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {

		return new ResponseEntity<User>(userDetailsServiceImpl.save(user), new HttpHeaders(), HttpStatus.OK);
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
}
