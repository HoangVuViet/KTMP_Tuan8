package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.Services.UserService;
import vn.edu.iuh.fit.dtos.CreateUserRequest;
import vn.edu.iuh.fit.dtos.LoginRequest;
import vn.edu.iuh.fit.dtos.UserDto;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        boolean success = userService.login(request);
        return success ? ResponseEntity.ok("Login success") : ResponseEntity.status(401).body("Invalid credentials");
    }
}
