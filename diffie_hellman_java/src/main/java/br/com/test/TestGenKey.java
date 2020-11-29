package br.com.test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import br.com.test.crypto.DHimpl;

public class TestGenKey {
	public static void main(String[] args) throws Exception {
		DHimpl dhimpl = new DHimpl();
		KeyPair keyPair = dhimpl.createSpecificKey();
				
		PublicKey pubKey = keyPair.getPublic();
		PrivateKey privKey = keyPair.getPrivate();
		String pubKeyBase64 = Base64.getEncoder().encodeToString(pubKey.getEncoded());
		String privKeyBase64 = Base64.getEncoder().encodeToString(privKey.getEncoded());
		
		System.out.println("pubKey.getFormat() = " + pubKey.getFormat());
		System.out.println("pubKeyBase64 = " + pubKeyBase64);
		System.out.println("privKeyBase64 = " + privKeyBase64);
	
	}
	
}

