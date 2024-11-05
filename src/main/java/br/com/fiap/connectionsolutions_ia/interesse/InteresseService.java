package br.com.fiap.connectionsolutions_ia.interesse;


import br.com.fiap.connectionsolutions_ia.interesse.dto.InteresseFormRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InteresseService {

    private final InteresseRepository interesseRepository;

    public InteresseService(InteresseRepository interesseRepository) {
        this.interesseRepository = interesseRepository;
    }

    public Interesse criar(@Valid Interesse interesse){
        return interesseRepository.save(interesse);
    }

    public List<Interesse> buscarTodos(){
        return interesseRepository.findAll();
    }

    public Interesse buscarPorId(Long id){
        return interesseRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado")
        );
    }

    public void deletar(Long id){
        verificarInteresse(id);
        interesseRepository.deleteById(id);
    }

    public Interesse atualizar(Long id, InteresseFormRequest interesseForm){
        verificarInteresse(id);
        Interesse interesseAtualizado = interesseForm.toModel();
        interesseAtualizado.setId(id);
        return interesseRepository.save(interesseAtualizado);
    }

    private void verificarInteresse(Long id) {
        interesseRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço com id" + id + "não encontrado")
        );
    }
}
