package com.springboot.blog.app.service;

import com.springboot.blog.app.payload.LoginDto;
import com.springboot.blog.app.payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
