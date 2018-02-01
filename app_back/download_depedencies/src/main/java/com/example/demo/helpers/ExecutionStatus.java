package com.example.demo.helpers;

import com.example.demo.domain.Aluno;

public class ExecutionStatus {
	private String code;
	private String message;
	private Aluno aluno;
	
	public ExecutionStatus(){
		
	}
	
	public ExecutionStatus(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public ExecutionStatus(String code, String message, Aluno aluno) {
		this.code = code;
		this.message = message;
		this.aluno = aluno;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
