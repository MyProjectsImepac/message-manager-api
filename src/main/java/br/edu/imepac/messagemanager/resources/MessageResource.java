package br.edu.imepac.messagemanager.resources;

import br.edu.imepac.messagemanager.dtos.message.MessageCreateDTO;
import br.edu.imepac.messagemanager.dtos.message.MessageDTO;
import br.edu.imepac.messagemanager.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageResource {

    private final MessageService messageService;

    @Autowired
    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO createMessage(@RequestBody MessageCreateDTO messageCreateDTO, @RequestHeader("api-key") String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "API Key is required");
        }
        return messageService.createMessage(messageCreateDTO, apiKey);
    }

    @GetMapping
    public List<MessageDTO> getAllMessages(@RequestHeader("api-key") String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "API Key is required");
        }
        return messageService.getAllMessages(apiKey);
    }

    @GetMapping("/{id}")
    public MessageDTO getMessageById(@PathVariable Long id, @RequestHeader("api-key") String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "API Key is required");
        }
        return messageService.getMessageById(id, apiKey);
    }

    @PutMapping("/{id}")
    public MessageDTO updateMessage(@PathVariable Long id, @RequestBody MessageCreateDTO messageCreateDTO, @RequestHeader("api-key") String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "API Key is required");
        }
        return messageService.updateMessage(id, messageCreateDTO, apiKey);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable Long id, @RequestHeader("api-key") String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "API Key is required");
        }
        messageService.deleteMessage(id, apiKey);
    }
}