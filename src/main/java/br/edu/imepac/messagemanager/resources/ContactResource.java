package br.edu.imepac.messagemanager.resources;

import br.edu.imepac.messagemanager.dtos.contact.ContactCreateDTO;
import br.edu.imepac.messagemanager.dtos.contact.ContactDTO;
import br.edu.imepac.messagemanager.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactResource {

    @Autowired
    private ContactService contactService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createContact(@RequestBody ContactCreateDTO contactCreateDTO, @RequestHeader("api-key") String apiKey) {
        if(apiKey == null || apiKey.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "API Key is required");
        }
        contactCreateDTO.setApiKey(apiKey);
        return contactService.createContact(contactCreateDTO);
    }

    @GetMapping
    public List<ContactDTO> getAllContacts(@RequestHeader("api-key") String apiKey) {
        if(apiKey == null || apiKey.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "API Key is required");
        }
        return contactService.getAllContacts(apiKey);
    }

    @GetMapping("/{id}")
    public ContactDTO getContactById(@PathVariable Long id, @RequestHeader("api-key") String apiKey) {
        if(apiKey == null || apiKey.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "API Key is required");
        }
        ContactDTO contact = contactService.getContactById(id, apiKey);
        if (contact == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found with id " + id);
        } else {
            return contact;
        }
    }

    @PutMapping("/{id}")
    public ContactDTO updateContact(@PathVariable Long id, @RequestBody ContactCreateDTO contactCreateDTO, @RequestHeader("api-key") String apiKey) {
        if(apiKey == null || apiKey.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "API Key is required");
        }
        return contactService.updateContact(id, contactCreateDTO, apiKey);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long id, @RequestHeader("api-key") String apiKey) {
        if(apiKey == null || apiKey.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "API Key is required");
        }
        contactService.deleteContact(id, apiKey);
    }
}