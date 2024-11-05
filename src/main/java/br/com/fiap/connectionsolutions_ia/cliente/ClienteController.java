package br.com.fiap.connectionsolutions_ia.cliente;

import br.com.fiap.connectionsolutions_ia.cliente.dto.ClienteFormRequest;
import br.com.fiap.connectionsolutions_ia.endereco.dto.EnderecoFormRequest;
import br.com.fiap.connectionsolutions_ia.enums.TipoEnderecoEnum;
import br.com.fiap.connectionsolutions_ia.excel.ExcelImporter;
import br.com.fiap.connectionsolutions_ia.excel.ImportError;
import br.com.fiap.connectionsolutions_ia.interesse.dto.InteresseFormRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/cliente")
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;
    private final ExcelImporter excelImporter;

    public ClienteController(ClienteService clienteService, ExcelImporter excelImporter) {
        this.clienteService = clienteService;
        this.excelImporter = excelImporter;
    }

    @GetMapping("/adicionar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tiposDeEndereco", TipoEnderecoEnum.values());
        model.addAttribute("clienteForm", new ClienteFormRequest(null, null, null, null, null, null, null, new EnderecoFormRequest(null, null, null,null, null, null, null,null), new InteresseFormRequest(null, null)));
        return "cliente/adicionar";
    }

    @GetMapping("/importar")
    public String importarClientesPage(Model model) {
        model.addAttribute("mensagemSucesso", model.getAttribute("mensagemSucesso"));
        model.addAttribute("mensagemErro", model.getAttribute("mensagemErro"));
        return "cliente/importar";
    }

    @PostMapping("/adicionar")
    public String adicionarCliente(@Valid @ModelAttribute("clienteForm") ClienteFormRequest clienteForm,
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tiposDeEndereco", TipoEnderecoEnum.values());
            model.addAttribute("clienteForm", clienteForm);
            log.error(bindingResult.getAllErrors().toString());
            return "cliente/adicionar";
        }


        log.info("Tipo de endereço recebido: {}", clienteForm.endereco().toModel().getTipo());
        clienteService.criar(clienteForm);
        return "redirect:/dashboard";
    }

    @PostMapping("/importar")
    public String adicionarClientesExcel(
            @RequestParam("arquivoExcel") MultipartFile arquivoExcel,
            Model model) {

        if (arquivoExcel.isEmpty()) {
            model.addAttribute("mensagemErro", "Por favor, selecione um arquivo para importar.");
            return "cliente/importar";
        }

        try {
            List<Cliente> clientesImportados = excelImporter.importarClientes(arquivoExcel);

            List<ImportError> errosImportacao = excelImporter.getImportErrors();
            if (!errosImportacao.isEmpty()) {
                model.addAttribute("errosImportacao", errosImportacao);
                model.addAttribute("mensagemErro", "Alguns clientes não foram importados devido a erros.");
            } else if (!clientesImportados.isEmpty()) {
                model.addAttribute("mensagemSucesso", "Clientes importados com sucesso!");
            }

        } catch (IOException e) {
            model.addAttribute("mensagemErro", "Erro ao importar clientes do Excel: " + e.getMessage());
            return "cliente/importar";
        }

        return "cliente/importar";
    }

    @GetMapping("/todos")
    public String exibirTodosClientes(Model model) {
        List<Cliente> clientes = clienteService.buscarTodas();
        model.addAttribute("clientes", clientes);
        return "cliente/todos";
    }

    @GetMapping("/details/{id}")
    public String exibirCliente(@PathVariable Long id, Model model){
        Cliente cliente = clienteService.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        return "cliente/details";
    }
}
