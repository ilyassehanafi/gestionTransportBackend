package com.example.demo.service;

import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service()
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserModel findUserByName(String userName){
        return userRepository.findByUserName(userName);
    }


    public void saveUser(UserModel userModel){
        userRepository.save(userModel);
    }

    public UserModel logUser(UserModel userModel){
        UserModel foundedUserModel = userRepository.findByUserName(userModel.getUserName());
        if (foundedUserModel == null){
            return null;
        }
        else{
            return foundedUserModel;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel foundedUserModel = userRepository.findByUserName(username);
        if (foundedUserModel == null) return null;
        String name = foundedUserModel.getUserName();
        String password = foundedUserModel.getPassword();
        return new User(name,password,new ArrayList<>());

    }
}
