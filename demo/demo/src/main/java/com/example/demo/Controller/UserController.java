package com.example.demo.Controller;

import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping(method = RequestMethod.POST, value = "/auth")
    private String loginIn(@RequestBody UserModel userModel) {
        System.out.println(userModel);
        UserModel loggedUserModel = userService.logUser(userModel);
        if (loggedUserModel == null) {
            return "null";
        } else {
            return loggedUserModel.toString();
        }
    }


    @PostMapping("/subscribe")
    private ResponseEntity<AuthenticationResponse> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest) {
        String userName = authenticationRequest.getUserName();
        String password = authenticationRequest.getPassword();
        UserModel userModel = new UserModel(userName, password);
        try {
            userService.saveUser(userModel);
        } catch (Exception e) {
            return ResponseEntity.ok(new AuthenticationResponse("Error durring sign up subcription for the user " + userName));
        }
        return ResponseEntity.ok(new AuthenticationResponse("Succesul sign up for the new user " + userName));
    }

    @PostMapping("/authenticate")
    private ResponseEntity<AuthenticationResponse> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest) {

        String userName = authenticationRequest.getUserName();
        String password = authenticationRequest.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (Exception e) {
            return new ResponseEntity("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        }
        UserDetails loadedUser = userService.loadUserByUsername(userName);
        String token = jwtUtils.generateToken(loadedUser);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

}
