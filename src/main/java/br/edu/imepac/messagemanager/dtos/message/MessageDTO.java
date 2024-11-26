package br.edu.imepac.messagemanager.dtos.message;

import br.edu.imepac.messagemanager.dtos.contact.ContactDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {
    private long id;
    private ContactDTO contactReceiver;
    private ContactDTO contactSender;
    private String message;

    private String apiKey;
    
}