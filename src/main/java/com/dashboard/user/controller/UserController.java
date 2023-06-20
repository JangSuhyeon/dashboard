package com.dashboard.user.controller;

import com.dashboard.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@AllArgsConstructor
@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<String> loginSuccess(@RequestBody Map<String, String> loginForm){
        String token = userService.login(loginForm.get("username"), loginForm.get("password"));
        return ResponseEntity.ok(token);
    }

}
