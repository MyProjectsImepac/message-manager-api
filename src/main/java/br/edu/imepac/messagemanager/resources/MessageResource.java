package br.edu.imepac.messagemanager.resources;

import br.edu.imepac.messagemanager.dtos.message.MessageCreateDTO;
import br.edu.imepac.messagemanager.dtos.message.MessageDTO;
import br.edu.imepac.messagemanager.exceptions.CustomException;
import br.edu.imepac.messagemanager.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageResource {

    @Autowired
    private MessageService messageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO createMessage(@RequestBody MessageCreateDTO messageCreateDTO) {
        return messageService.createMessage(messageCreateDTO);
    }

    @GetMapping
    public List<MessageDTO> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public MessageDTO getMessageById(@PathVariable Long id) {
        Optional<MessageDTO> messageDTO = messageService.getMessageById(id);
        return messageDTO.orElseThrow(() -> new CustomException("Message not found"));
    }

    @PutMapping("/{id}")
    public MessageDTO updateMessage(@PathVariable Long id, @RequestBody MessageCreateDTO messageCreateDTO) {
        return messageService.updateMessage(id, messageCreateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }
}