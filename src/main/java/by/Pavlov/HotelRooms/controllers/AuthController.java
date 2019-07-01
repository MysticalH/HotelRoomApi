package by.Pavlov.HotelRooms.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;


import by.Pavlov.HotelRooms.configures.JwtTokenProvider;
import by.Pavlov.HotelRooms.models.AuthBody;

import by.Pavlov.HotelRooms.models.User;
import by.Pavlov.HotelRooms.repositories.RoleRepository;
import by.Pavlov.HotelRooms.repositories.UserRepository;
import by.Pavlov.HotelRooms.service.CustomUserDetailsService;
import by.Pavlov.HotelRooms.service.other.EmailValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RequestWrapper;


@RestController
@RequestMapping("/api/auth")
public class AuthController {



    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CustomUserDetailsService userService;

    @SuppressWarnings("rawtypes")
    /**
     * Login user
     * use Post mapping
     * returns token
     */
    @PostMapping("/login")
    @RequestWrapper
    public ResponseEntity login(@RequestBody AuthBody data) {
        try {
            String username = data.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            User user = userRepository.findByEmail(username).orElseThrow(() -> new BadCredentialsException(username));
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);

            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
    /**
     * Register user
     * Post request body
     * default User role "USER"
     * if success returns
     */
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {


        User userExists = userService.findUserByEmail(user.getEmail()).orElse(new User());
        if (userExists.getEmail() != null) {
            throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
        }
        if (!EmailValidation.isEmailValid(user.getEmail())) {
            throw new BadCredentialsException("Incorrect Email address " + user.getEmail() + " already exists");
        }

        userService.saveUser(user);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");

        return ok(model);
    }
}
