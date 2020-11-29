package br.com.certisign.mv.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import br.com.certisign.servicecertimed.webservicesmv.CertimedException;

public class ConfigCloud {
	private static ConfigCloud instance;
	private Properties config;
	private String pathKp;
	private String proxyAddress;
	private String proxyPort;
	private String proxyUsername;
	private String proxyPassword;
	private String pathPrefix;

	private ConfigCloud() throws FileNotFoundException, IOException, CertimedException {
		
		String configFolder = System.getenv("CERTISIGNER_RESOURCE");
		if (configFolder == null)
			throw new CertimedException("Favor configurar a variavel de ambiente CERTISIGNER_RESOURCE com o caminho da pasta de configuracão!",800);
		
		//carrega parametros 
		config = new Properties();
		config.load(new FileInputStream(new File(configFolder + "/CERTISIGNER_CONFIG/general.properties")));
		pathKp = config.getProperty("certimed.key.pathKp");
		proxyAddress = config.getProperty("proxy.host");
		proxyPort = config.getProperty("proxy.port");
		proxyUsername = config.getProperty("proxy.user");
		proxyPassword = config.getProperty("proxy.password");
		pathPrefix = config.getProperty("certimed.key.pathPrefix");

	}

	public static ConfigCloud getInstance() throws FileNotFoundException, IOException, CertimedException {
		if (instance == null)
			instance = new ConfigCloud();
		
		return instance;
	}

	public Properties getConfiguration() {
		return this.config;
	}
	
	public String getPathKp() {
		return this.pathKp;
	}

	public String getProxyAddress() {
		return this.proxyAddress;
	}
	
	public String getProxyPort() {
		return this.proxyPort;
	}
	
	public String getProxyUsername() {
		return this.proxyUsername;
	}
	
	public String getProxyPassword() {
		return this.proxyPassword;
	}
	public String getPathPrefix() {
        return this.pathPrefix;
    }
}