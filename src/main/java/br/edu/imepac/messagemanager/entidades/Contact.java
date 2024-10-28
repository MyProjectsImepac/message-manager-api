// src/main/java/br/edu/imepac/messagemanager/entidades/Contact.java
package br.edu.imepac.messagemanager.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private Date birthDate;

    @OneToMany(mappedBy = "contactReceiver", cascade = CascadeType.REMOVE)
    private List<Message> receivedMessages;

    @OneToMany(mappedBy = "contactSender", cascade = CascadeType.REMOVE)
    private List<Message> sentMessages;
}