package org.example.todolistsecurity.controller;

import org.example.todolistsecurity.dto.BaseResponseDto;
import org.example.todolistsecurity.dto.UserLoginDto;
import org.example.todolistsecurity.model.User;
import org.example.todolistsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public BaseResponseDto registerUser(@RequestBody User user) {
        if (userService.createUser(user)) {
            return new BaseResponseDto("Success");
        } else {
            return new BaseResponseDto("Failed");
        }
    }

    @PostMapping("/login")
    public BaseResponseDto loginUser(@RequestBody UserLoginDto userLoginDto) {

        if (userService.checkUserNameExists(userLoginDto.getEmail())) {

            if (userService.verifyUser(userLoginDto.getEmail(), userLoginDto.getPassword())) {

                Map<String, Object> data = new HashMap<>();

                data.put("token", userService.generateToken(userLoginDto.getEmail(), userLoginDto.getPassword()));

                return new BaseResponseDto("Success", data);

            } else {
                return new BaseResponseDto("Wrong password");
            }

        } else {
            return new BaseResponseDto("User Not Exist");
        }


    }
}
