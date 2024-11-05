package br.com.fiap.connectionsolutions_ia.usuario.dto;



import br.com.fiap.connectionsolutions_ia.usuario.Usuario;

import java.time.LocalDateTime;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        LocalDateTime createdAt
) {
    public static UsuarioResponse from(Usuario usuario){
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCreatedAt()
        );
    }
}
