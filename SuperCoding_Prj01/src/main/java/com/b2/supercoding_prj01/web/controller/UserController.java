package com.b2.supercoding_prj01.web.controller;

import com.b2.supercoding_prj01.dto.UserRequestDto;

import com.b2.supercoding_prj01.repository.UserRepository;
import com.b2.supercoding_prj01.service.JwtService;
import com.b2.supercoding_prj01.service.UserService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.web.bind.annotation.*;


import javax.print.DocFlavor;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RedisTemplate<String , String > redisTemplate;

    //    회원가입 - signup
    @PostMapping("/signup")
    public String register(@RequestBody UserRequestDto userDto) {
        return userService.signUp(userDto);
    }
    @PostMapping(value = "/login")
    public String login(@RequestBody UserRequestDto loginRequest, HttpServletResponse httpServletResponse){
        String token = userService.login(loginRequest);
        redisTemplate.opsForValue().set(loginRequest.getEmail(), token);
        httpServletResponse.setHeader("X-AUTH-TOKEN", token);
        String key = "JWT Token : " + loginRequest.getEmail();
        String value = redisTemplate.opsForValue().get(key);
        return "로그인 완료";
    }

    @GetMapping("/test")
    public boolean test(@RequestBody UserRequestDto userRequestDto){
        return userService.test(userRequestDto);
    }


    @PostMapping("/logout")
    public String  logout(@RequestBody UserRequestDto userRequestDto,HttpServletResponse httpServletResponse){
        userService.logout(userRequestDto);
        return "로그아웃 완료";
    }

    @GetMapping("/validate")
    public void validate(){
        System.out.println("유효한 토~큰~");
    }

}
