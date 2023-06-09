package com.fsb.chatapplication.Services;

import com.fsb.chatapplication.Models.User;
import com.fsb.chatapplication.Repositories.UserRepository;
import com.fsb.chatapplication.exceptions.UserAlreadyExistException;
import com.fsb.chatapplication.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UseServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getall() throws UserNotFoundException {
        List<User> users=userRepository.findAll();
        if (users.isEmpty()){
            throw new UserNotFoundException();
        }else {
            return users;
        }
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistException {
        Optional<User> user1 = userRepository.findByUserName(user.getUserName());
        System.out.println(user.getUserName());
        if (user1.isPresent()){
            throw new UserAlreadyExistException();
        }else {
            return userRepository.save(user);
        }    }

    @Override
    public User getUserByUserName(String username) throws UserNotFoundException {
        Optional<User> user1=userRepository.findByUserName(username);

        if (user1.isPresent()){
            return user1.get();
        }else {
            throw new UserNotFoundException();
        }
    }
}
