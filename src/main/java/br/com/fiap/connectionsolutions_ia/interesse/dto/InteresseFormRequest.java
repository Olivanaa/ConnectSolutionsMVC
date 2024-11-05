package br.com.fiap.connectionsolutions_ia.interesse.dto;



import br.com.fiap.connectionsolutions_ia.interesse.Interesse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record InteresseFormRequest(
        @NotBlank(message = "{campo.obrigatorio}")
        @Size(min=5, max=500, message = "{tamanho.campo}")
        String descricao,
        @NotNull(message = "{campo.obrigatorio}")
        @PastOrPresent(message = "{data.passado}")
        LocalDate dtaInteracao
) {
    public Interesse toModel(){
        return Interesse.builder()
                .descricao(descricao)
                .dtInteracao(dtaInteracao)
                .build();
    }
}
