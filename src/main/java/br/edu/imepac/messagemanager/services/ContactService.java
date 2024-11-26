package br.edu.imepac.messagemanager.services;

import br.edu.imepac.messagemanager.dtos.contact.ContactCreateDTO;
import br.edu.imepac.messagemanager.dtos.contact.ContactDTO;
import br.edu.imepac.messagemanager.entidades.Contact;
import br.edu.imepac.messagemanager.repositories.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    ModelMapper modelMapper;

    public ContactDTO createContact(ContactCreateDTO contactCreateDTO) {
        Contact contact = modelMapper.map(contactCreateDTO, Contact.class);
        Contact savedContact = contactRepository.save(contact);
        return modelMapper.map(savedContact, ContactDTO.class);
    }

    public List<ContactDTO> getAllContacts(String apiKey) {
        List<Contact> contacts = contactRepository.findByApiKey(apiKey);
        return contacts.stream().map(contact -> modelMapper.map(contact, ContactDTO.class)).toList();
    }

    public ContactDTO getContactById(Long id, String apiKey) {
        Contact contact = contactRepository.findByIdAndApiKey(id, apiKey);
        return modelMapper.map(contact, ContactDTO.class);
    }

    public ContactDTO updateContact(Long id, ContactCreateDTO contactCreateDTO, String apiKey) {
        Contact contactSaved = contactRepository.findByIdAndApiKey(id, apiKey);
        if (contactSaved == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found with id " + id);
        }
        modelMapper.map(contactCreateDTO, contactSaved);
        Contact updatedContact = contactRepository.save(contactSaved);
        return modelMapper.map(updatedContact, ContactDTO.class);
    }

    public void deleteContact(Long id, String apiKey) {
        contactRepository.deleteByIdAndApiKey(id, apiKey);
    }

}