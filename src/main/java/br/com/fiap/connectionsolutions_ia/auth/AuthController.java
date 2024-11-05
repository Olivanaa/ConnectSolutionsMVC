package br.com.fiap.connectionsolutions_ia.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class AuthController {


    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "E-mail ou senha inválidos");
        }
        return "login";
    }

//    @GetMapping("/dashboard")
//    public String showDashboard() {
//        return "dashboard";
//    }
}

