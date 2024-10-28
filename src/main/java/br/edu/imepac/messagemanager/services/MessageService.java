package br.edu.imepac.messagemanager.services;

import br.edu.imepac.messagemanager.dtos.contact.ContactDTO;
import br.edu.imepac.messagemanager.dtos.message.MessageCreateDTO;
import br.edu.imepac.messagemanager.dtos.message.MessageDTO;
import br.edu.imepac.messagemanager.entidades.Contact;
import br.edu.imepac.messagemanager.entidades.Message;
import br.edu.imepac.messagemanager.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public MessageDTO createMessage(MessageCreateDTO messageCreateDTO) {
        Message message = new Message();
        message.setContactReceiver(convertToEntity(messageCreateDTO.getContactReceiver()));
        message.setContactSender(convertToEntity(messageCreateDTO.getContactSender()));
        message.setMessage(messageCreateDTO.getMessage());
        Message savedMessage = messageRepository.save(message);
        return convertToDTO(savedMessage);
    }

    public List<MessageDTO> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<MessageDTO> getMessageById(Long id) {
        return messageRepository.findById(id).map(this::convertToDTO);
    }

    public MessageDTO updateMessage(Long id, MessageCreateDTO messageCreateDTO) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setContactReceiver(convertToEntity(messageCreateDTO.getContactReceiver()));
            message.setContactSender(convertToEntity(messageCreateDTO.getContactSender()));
            message.setMessage(messageCreateDTO.getMessage());
            Message updatedMessage = messageRepository.save(message);
            return convertToDTO(updatedMessage);
        } else {
            throw new RuntimeException("Message not found with id " + id);
        }
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    private MessageDTO convertToDTO(Message message) {
        return new MessageDTO(
                message.getId(),
                convertToDTO(message.getContactReceiver()),
                convertToDTO(message.getContactSender()),
                message.getMessage()
        );
    }

    private ContactDTO convertToDTO(Contact contact) {
        if (contact == null) {
            return null;
        }
        return new ContactDTO(contact.getId(), contact.getName(), contact.getEmail(), contact.getBirthDate());
    }

    private Contact convertToEntity(ContactDTO contactDTO) {
        if (contactDTO == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setId(contactDTO.getId());
        contact.setName(contactDTO.getName());
        contact.setEmail(contactDTO.getEmail());
        contact.setBirthDate(contactDTO.getBirthDate());
        return contact;
    }
}