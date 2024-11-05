package br.com.fiap.connectionsolutions_ia.lead;


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
@Table(name = "lead")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{campo.obrigatorio}")
    @Size(min=3, max=200, message = "{tamanho.campo}")
    private String nome;

    @NotBlank(message = "{campo.obrigatorio}")
    @Email(message = "{email.invalido}")
    private String email;

    @NotBlank(message = "{campo.obrigatorio}")
    @Size(min=10, max=11, message = "{tamanho.telefone}")
    private String telefone;

    @NotNull(message = "{campo.obrigatorio}")
    @PastOrPresent
    private LocalDate dtInteracao;

    @ManyToOne
    private Interesse interesse;
}
