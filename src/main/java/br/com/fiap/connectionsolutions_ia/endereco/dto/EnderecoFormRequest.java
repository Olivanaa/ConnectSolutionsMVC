package br.com.fiap.connectionsolutions_ia.endereco.dto;


import br.com.fiap.connectionsolutions_ia.endereco.Endereco;
import br.com.fiap.connectionsolutions_ia.enums.TipoEnderecoEnum;
import br.com.fiap.connectionsolutions_ia.validation.TipoEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoFormRequest(
        @NotBlank(message = "{campo.obrigatorio}")
        String logradouro,
        @NotBlank(message = "{campo.obrigatorio}")
        String numero,
        String complemento,
        @NotBlank(message = "{campo.obrigatorio}")
        String bairro,
        @NotBlank(message = "{campo.obrigatorio}")
        String cidade,
        @NotBlank(message = "{campo.obrigatorio}")
        String estado,
        @NotBlank(message = "{campo.obrigatorio}")
        @Size(min = 8, max = 8, message = "{tamanho.cep}")
        String cep,
        @TipoEndereco
        String tipo
) {
    public Endereco toModel(){
        return Endereco.builder()
                .logradouro(logradouro)
                .numero(numero)
                .complemento(complemento)
                .bairro(bairro)
                .cidade(cidade)
                .estado(estado)
                .cep(cep)
                .tipo(tipo)
                .build();
    }
}
