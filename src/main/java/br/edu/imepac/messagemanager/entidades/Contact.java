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

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String estado;
    private String numero;

    @Column(name = "api_key")
    private String apiKey;


    @OneToMany(mappedBy = "contactReceiver", cascade = CascadeType.REMOVE)
    private List<Message> receivedMessages;

    @OneToMany(mappedBy = "contactSender", cascade = CascadeType.REMOVE)
    private List<Message> sentMessages;
}