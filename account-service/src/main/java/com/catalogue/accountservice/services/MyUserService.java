package com.catalogue.accountservice.services;
import com.catalogue.accountservice.repositories.MyUserRepository;
import com.catalogue.accountservice.domain.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserService {
    private MyUserRepository repository;
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public MyUserService(MyUserRepository repository){
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.repository  = repository;
    }

    public MyUser loadUserByUsername(String username){
        return repository.findMyUserByUsername(username);
    }
    public MyUser saveMyUser(MyUser user){
        //need to add some checks here to check if the user already exists!!
        return repository.save(user);
    }
}
