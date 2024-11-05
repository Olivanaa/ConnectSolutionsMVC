package br.com.fiap.connectionsolutions_ia.lead;


import br.com.fiap.connectionsolutions_ia.interesse.Interesse;
import br.com.fiap.connectionsolutions_ia.interesse.InteresseService;
import br.com.fiap.connectionsolutions_ia.lead.dto.LeadFormRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LeadService {

    private final LeadRepository leadRepository;
    private final InteresseService interesseService;

    public LeadService(LeadRepository leadRepository, InteresseService interesseService) {
        this.leadRepository = leadRepository;
        this.interesseService = interesseService;
    }

    @Transactional
    public Lead criar(@Valid LeadFormRequest leadForm){
        Interesse interesse = interesseService.criar(leadForm.interesse().toModel());
        Lead lead = leadForm.toModel(interesse);
        return leadRepository.save(lead);
    }

    public List<Lead> buscarTodos(){
        return leadRepository.findAll();
    }

    public Lead buscarPorId(Long id){
        return leadRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lead não encontrada")
        );
    }

    public void deletar(Long id){
        verificarLead(id);
        leadRepository.deleteById(id);
    }

    public Lead atualizar(Long id, @Valid LeadFormRequest leadForm){
        verificarLead(id);
        Lead lead = buscarPorId(id);

        Interesse interesseAtualizado;
        if(leadForm.interesse() != null){
            interesseAtualizado = interesseService.atualizar(lead.getInteresse().getId(), leadForm.interesse());
        }else{
            interesseAtualizado = lead.getInteresse();
        }
        Lead leadAtualizado = leadForm.toModel(interesseAtualizado);

        leadAtualizado.setId(id);
        return leadRepository.save(leadAtualizado);
    }

    private void verificarLead(Long id) {
        leadRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lead com o id " + id + "não encontrado")
        );
    }
}
