package br.com.fiap.connectionsolutions_ia.dashboard;

import br.com.fiap.connectionsolutions_ia.cliente.ClienteService;
import br.com.fiap.connectionsolutions_ia.lead.LeadService;
import br.com.fiap.connectionsolutions_ia.cliente.Cliente;
import br.com.fiap.connectionsolutions_ia.lead.Lead;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final ClienteService clienteService;
    private final LeadService  leadService;

    public DashboardController(ClienteService clienteService, LeadService leadService) {
        this.clienteService = clienteService;
        this.leadService = leadService;
    }

    @GetMapping
    public String exibirDashboard(Model model) {
        List<Cliente> clientes = clienteService.buscarTodas().stream()
                .limit(10)
                .collect(Collectors.toList());

        List<Lead> leads = leadService.buscarTodos().stream()
                .limit(10)
                .collect(Collectors.toList());

        model.addAttribute("clientes", clientes);
        model.addAttribute("leads", leads);

        return "dashboard";
    }
}
