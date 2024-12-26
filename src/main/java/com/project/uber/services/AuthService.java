package com.project.uber.services;

import com.project.uber.dto.DriverDto;
import com.project.uber.dto.SignupDto;
import com.project.uber.dto.UserDto;

public interface AuthService {

    String login(String email,String password);

    UserDto signUp(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId,String vehicleId);


}
