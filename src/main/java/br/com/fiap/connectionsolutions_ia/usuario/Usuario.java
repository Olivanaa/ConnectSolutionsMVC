package br.com.fiap.connectionsolutions_ia.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "nome", nullable = false)
    String nome;
    @Column(name = "email", unique = true, nullable = false)
    String email;
    @Column(name = "senha", unique = true, nullable = false)
    String senha;
    @Column(name = "role", nullable = false)
    String role;
    LocalDateTime createdAt;
}
