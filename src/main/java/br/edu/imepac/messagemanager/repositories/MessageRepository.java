package br.edu.imepac.messagemanager.repositories;

import br.edu.imepac.messagemanager.entidades.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}