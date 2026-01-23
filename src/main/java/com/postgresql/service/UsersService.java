package com.postgresql.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.postgresql.repository.UsersRepository;
import com.postgresql.entity.UsersEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository userRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UsersEntity users = usersRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
                        
        System.out.println("pass : " + new BCryptPasswordEncoder().encode("password"));
        System.out.println("pass2 : "+ passwordEncoder.matches("password", users.password));

        return org.springframework.security.core.userdetails.User
                .withUsername(users.username)
                //.password(users.password)
                .password(passwordEncoder.encode(users.password))
                .disabled(!users.enabled)
                .roles("USER")
                .build();
    }
}
