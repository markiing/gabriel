package com.example.demo.controllers;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Aluno;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.helpers.ExecutionStatus;
import com.example.demo.services.AlunoService;

@RestController
@RequestMapping("/alunos/*")
public class AlunoController {
	
	final static Logger logger = LoggerFactory.getLogger(AlunoController.class);
	
	AlunoService alunosService;

	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	public AlunoController(AlunoService alunosService) {
		this.alunosService = alunosService;
	}
	
	@GetMapping(value="/list", produces="application/json")
	public List<Aluno> listAll(ModelMap model) {
		
		List<Aluno> alunos = alunosService.listAll();
		return alunos;
	}
	
	@PostMapping(value="/save")
	public ExecutionStatus saveUser(ModelMap model, @RequestBody Aluno a) {
		alunosService.save(a);
		return new ExecutionStatus("ALUNO_ATUALIZADO", "Aluno atualizado com sucesso!");
	}
	
	
	@PostMapping(value="/update")
	public ExecutionStatus updateUser(ModelMap model, @RequestBody Aluno a) {
		Aluno aluno = new Aluno();
		aluno.setId(a.getId());
		aluno.setNome(a.getNome());
		aluno.setCpf(a.getCpf());
		aluno.setEmail(a.getEmail());
		aluno.setEndereco(a.getEndereco());
		aluno.setTelefone(a.getTelefone());
		aluno.setCategoria(a.getCategoria());
		alunosService.update(aluno);
		return new ExecutionStatus("ALUNO_ATUALIZADO", "Aluno atualizado com sucesso!");
	}
}
