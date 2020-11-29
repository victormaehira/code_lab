package com.lab.victor.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.victor.api.model.Chave;
import com.lab.victor.api.model.Pessoa;
import com.lab.victor.api.repository.ChaveRepository;
import com.lab.victor.api.repository.PessoaRepository;
import com.lab.victor.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class ChaveService {

	@Autowired
	private ChaveRepository chaveRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Chave salvar(Chave chave) {
		Pessoa pessoa = pessoaRepository.findOne(chave.getPessoa().getCodigo());
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return chaveRepository.save(chave);
	}
	
	public Chave atualizar(Long codigo, Chave chave) {
		Chave chaveSalvo = buscarChaveExistente(codigo);
		if (!chave.getPessoa().equals(chaveSalvo.getPessoa())) {
			validarPessoa(chave);
		}

		BeanUtils.copyProperties(chave, chaveSalvo, "codigo");

		return chaveRepository.save(chaveSalvo);
	}

	private void validarPessoa(Chave chave) {
		Pessoa pessoa = null;
		if (chave.getPessoa().getCodigo() != null) {
			pessoa = pessoaRepository.findOne(chave.getPessoa().getCodigo());
		}

		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}

	private Chave buscarChaveExistente(Long codigo) {
		Chave chaveSalvo = chaveRepository.findOne(codigo);
		if (chaveSalvo == null) {
			throw new IllegalArgumentException();
		}
		return chaveSalvo;
	}
}
