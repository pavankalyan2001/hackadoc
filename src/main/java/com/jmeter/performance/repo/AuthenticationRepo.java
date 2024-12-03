package com.jmeter.performance.repo;

import com.jmeter.performance.entity.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuthenticationRepo extends MongoRepository<UserDetails, String>{

    List<UserDetails> findByUserName(String userName);
}
