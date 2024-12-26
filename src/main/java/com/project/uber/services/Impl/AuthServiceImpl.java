package com.project.uber.services.Impl;

import com.project.uber.dto.DriverDto;
import com.project.uber.dto.SignupDto;
import com.project.uber.dto.UserDto;
import com.project.uber.entities.Driver;
import com.project.uber.entities.Rider;
import com.project.uber.entities.User;
import com.project.uber.entities.enums.Role;
import com.project.uber.exceptions.ResourceNotFoundException;
import com.project.uber.exceptions.RuntimeConflictException;
import com.project.uber.repositories.UserRepository;
import com.project.uber.services.AuthService;
import com.project.uber.services.DriverService;
import com.project.uber.services.RiderService;
import com.project.uber.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.project.uber.entities.enums.Role.DRIVER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;

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
    public DriverDto onboardNewDriver(Long userId,String vehicleId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id: "+userId));
        if(user.getRoles().contains(DRIVER))
        {
            throw new RuntimeException("User with id "+userId+" is already a driver");
        }
        Driver createDriver=Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();

        user.getRoles().add(DRIVER);
        userRepository.save(user);
        Driver savedDriver=driverService.createNewDriver(createDriver);
        return modelMapper.map(savedDriver,DriverDto.class);
    }
}
