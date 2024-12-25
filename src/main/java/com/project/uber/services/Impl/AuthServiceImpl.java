package com.project.uber.services.Impl;

import com.project.uber.dto.DriverDto;
import com.project.uber.dto.SignupDto;
import com.project.uber.dto.UserDto;
import com.project.uber.entities.Rider;
import com.project.uber.entities.User;
import com.project.uber.entities.enums.Role;
import com.project.uber.exceptions.RuntimeConflictException;
import com.project.uber.repositories.UserRepository;
import com.project.uber.services.AuthService;
import com.project.uber.services.RiderService;
import com.project.uber.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signUp(SignupDto signupDto) {
       User user= userRepository.findByEmail(signupDto.getEmail()).orElse(null);
       if(user!=null)
       {
           throw new RuntimeConflictException("Cannot signup , User already exists with email "+signupDto.getEmail());
       }
//        if(user!=null)
//        {
//            throw  new RuntimeException("Cannot signup , User already exists with email "+signupDto.getEmail());
//        }
        User mappedUser=modelMapper.map(signupDto,User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser=userRepository.save(mappedUser);

//        create user related entities
        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(String userId) {
        return null;
    }
}
