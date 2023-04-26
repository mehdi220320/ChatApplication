package com.fsb.chatapplication.Services;

import com.fsb.chatapplication.Models.Chat;
import com.fsb.chatapplication.Models.Message;
import com.fsb.chatapplication.exceptions.ChatAlreadyExistException;
import com.fsb.chatapplication.exceptions.ChatNotFoundException;
import com.fsb.chatapplication.exceptions.NoChatExistsInTheRepository;

import java.util.HashSet;
import java.util.List;

public interface ChatService {
    public Chat addChat(Chat chat) throws ChatAlreadyExistException, ChatNotFoundException;

    List<Chat> findallchats() throws NoChatExistsInTheRepository;

    Chat getById(long id)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatBySecondUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName)  throws ChatNotFoundException;

    Chat addMessage(Message add, long chatId)  throws ChatNotFoundException;
}
