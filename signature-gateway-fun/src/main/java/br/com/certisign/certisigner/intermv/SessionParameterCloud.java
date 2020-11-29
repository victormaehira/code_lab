package br.com.certisign.certisigner.intermv;

public class SessionParameterCloud {
	
	
	private String pin;
	private String otp;
	private String email;
	private String cpf;
	private String idSistema;
	private String issue;
	private String serialNumber;
	private String tokenSessao;
	private String algorithm;
	
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public String getTokenSessao() {
		return tokenSessao;
	}
	public void setTokenSessao(String tokenSessao) {
		this.tokenSessao = tokenSessao;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	

}
