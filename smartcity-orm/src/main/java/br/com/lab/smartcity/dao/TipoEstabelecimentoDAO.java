package br.com.lab.smartcity.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.lab.smartcity.domain.TipoEstabelecimento;

public class TipoEstabelecimentoDAO extends GenericDAO<TipoEstabelecimento, Integer> {
	public TipoEstabelecimentoDAO(EntityManager em) {
		super(em);
	}

	@Override
	public List<TipoEstabelecimento> listar() {
		return this.em.createQuery("from TipoEstabelecimento").getResultList();
	}

	public List<TipoEstabelecimento> listarOrdenadoNome() {
		return this.em.createQuery("from TipoEstabelecimento order by nome").getResultList();
	}

	public List<TipoEstabelecimento> listarTresUltimos() {
		return this.em.createQuery("from TipoEstabelecimento order by id desc").setMaxResults(3).getResultList();
	}

	public List<TipoEstabelecimento> listarPaginado(int itensPorPagina, int pagina) {
		int primeiro = (pagina - 1) * itensPorPagina;
		return this.em.createQuery("from TipoEstabelecimento order by nome").setMaxResults(itensPorPagina)
				.setFirstResult(primeiro).getResultList();
	}

	public void alterarTipoTodos(TipoEstabelecimento tipo) {
		this.em.createQuery("update Estabelecimento e set e.tipo = :tipo").setParameter("tipo", tipo).executeUpdate();
	}

	public int alterarTipoTodosQtd(TipoEstabelecimento tipo) {
		return this.em.createQuery("update Estabelecimento e set e.tipo = :tipo").setParameter("tipo", tipo)
				.executeUpdate();
	}

	public void excluirAntesDe(Calendar data) {
		this.em.createQuery("delete from Estabelecimento e where dataCriacao < :data").setParameter("data", data)
				.executeUpdate();
	}

	public int excluirAntesDeQtd(Calendar data) {
		return this.em.createQuery("delete from Estabelecimento e where dataCriacao < :data")
				.setParameter("data", data).executeUpdate();
	}

}