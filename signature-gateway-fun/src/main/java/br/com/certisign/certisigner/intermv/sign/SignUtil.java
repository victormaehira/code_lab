package br.com.certisign.certisigner.intermv.sign;

import br.com.certisign.servicecertimed.webservicesmv.CertimedException;

/**
 * Classe responsavel por metodos Utils para assiantura
 * 
 * @author ricardo.andrade
 *
 */

public class SignUtil {

	public static CertimedException TrataDisparoErro(Exception e, String mensagem, int id) throws CertimedException {
		if (e instanceof CertimedException) {
			CertimedException excessao = (CertimedException) e;
			return new CertimedException(excessao.getMessage(), excessao.getId());
		} else {
			//LOGGER.error(e);
			return new CertimedException(mensagem, id);
		}
	}
}