package com.fsb.chatapplication.Services;

import com.fsb.chatapplication.Models.User;
import com.fsb.chatapplication.exceptions.UserAlreadyExistException;
import com.fsb.chatapplication.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<User> getall() throws UserNotFoundException;

    User addUser(User user) throws UserAlreadyExistException;

    User getUserByUserName(String username)  throws UserNotFoundException;
}
