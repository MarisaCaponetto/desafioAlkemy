package com.alkemy.icons.icons.auth.controller;

import com.alkemy.icons.icons.auth.dto.UserDTO;
import com.alkemy.icons.icons.auth.service.AuthenticationRequest;
import com.alkemy.icons.icons.auth.service.AuthenticationResponse;
import com.alkemy.icons.icons.auth.service.JwtUtils;
import com.alkemy.icons.icons.auth.service.UserDetailsCustomService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserAuthController {
    
    
    UserDetailsCustomService userDetailsService; 
       
    AuthenticationManager authenticationManager;    
    
    JwtUtils jwtTokenUtil;
    
    @Autowired
    public UserAuthController(
            UserDetailsCustomService userDetailsService, 
            AuthenticationManager authenticationManager, 
            JwtUtils jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }
    
    
    
  @PostMapping("/singup")
  public ResponseEntity<AuthenticationResponse> singUp(@Valid @RequestBody UserDTO user) throws Exception{
      this.userDetailsService.save(user);
      return ResponseEntity.status(HttpStatus.CREATED).build();
  }
  
  @PostMapping("/singin")
  public ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authRequest) throws Exception{
      UserDetails userDetails = null;
      try{
          Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getUsername()));
      }catch(BadCredentialsException e){
          throw new Exception("Incorrect username or password", e);
      }
      
      final String jwt = jwtTokenUtil.generateToken(userDetails);
      
      return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
