package br.com.fiap.connectionsolutions_ia.cliente.dto;



import br.com.fiap.connectionsolutions_ia.cliente.Cliente;
import br.com.fiap.connectionsolutions_ia.endereco.Endereco;
import br.com.fiap.connectionsolutions_ia.endereco.dto.EnderecoFormRequest;
import br.com.fiap.connectionsolutions_ia.interesse.Interesse;
import br.com.fiap.connectionsolutions_ia.interesse.dto.InteresseFormRequest;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClienteFormRequest(
        @NotBlank(message = "{campo.obrigatorio}")
        @Size(min=3, max= 200, message = "{tamanho.campo}")
        String nome,
        @NotBlank(message = "{campo.obrigatorio}")
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank(message = "{campo.obrigatorio}")
        @Size(min=10, max=11, message = "{tamanho.telefone}")
        String telefone,
        @NotNull(message = "{campo.obrigatorio}")
        @Past(message = "{data.passado}")
        LocalDate dtaCadastro,
        @NotNull(message = "{campo.obrigatorio}")
        @PastOrPresent(message = "{data.passado}")
        LocalDate dtaNascimento,
        @NotBlank(message = "{campo.obrigatorio}")
        @Size(min=3, max=50, message = "{tamanho.campo}")
        String segmentoMercado,
        @NotNull(message = "{campo.obrigatorio}")
        @PastOrPresent(message = "{data.passado}")
        LocalDate dtaUltimaInteracao,
        EnderecoFormRequest endereco,
        InteresseFormRequest interesse
) {
    public Cliente toModel(Endereco endereco, Interesse interesse){


        return Cliente.builder()
                .nome(nome)
                .email(email)
                .telefone(telefone)
                .dtaNascimento(dtaNascimento)
                .dtaCadastro(dtaCadastro)
                .segmentoMercado(segmentoMercado)
                .dtaUltimaInteracao(dtaUltimaInteracao)
                .endereco(endereco)
                .interesse(interesse)
                .build();

    }
}
