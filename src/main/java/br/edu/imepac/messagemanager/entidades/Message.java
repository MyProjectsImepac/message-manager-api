// src/main/java/br/edu/imepac/messagemanager/entidades/Message.java
package br.edu.imepac.messagemanager.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "api_key")
    private String apiKey;

    @ManyToOne
    @JoinColumn(name = "contact_receiver_id")
    private Contact contactReceiver;

    @ManyToOne
    @JoinColumn(name = "contact_sender_id")
    private Contact contactSender;

    private String message;
}