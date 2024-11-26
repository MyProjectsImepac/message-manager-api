package br.edu.imepac.messagemanager.repositories;

import br.edu.imepac.messagemanager.entidades.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByApiKey(String apiKey);

    Message findByIdAndApiKey(Long id, String apiKey);

    void deleteByIdAndApiKey(Long id, String apiKey);
}