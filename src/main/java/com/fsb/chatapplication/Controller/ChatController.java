package com.fsb.chatapplication.Controller;

import com.fsb.chatapplication.Models.Chat;
import com.fsb.chatapplication.Models.Message;
import com.fsb.chatapplication.Services.ChatService;
//import com.fsb.chatapplication.Services.SequenceGeneratorService;
import com.fsb.chatapplication.exceptions.ChatAlreadyExistException;
import com.fsb.chatapplication.exceptions.ChatNotFoundException;
import com.fsb.chatapplication.exceptions.NoChatExistsInTheRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;
//    @Autowired
//    private SequenceGeneratorService sequenceGeneratorService;

    @SneakyThrows
    @PostMapping("/add")
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat) throws IOException {

        try {
            return new ResponseEntity<Chat>(chatService.addChat(chat), HttpStatus.CREATED);
        } catch (ChatAlreadyExistException  e) {
            return new ResponseEntity("Chat Already Exist", HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Chat>> getAllChats() {
        try {
            return new ResponseEntity<List<Chat>>(chatService.findallchats(), HttpStatus.OK);
        } catch (NoChatExistsInTheRepository e) {
            return new ResponseEntity("List not found", HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable int id) {
        try {
            return new ResponseEntity<Chat>(chatService.getById(id), HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/firstUserName/{username}")
    public ResponseEntity<?> getChatByFirstUserName(@PathVariable String username) {
        try {
            HashSet<Chat> byChat = this.chatService.getChatByFirstUserName(username);
            return new ResponseEntity<>(byChat, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/getChatByFirstUserNameOrSecondUserName/{username}")
    public ResponseEntity<?> getChatByFirstUserNameOrSecondUserName(@PathVariable String username) {

        try {
            HashSet<Chat> byChat = this.chatService.getChatByFirstUserNameOrSecondUserName(username);
            return new ResponseEntity<>(byChat, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/getChatByFirstUserNameAndSecondUserName")
    public ResponseEntity<?> getChatByFirstUserNameAndSecondUserName(@RequestParam("firstUserName") String firstUserName, @RequestParam("secondUserName") String secondUserName){

        try {
            HashSet<Chat> chatByBothEmail = this.chatService.getChatByFirstUserNameAndSecondUserName(firstUserName, secondUserName);
            return new ResponseEntity<>(chatByBothEmail, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/message/{chatId}")
    public ResponseEntity<Chat> addMessage(@RequestBody Message add , @PathVariable int chatId) throws ChatNotFoundException {
        return new ResponseEntity<Chat>(chatService.addMessage(add,chatId), org.springframework.http.HttpStatus.OK);
    }
    @GetMapping("/lastmessage")
    public ResponseEntity<Message> getLastMessage(@RequestParam("firstUserName") String firstUserName, @RequestParam("secondUserName") String secondUserName){
        try {
            Message lastmessage = this.chatService.getLastMessage(firstUserName, secondUserName);
            System.out.println(lastmessage);
            return new ResponseEntity<>(lastmessage, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.NOT_FOUND);
        }    }
}
