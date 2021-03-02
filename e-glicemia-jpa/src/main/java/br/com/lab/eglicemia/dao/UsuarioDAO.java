package br.com.lab.eglicemia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.lab.eglicemia.domain.TipoDiabetes;
import br.com.lab.eglicemia.domain.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Integer>{

	public UsuarioDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}
	
	public List<Usuario> listarPorNome(String nome) {
		TypedQuery<Usuario> typedQuery = this.em.createQuery("select u from Usuario u where u.nome = :nome", Usuario.class).setParameter("nome", nome);
		return typedQuery.getResultList();
	}

	public List<Usuario> listarPorTipoDiabetes(TipoDiabetes tipo) {
		TypedQuery<Usuario> typedQuery = this.em.createQuery("select u from Usuario u " + "where u.tipoDiabetes = :tipo", Usuario.class)
				.setParameter("tipo", tipo);
		return typedQuery.getResultList();
	}
	
	public List<Usuario> listarOrdenadoNome() {
		TypedQuery<Usuario> typedQuery = this.em.createQuery("from Usuario order by nome", Usuario.class); 
		return typedQuery.getResultList();
	}

	public List<Usuario> listarTresUltimos() {
		TypedQuery<Usuario> typedQuery = this.em.createQuery("from Usuario order by id desc", Usuario.class); 
		return typedQuery.setMaxResults(3).getResultList();
	}

	public List<Usuario> listarPaginado(int itensPorPagina, int pagina) {
		int primeiro = (pagina - 1) * itensPorPagina;
		TypedQuery<Usuario> typedQuery = this.em.createQuery("from Usuario order by nome", Usuario.class); 
		return typedQuery.setMaxResults(itensPorPagina)
				.setFirstResult(primeiro).getResultList();
	}

	public void alterarTipoDiabetesTodos(TipoDiabetes tipo) {
		this.em.createQuery("update Usuario u set u.tipoDiabetes = :tipo").setParameter("tipo", tipo).executeUpdate();
	}

	public int alterarTipoTodosQtd(TipoDiabetes tipo) {
		return this.em.createQuery("update Usuario u set u.tipoDiabetes = :tipo").setParameter("tipo", tipo)
				.executeUpdate();
	}

}
