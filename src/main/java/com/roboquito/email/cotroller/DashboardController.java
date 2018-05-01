package com.roboquito.email.cotroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@DeleteMapping("/{id}")
	public String remover(@PathVariable Long id, RedirectAttributes attributes) {
		clientes.delete(id);
		attributes.addFlashAttribute("mensagem", "Cliente removido com sucesso!");
		return "redirect:/servidor-email";
	}
	
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("lista-usuarios");
		modelAndView.addObject("usuarios", clientes.findAll());
		
		return modelAndView;
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
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		return novo(clientes.findOne(id));
	}

}
