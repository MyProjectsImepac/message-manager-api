package br.edu.imepac.messagemanager.dtos.message;

import br.edu.imepac.messagemanager.dtos.contact.ContactDTO;
import br.edu.imepac.messagemanager.entidades.Contact;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageCreateDTO {
    private ContactDTO contactReceiver;
    private ContactDTO contactSender;
    private String message;
}