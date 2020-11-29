package br.com.certisign.servicecertimed.webservicesmv;

public class CertimedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8297714235755603650L;
	private int id = 0;

	public CertimedException(int id) {
		setId(id);
	}

	public CertimedException(String message, int id) {
		super(message);
		setId(id);
	}

	public CertimedException(Throwable cause, int id) {
		super(cause);
		setId(id);
	}

	public CertimedException(String message, Throwable cause, int id) {
		super(message, cause);
		setId(id);
	}

	public CertimedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int id) {
		super(message, cause, enableSuppression, writableStackTrace);
		setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
