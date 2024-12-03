package com.jmeter.performance.controller;

import com.jmeter.performance.dto.LoginRequest;
import com.jmeter.performance.dto.LoginResponse;
import com.jmeter.performance.entity.UserDetails;
import com.jmeter.performance.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println(loginRequest);
            UserDetails userDetails = authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            if (userDetails != null && userDetails.isAccess()) {
                return ResponseEntity.ok(new LoginResponse("Login success"));
            } else {
                return ResponseEntity.status(401).body(new LoginResponse("Login failed"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body(new LoginResponse("Invalid username or password"));
        }
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetails userDetails = authenticationService.addUser(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(String.valueOf(userDetails.isAccess()));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @GetMapping("/version")
    public ResponseEntity<String> getVersion() {
        return new ResponseEntity<>("0.0.2", HttpStatus.OK);
    }


    @GetMapping("/mock-delayed-service")
    public ResponseEntity<String> delayedResponse() throws InterruptedException {
        Thread.sleep(35000);
        return new ResponseEntity<>("Delayed response received!", HttpStatus.OK);
    }
}
