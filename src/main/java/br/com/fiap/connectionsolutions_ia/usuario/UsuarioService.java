package br.com.fiap.connectionsolutions_ia.usuario;


import br.com.fiap.connectionsolutions_ia.usuario.dto.UsuarioFormRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

//    private final UsuarioRepository usuarioRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
//        this.usuarioRepository = usuarioRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public List<Usuario> buscarTodos(){
//        return usuarioRepository.findAll();
//    }
//
//    public Usuario criar(Usuario usuario){
//        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
//        if (usuario.getRole() == null){
//            usuario.setRole("USER");
//        }
//        return usuarioRepository.save(usuario);
//    }
//
//    public Usuario getUsuarioById(Long id){
//        return  usuarioRepository.findById(id).orElseThrow(
//                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario com id " + id + "não encontrado")
//        );
//    }
//
//    public List<Usuario> getUsuarioByNome(String nome){
//        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
//    }
//
//    public Usuario atualizar(Long id, UsuarioFormRequest usuarioForm){
//        verificarUsuario(id);
//        Usuario usuarioAtualizado = usuarioForm.toModel();
//        if (usuarioAtualizado.getRole() == null){
//            usuarioAtualizado.setRole("USER");
//        }
//        usuarioAtualizado.setId(id);
//        return usuarioRepository.save(usuarioAtualizado);
//    }
//
//    public void deletar(Long id){
//        verificarUsuario(id);
//        usuarioRepository.deleteById(id);
//    }
//
//    private void verificarUsuario(Long id) {
//        usuarioRepository.findById(id).orElseThrow(
//                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario com id " + id + "não encontrado")
//        );
//    }
//
//    private void verificarRole(String role){
//        String tipo = usuarioRepository.findByRole(role).toString();
//        if(!Objects.equals(usuarioRepository.findByRole(role).toString(), "ADMIN")){
//            new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso indevido");
//        }else{
//
//        }
//    }
}
