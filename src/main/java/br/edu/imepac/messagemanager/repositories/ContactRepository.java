package br.edu.imepac.messagemanager.repositories;

import br.edu.imepac.messagemanager.entidades.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}