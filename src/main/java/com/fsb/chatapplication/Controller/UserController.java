package com.fsb.chatapplication.Controller;

import com.fsb.chatapplication.Models.User;
import com.fsb.chatapplication.Services.UserService;
import com.fsb.chatapplication.exceptions.UserAlreadyExistException;
import com.fsb.chatapplication.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getall")
    public ResponseEntity<List<User>> getall() throws IOException {
        try{
            return new ResponseEntity<List<User>>(userService.getall(), HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity("User not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) throws IOException {
        try{
            return new ResponseEntity<User>(userService.addUser(user), HttpStatus.OK);
        }catch (UserAlreadyExistException e){
            return new ResponseEntity("User already exists", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getbyusername/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String username) throws IOException {
        try{
            return new ResponseEntity<User>(userService.getUserByUserName(username), HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity("User not Found", HttpStatus.NOT_FOUND);
        }
    }
}
