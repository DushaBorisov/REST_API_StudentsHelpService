package com.example.andrey.studentshelp.demo.Security;

import com.example.andrey.studentshelp.demo.Services.UserService;
import com.example.andrey.studentshelp.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService){
        this.userService = userService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.GetUserByLogin(s);
        return  CustomUserDetails.fromUserToCustomUserDetails(user);
    }


}
