package com.example.flightprices.booky.Service;

import com.example.flightprices.booky.Api.ApiException;
import com.example.flightprices.booky.Model.User;
import com.example.flightprices.booky.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void registerUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }

    public User getUserInfo(User user){
        User isUser=userRepository.findUserByEmail(user.getEmail());
        if(isUser==null){
            throw new ApiException("User not found");
        }
            return isUser;
    }
}
