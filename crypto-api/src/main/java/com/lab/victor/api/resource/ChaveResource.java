package com.lab.victor.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lab.victor.api.event.RecursoCriadoEvent;
import com.lab.victor.api.exceptionhandler.CryptoExceptionHandler.Erro;
import com.lab.victor.api.model.Chave;
import com.lab.victor.api.repository.ChaveRepository;
import com.lab.victor.api.repository.filter.ChaveFilter;
import com.lab.victor.api.repository.projection.ResumoChave;
import com.lab.victor.api.service.ChaveService;
import com.lab.victor.api.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/chaves")
public class ChaveResource {

	@Autowired
	private ChaveRepository chaveRepository;
	
	@Autowired
	private ChaveService chaveService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	/*
	@GetMapping
	public List<Chave> listar() {
		return chaveRepository.findAll();
	}*/
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<Chave> pesquisar(ChaveFilter chaveFilter, Pageable pageable) {
		return chaveRepository.filtrar(chaveFilter, pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<ResumoChave> resumir(ChaveFilter chaveFilter, Pageable pageable) {
		return chaveRepository.resumir(chaveFilter, pageable);
	}
	
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Chave> buscarPeloCodigo(@PathVariable Long codigo) {
		Chave chave = chaveRepository.findOne(codigo);
		return chave != null ? ResponseEntity.ok(chave) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Chave> criar (@Valid @RequestBody Chave chave, HttpServletResponse response) {
		Chave chaveSalvo = chaveService.salvar(chave);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, chaveSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(chaveSalvo);
		
	}
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		chaveRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO')")
	public ResponseEntity<Chave> atualizar(@PathVariable Long codigo, @Valid @RequestBody Chave lancamento) {
		try {
			Chave chaveSalvo = chaveService.atualizar(codigo, lancamento);
			return ResponseEntity.ok(chaveSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
