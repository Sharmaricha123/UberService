package com.project.uber.controllers;

import com.project.uber.dto.DriverDto;
import com.project.uber.dto.OnboardDriverDto;
import com.project.uber.dto.SignupDto;
import com.project.uber.dto.UserDto;
import com.project.uber.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto)
    {
        return new ResponseEntity<>(authService.signUp(signupDto), HttpStatus.CREATED);
    }

    @PostMapping("/onboardNewDriver/{userId}")
    ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId, @RequestBody OnboardDriverDto onboardDriverDto)
    {
        return  new ResponseEntity<>(authService.onboardNewDriver(userId,onboardDriverDto.getVehicleId()),HttpStatus.CREATED);
    }

}
