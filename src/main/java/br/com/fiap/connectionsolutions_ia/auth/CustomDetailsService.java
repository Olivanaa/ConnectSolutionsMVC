package br.com.fiap.connectionsolutions_ia.auth;

import br.com.fiap.connectionsolutions_ia.usuario.Usuario;
import br.com.fiap.connectionsolutions_ia.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//public class CustomDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("Procurando por usuário com email: " + username);
//        Usuario user = usuarioRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
//
//        System.out.println("Usuário encontrado: " + user.getEmail() + " com role: " + user.getRole());
//        return User.builder()
//                .username(user.getEmail())
//                .password(user.getSenha())
//                .roles(user.getRole())
//                .build();
//    }
//
//
//}
