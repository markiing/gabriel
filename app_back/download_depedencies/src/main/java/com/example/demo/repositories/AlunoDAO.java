package com.example.demo.repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Aluno;

@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class AlunoDAO {

	private SessionFactory sessionFactory;
    
	@Autowired 
	public AlunoDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> listAll() {
    	Session session = this.sessionFactory.getCurrentSession();
        TypedQuery<Aluno> query = (TypedQuery<Aluno>) session.getNamedQuery("listAll");
        return query.getResultList();
	}
	
    @SuppressWarnings("unchecked")
	public List<Aluno> findByName(String nome) {
    	Session session = this.sessionFactory.getCurrentSession();
    	return session.createCriteria(Aluno.class)
		 	.add(Restrictions.like("nome", "%"+nome+"%"))
		 	.list();
	}

	public Aluno save(Aluno aluno) {
		Session session = this.sessionFactory.openSession();
		session.save(aluno);
		session.close();
		return aluno;
		
	}
	
	public void update(Aluno aluno) {
		Session session = this.sessionFactory.openSession();
		Aluno alunop = (Aluno) session.load(Aluno.class, new Integer(aluno.getId()));
		Transaction tx = session.beginTransaction();
		alunop.setNome(aluno.getNome());
		alunop.setEndereco(aluno.getEndereco());
		alunop.setEmail(aluno.getEmail());
		alunop.setTelefone(aluno.getTelefone());
		alunop.setCategoria(aluno.getCategoria());
		session.update(alunop);		
		tx.commit();
	}

	// public List<User> findByEmailAndPassword(String email,String password){
	// 	Session session = this.sessionFactory.getCurrentSession();
	// 	return session.createCriteria(User.class)
	// 	.add(Restrictions.eq("email", email))
	// 	.add(Restrictions.eq("password", password))
	// 	.list();
	// }
}