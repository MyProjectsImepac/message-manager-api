package br.edu.imepac.messagemanager.services;

import br.edu.imepac.messagemanager.dtos.message.MessageCreateDTO;
import br.edu.imepac.messagemanager.dtos.message.MessageDTO;
import br.edu.imepac.messagemanager.entidades.Message;
import br.edu.imepac.messagemanager.repositories.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MessageDTO createMessage(MessageCreateDTO messageCreateDTO, String apiKey) {
        messageCreateDTO.setApiKey(apiKey);
        Message message = modelMapper.map(messageCreateDTO, Message.class);
        Message savedMessage = messageRepository.save(message);
        return modelMapper.map(savedMessage, MessageDTO.class);
    }

    public List<MessageDTO> getAllMessages(String apiKey) {
        List<Message> messages = messageRepository.findByApiKey(apiKey);
        return messages.stream().map(message -> modelMapper.map(message, MessageDTO.class)).toList();

    }

    public MessageDTO getMessageById(Long id, String apiKey) {
        Message message = messageRepository.findByIdAndApiKey(id, apiKey);
        if (message != null) {
            return modelMapper.map(message, MessageDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found with id " + id);
        }
    }

    public MessageDTO updateMessage(Long id, MessageCreateDTO messageCreateDTO, String apiKey) {
        Message message = messageRepository.findByIdAndApiKey(id, apiKey);
        if (message != null) {
            modelMapper.map(messageCreateDTO, message);
            Message updatedMessage = messageRepository.save(message);
            return modelMapper.map(updatedMessage, MessageDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found with id " + id);
        }
    }

    public void deleteMessage(Long id, String apiKey) {
        messageRepository.deleteByIdAndApiKey(id, apiKey);
    }
}