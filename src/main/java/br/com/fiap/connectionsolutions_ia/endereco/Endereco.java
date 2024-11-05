package br.com.fiap.connectionsolutions_ia.endereco;


import br.com.fiap.connectionsolutions_ia.validation.TipoEndereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "endereco")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{campo.obrigatorio}")
    private String logradouro;

    @NotBlank(message = "{campo.obrigatorio}")
    private String numero;

    private String complemento;

    @NotBlank(message = "{campo.obrigatorio}")
    private String bairro;

    @NotBlank(message = "{campo.obrigatorio}")
    private String cidade;

    @NotBlank(message = "{campo.obrigatorio}")
    private String estado;

    @NotBlank(message = "{campo.obrigatorio}")
    @Size(min = 8, max = 8, message = "{tamanho.cep}")
    private String cep;

    @TipoEndereco
    private String tipo;
}
