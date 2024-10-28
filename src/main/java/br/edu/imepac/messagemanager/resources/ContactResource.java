package br.edu.imepac.messagemanager.resources;

import br.edu.imepac.messagemanager.dtos.contact.ContactCreateDTO;
import br.edu.imepac.messagemanager.dtos.contact.ContactDTO;
import br.edu.imepac.messagemanager.exceptions.CustomException;
import br.edu.imepac.messagemanager.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactResource {

    @Autowired
    private ContactService contactService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createContact(@RequestBody ContactCreateDTO contactCreateDTO) {
        return contactService.createContact(contactCreateDTO);
    }

    @GetMapping
    public List<ContactDTO> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ContactDTO getContactById(@PathVariable Long id) {
        Optional<ContactDTO> contactDTO = contactService.getContactById(id);
        return contactDTO.orElseThrow(() -> new CustomException("Contact not found"));
    }

    @PutMapping("/{id}")
    public ContactDTO updateContact(@PathVariable Long id, @RequestBody ContactCreateDTO contactCreateDTO) {
        return contactService.updateContact(id, contactCreateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }
}