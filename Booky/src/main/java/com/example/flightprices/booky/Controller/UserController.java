package com.example.flightprices.booky.Controller;

import com.example.flightprices.booky.Model.User;
import com.example.flightprices.booky.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.status(200).body("register successful");
    }
}
