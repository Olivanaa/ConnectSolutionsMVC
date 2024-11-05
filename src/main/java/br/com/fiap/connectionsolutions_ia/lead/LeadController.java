package br.com.fiap.connectionsolutions_ia.lead;

import br.com.fiap.connectionsolutions_ia.cliente.Cliente;
import br.com.fiap.connectionsolutions_ia.cliente.dto.ClienteFormRequest;
import br.com.fiap.connectionsolutions_ia.endereco.dto.EnderecoFormRequest;
import br.com.fiap.connectionsolutions_ia.enums.TipoEnderecoEnum;
import br.com.fiap.connectionsolutions_ia.interesse.dto.InteresseFormRequest;
import br.com.fiap.connectionsolutions_ia.lead.dto.LeadFormRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lead")
@Slf4j
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @GetMapping("/adicionar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("leadForm", new LeadFormRequest(null,null,null,null,new InteresseFormRequest(null, null)));
        return "lead/adicionar";
    }

    @PostMapping("/adicionar")
    public String adicionarLead(@Valid @ModelAttribute("leadForm") LeadFormRequest leadForm, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("leadForm", leadForm);
            log.error(result.getAllErrors().toString());
            return "lead/adicionar";
        }
        log.info("Tipo de endereço recebido: {}", leadForm.interesse().toModel().getDescricao());
        leadService.criar(leadForm);
        return "redirect:/dashboard";
    }

    @GetMapping("/todos")
    public String exibirTodosLead(Model model) {
        List<Lead> leads = leadService.buscarTodos();
        model.addAttribute("leads", leads);
        return "lead/todos";
    }

    @GetMapping("/details/{id}")
    public String exibirLead(@PathVariable Long id, Model model){
        Lead lead = leadService.buscarPorId(id);
        model.addAttribute("lead", lead);
        return "lead/details";
    }



}
