package br.com.fiap.connectionsolutions_ia.endereco;


import br.com.fiap.connectionsolutions_ia.endereco.dto.EnderecoFormRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco criar(@Valid Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public Endereco buscarPorId(Long id) {
        return enderecoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado")
        );
    }

    public List<Endereco> buscarTodos(){
        return enderecoRepository.findAll();
    }

    public Endereco atualizar(Long id, EnderecoFormRequest enderecoForm){
        verificarId(id);
        Endereco enderecoAtualizado = enderecoForm.toModel();
        enderecoAtualizado.setId(id);
        return enderecoRepository.save(enderecoAtualizado);
    }

    public void deletar(Long id){
        verificarId(id);
        enderecoRepository.deleteById(id);
    }

    private void verificarId(Long id) {
        enderecoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado endereço com o id")
        );
    }

}
