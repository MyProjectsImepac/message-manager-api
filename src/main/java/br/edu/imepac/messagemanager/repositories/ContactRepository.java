package br.edu.imepac.messagemanager.repositories;

import br.edu.imepac.messagemanager.entidades.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByApiKey(String apiKey);

    Contact findByIdAndApiKey(Long id, String apiKey);

    void deleteByIdAndApiKey(Long id, String apiKey);
}