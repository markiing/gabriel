package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Aluno;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.AlunoDAO;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class AlunoService {

	private AlunoDAO alunoDAO;
	
	@Autowired
    public AlunoService(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }
	
	public List<Aluno> listAll(){
		return alunoDAO.listAll();
	}
	
	public Aluno save(Aluno aluno) {
		return alunoDAO.save(aluno);
	}
	
	public void update(Aluno aluno) {
		alunoDAO.update(aluno);
	}

}
