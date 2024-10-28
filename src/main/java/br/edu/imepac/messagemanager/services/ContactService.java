package br.edu.imepac.messagemanager.services;

import br.edu.imepac.messagemanager.dtos.contact.ContactCreateDTO;
import br.edu.imepac.messagemanager.dtos.contact.ContactDTO;
import br.edu.imepac.messagemanager.entidades.Contact;
import br.edu.imepac.messagemanager.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactDTO createContact(ContactCreateDTO contactCreateDTO) {
        Contact contact = new Contact();
        contact.setName(contactCreateDTO.getName());
        contact.setEmail(contactCreateDTO.getEmail());
        contact.setBirthDate(contactCreateDTO.getBirthDate());
        Contact savedContact = contactRepository.save(contact);
        return convertToDTO(savedContact);
    }

    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ContactDTO> getContactById(Long id) {
        return contactRepository.findById(id).map(this::convertToDTO);
    }

    public ContactDTO updateContact(Long id, ContactCreateDTO contactCreateDTO) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isPresent()) {
            Contact contact = contactOptional.get();
            contact.setName(contactCreateDTO.getName());
            contact.setEmail(contactCreateDTO.getEmail());
            contact.setBirthDate(contactCreateDTO.getBirthDate());
            Contact updatedContact = contactRepository.save(contact);
            return convertToDTO(updatedContact);
        } else {
            throw new RuntimeException("Contact not found with id " + id);
        }
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    private ContactDTO convertToDTO(Contact contact) {
        return new ContactDTO(contact.getId(), contact.getName(), contact.getEmail(), contact.getBirthDate());
    }
}