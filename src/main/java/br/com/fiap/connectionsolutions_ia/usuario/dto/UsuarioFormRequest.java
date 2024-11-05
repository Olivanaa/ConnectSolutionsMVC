package br.com.fiap.connectionsolutions_ia.usuario.dto;


import br.com.fiap.connectionsolutions_ia.usuario.Usuario;

public record UsuarioFormRequest(
        String nome,
        String email,
        String senha,
        String role
) {
    public Usuario toModel(){
        return Usuario.builder()
                .nome(nome)
                .email(email)
                .senha(senha)
                .role(role)
                .build();
    }
}
