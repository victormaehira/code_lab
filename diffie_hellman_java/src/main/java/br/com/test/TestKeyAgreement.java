package br.com.test;

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.KeyAgreement;

import br.com.test.crypto.DHimpl;

public class TestKeyAgreement {
	public static void main(String[] args) throws Exception {
		DHimpl dhimpl = new DHimpl();
		//GENKEY A
		KeyPair keyPairA = dhimpl.createSpecificKey();
		PublicKey pubKeyA = keyPairA.getPublic();

		//GENKEY B
		KeyPair keyPairB = dhimpl.createSpecificKey();
		PublicKey pubKeyB = keyPairB.getPublic();
		
		String pubKeyABase64 = Base64.getEncoder().encodeToString(pubKeyA.getEncoded());
		String pubKeyBBase64 = Base64.getEncoder().encodeToString(pubKeyB.getEncoded());
		
		System.out.println("pubKeyABase64 = " + pubKeyABase64);
		System.out.println("pubKeyBBase64 = " + pubKeyBBase64);

		//Key Agreement
		KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
		keyAgreement.init(keyPairA.getPrivate());
		keyAgreement.doPhase(pubKeyB, true);
		
		byte[] largeSecretKey = keyAgreement.generateSecret();
		
		byte[] secretKey = dhimpl.extractToSmallKey(largeSecretKey);

		if (secretKey != null) {
			System.out.println("secret key size = " + secretKey.length);
		}
		System.out.println("Gerou secretKey!");
		//keytool -keystore teste.jks -storetype PKCS12 -list
		dhimpl.setSecretKey("teste", secretKey);
		System.out.println("Done!");
	}

}
