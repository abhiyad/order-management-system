package com.catalogue.accountservice.services;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.catalogue.accountservice.domain.MyUser;

import javax.transaction.Transactional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = new MyUser();
        try {
            user = myUserService.loadUserByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public MyUser save(MyUser user) {
        MyUser newUser = new MyUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return myUserService.saveMyUser(newUser);
    }

}