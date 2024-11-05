package br.com.fiap.connectionsolutions_ia.lead.dto;



import br.com.fiap.connectionsolutions_ia.interesse.Interesse;
import br.com.fiap.connectionsolutions_ia.interesse.dto.InteresseFormRequest;
import br.com.fiap.connectionsolutions_ia.lead.Lead;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record LeadFormRequest(
        @NotBlank(message = "{campo.obrigatorio}")
        @Size(min=3, max=200, message = "{tamanho.campo}")
        String nome,
        @NotBlank(message = "{campo.obrigatorio}")
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank(message = "{campo.obrigatorio}")
        @Size(min=10, max=11, message = "{tamanho.telefone}")
        String telefone,
        @NotNull(message = "{campo.obrigatorio}")
        @PastOrPresent(message = "{data.passado}")
        LocalDate dtInteracao,
        InteresseFormRequest interesse
) {
    public Lead toModel(Interesse interesse){
        return Lead.builder()
                .nome(nome)
                .email(email)
                .telefone(telefone)
                .dtInteracao(dtInteracao)
                .interesse(interesse)
                .build();
    }
}
