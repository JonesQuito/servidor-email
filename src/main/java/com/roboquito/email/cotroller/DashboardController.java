package com.roboquito.email.cotroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.roboquito.email.model.Cliente;
import com.roboquito.email.repository.Clientes;

@Controller
@RequestMapping("/servidor-email")
public class DashboardController {
	
	@Autowired
	private Clientes clientes;
	
	@GetMapping
	public String dashboard() {
		//return "/dashboard";
		return "/dashboard";
	}
	
	
	
	@GetMapping("/novoCliente")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView modelAndView = new ModelAndView("/dashboard");
		
		modelAndView.addObject(cliente);
		
		return modelAndView;
	}
	
	
	@PostMapping("/novoCliente")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cliente);
		}
		
		clientes.save(cliente);
		
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		
		return new ModelAndView("redirect:/servidor-email/novoCliente");
	}

}
