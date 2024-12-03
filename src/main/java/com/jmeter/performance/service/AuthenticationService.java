package com.jmeter.performance.service;

import com.jmeter.performance.entity.UserDetails;
import com.jmeter.performance.repo.AuthenticationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private final AuthenticationRepo authenticationRepo;

    public AuthenticationService(AuthenticationRepo authenticationRepo) {
        this.authenticationRepo = authenticationRepo;
    }

    public UserDetails authenticate(String username, String password) {
        try {
            List<UserDetails> userDetails = authenticationRepo.findByUserName(username);
            if(userDetails!=null && !userDetails.isEmpty()){
                UserDetails userDetail = userDetails.get(0);
                return userDetail.getUserName().equals(username) && userDetail.getPassword().equals(password)? userDetail: null;
            }
        }catch (Exception e){
            throw new RuntimeException("Invalid username or password");
        }
        return null;
    }

    public UserDetails addUser(String username, String password) {
        return authenticationRepo.save(new UserDetails(username,password,true));
    }
}
