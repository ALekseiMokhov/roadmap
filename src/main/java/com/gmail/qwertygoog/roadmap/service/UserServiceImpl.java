package com.gmail.qwertygoog.roadmap.service;

import com.gmail.qwertygoog.roadmap.model.User;
import com.gmail.qwertygoog.roadmap.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements ReactiveUserDetailsService {

    private final UserRepo userRepo;
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepo.findByUsername(username).cast(UserDetails.class);
    }

    public Mono<User> save(User user){
        if(userRepo.findByUsername(user.getUsername())!=null){
            throw new RuntimeException("User with username : " +user.getUsername() + " already exists!");
        }
        return userRepo.save(user);
    }


}
