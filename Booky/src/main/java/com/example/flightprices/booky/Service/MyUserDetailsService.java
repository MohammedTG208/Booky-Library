package com.example.flightprices.booky.Service;

import com.example.flightprices.booky.Api.ApiException;
import com.example.flightprices.booky.Model.User;
import com.example.flightprices.booky.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findUserByEmail(username);

        if (user==null){
            throw new ApiException("Email or password is incorrect");
        }

        return user;
    }
}
