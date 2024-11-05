package br.com.fiap.connectionsolutions_ia.cliente;


import br.com.fiap.connectionsolutions_ia.cliente.dto.ClienteFormRequest;
import br.com.fiap.connectionsolutions_ia.endereco.Endereco;
import br.com.fiap.connectionsolutions_ia.endereco.EnderecoService;
import br.com.fiap.connectionsolutions_ia.interesse.Interesse;
import br.com.fiap.connectionsolutions_ia.interesse.InteresseService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;
    private final InteresseService interesseService;

    public ClienteService(ClienteRepository clienteRepository, EnderecoService enderecoService, InteresseService interesseService) {
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
        this.interesseService = interesseService;
    }

    @Transactional
    public Cliente criar(@Valid ClienteFormRequest clienteForm){
        Endereco endereco = enderecoService.criar(clienteForm.endereco().toModel());
        Interesse interesse = interesseService.criar(clienteForm.interesse().toModel());

        Cliente cliente = clienteForm.toModel(endereco, interesse);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> buscarTodas(){
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id){
        return clienteRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @Transactional
    public Cliente atualizar(Long id, @Valid ClienteFormRequest clienteForm){
        verificarCliente(id);
        Cliente cliente = buscarPorId(id);

        Endereco enderecoAtualizado;
        if(clienteForm.endereco() != null){
            enderecoAtualizado = enderecoService.atualizar(cliente.getEndereco().getId(), clienteForm.endereco());
        }else{
            enderecoAtualizado = cliente.getEndereco();
        }
        Interesse interesseAtualizado;
        if(clienteForm.interesse() != null){
            interesseAtualizado = interesseService.atualizar(cliente.getInteresse().getId(), clienteForm.interesse());
        }else{
            interesseAtualizado = cliente.getInteresse();
        }

        Cliente clienteAtualizado = clienteForm.toModel(enderecoAtualizado, interesseAtualizado);
        clienteAtualizado.setId(id);

        return clienteRepository.save(clienteAtualizado);
    }

    public void deletar(Long id){
        verificarCliente(id);
        clienteRepository.deleteById(id);
    }

    private void verificarCliente(Long id) {
        clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe cliente com o id informado."));
    }


}
