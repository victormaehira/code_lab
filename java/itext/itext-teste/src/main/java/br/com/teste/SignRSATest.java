package br.com.teste;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Base64;

public class SignRSATest {
	public static void main(String[] args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException, UnrecoverableKeyException, InvalidKeyException, SignatureException {
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		ks.load(new FileInputStream("./27709797873_05_11_2020.pfx"), "12345678".toCharArray());
		String alias = ks.aliases().nextElement();
		PrivateKey pk = (PrivateKey) ks.getKey(alias, "12345678".toCharArray());
		
		String base64Hash = "1Orqp6RxWN6Nx7oQLybFGwC5vfH4cNFwhnZ7VXHrNBU=";
		
		Signature signature = Signature.getInstance("SHA256WithRSA");
		signature.initSign(pk);
		signature.update(Base64.getDecoder().decode(base64Hash.getBytes())); 
		System.out.println("assinaturaBase64 = " + new String(Base64.getEncoder().encode(signature.sign())));
	}
}
