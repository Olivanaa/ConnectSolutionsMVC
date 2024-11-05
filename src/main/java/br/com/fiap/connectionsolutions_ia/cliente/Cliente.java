package br.com.fiap.connectionsolutions_ia.cliente;

import br.com.fiap.connectionsolutions_ia.endereco.Endereco;
import br.com.fiap.connectionsolutions_ia.interesse.Interesse;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "cliente")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{campo.obrigatorio}")
    @Size(min=3, max= 200, message = "{tamanho.campo}")
    private String nome;

    @NotBlank(message = "{campo.obrigatorio}")
    @Email(message = "{email.invalido}")
    private String email;

    @NotBlank(message = "{campo.obrigatorio}")
    @Size(min=10, max=11, message = "{tamanho.telefone}")
    private String telefone;

    @NotNull(message = "{campo.obrigatorio}")
    @Past
    private LocalDate dtaNascimento;

    @NotNull(message = "{campo.obrigatorio}")
    @PastOrPresent
    private LocalDate dtaCadastro;

    @NotBlank(message = "{campo.obrigatorio}")
    @Size(min=3, max=50, message = "{tamanho.campo}")
    private String segmentoMercado;

    @NotNull(message = "{campo.obrigatorio}")
    @PastOrPresent
    private LocalDate dtaUltimaInteracao;

    @OneToOne
    private Endereco endereco;

    @OneToOne
    private Interesse interesse;

}