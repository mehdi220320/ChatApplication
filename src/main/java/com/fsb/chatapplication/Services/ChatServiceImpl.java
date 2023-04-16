package com.fsb.chatapplication.Services;

import com.fsb.chatapplication.Models.Chat;
import com.fsb.chatapplication.Models.Message;
import com.fsb.chatapplication.Repositories.ChatRepository;
import com.fsb.chatapplication.Repositories.MessageRepository;
import com.fsb.chatapplication.exceptions.ChatAlreadyExistException;
import com.fsb.chatapplication.exceptions.ChatNotFoundException;
import com.fsb.chatapplication.exceptions.NoChatExistsInTheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService{
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Override
    public Chat addChat(Chat chat) throws ChatAlreadyExistException {
        chat.setChatId(sequenceGeneratorService.generateSequence(Chat.SEQUENCE_NAME));
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> findallchats() throws NoChatExistsInTheRepository {
        if (chatRepository.findAll().isEmpty()) {
            throw new NoChatExistsInTheRepository();
        } else {
            return chatRepository.findAll();
        }
    }

    @Override
    public Chat getById(long id) throws ChatNotFoundException {
        Optional<Chat> chatid = chatRepository.findById(id);
        if (chatid.isPresent()) {
            return chatid.get();
        } else {
            throw new ChatNotFoundException();
        }    }

    @Override
    public HashSet<Chat> getChatByFirstUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatByFirstUserName(username);

        if (chat.isEmpty()) {
            throw new ChatNotFoundException();
        } else {
            return chat;
        }
    }

    @Override
    public HashSet<Chat> getChatBySecondUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatBySecondUserName(username);
        if (chat.isEmpty()) {
            throw new ChatNotFoundException();
        } else {
            return chat;
        }
    }

    @Override
    public HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatByFirstUserName(username);
        HashSet<Chat> chat1 = chatRepository.getChatBySecondUserName(username);

        chat1.addAll(chat);

        if (chat1.isEmpty()) {
            throw new ChatNotFoundException();
        } else {
            return chat1;
        }
    }

    @Override
    public HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName) throws ChatNotFoundException {
        HashSet<Chat> chat = chatRepository.getChatByFirstUserNameAndSecondUserName(firstUserName, secondUserName);
        HashSet<Chat> chat1 = chatRepository.getChatBySecondUserNameAndFirstUserName(firstUserName, secondUserName);
        if (chat.isEmpty() && chat1.isEmpty()) {
            throw new ChatNotFoundException();
        } else if (chat.isEmpty()) {
            return chat1;
        } else {
            return chat;
        }
    }
    @Override
    public Chat addMessage(Message newMessage, long chatId) throws ChatNotFoundException {
        Optional<Chat> optionalChat = chatRepository.findById(chatId);
        Chat chat = optionalChat.orElseThrow(() -> new ChatNotFoundException());

        if (chat.getMessages() == null) {
            List<Message> messages = new ArrayList<>();
            messages.add(newMessage);
            chat.setMessages(messages);
        } else {
            chat.getMessages().add(newMessage);
        }

        newMessage.setChat(chat);
        messageRepository.save(newMessage);
        return chatRepository.save(chat);
    }


}
