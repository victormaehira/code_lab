package br.com.teste.sign_test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

public class App {
	public static void main(String[] args) throws Exception {
		KeyPair chaves = geraChaves();
		
		//para validar no openssl:
		// openssl rsa -pubin -in pubkey.bin -inform DER -outform PEM -out pubkey.pem
		// openssl dgst -sha256 -verify pubkey.pem -signature assinatura.bin teste.txt
		writeByte(chaves.getPublic().getEncoded(), "pubkey.bin");
		
		
		byte[] assinatura = assina(Files.readAllBytes(Paths.get("teste.txt")), chaves.getPrivate());
		writeByte(assinatura, "assinatura.bin");

		boolean isOk = valida(Files.readAllBytes(Paths.get("teste.txt")), assinatura, chaves.getPublic());
		System.out.println("Valida " + isOk);
	}

	public static KeyPair geraChaves() throws Exception {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048, new SecureRandom());
		KeyPair pair = generator.generateKeyPair();
		return pair;
	}

	public static byte[] assina(byte[] hash, PrivateKey privateKey)
			throws SignatureException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
		Signature privateSignature = Signature.getInstance("SHA256withRSA");
		privateSignature.initSign(privateKey);
		privateSignature.update(hash);
		return privateSignature.sign();
	}

	public static boolean valida(byte[] hash, byte[] signature, PublicKey publicKey) throws Exception {
		Signature publicSignature = Signature.getInstance("SHA256withRSA");
		publicSignature.initVerify(publicKey);
		publicSignature.update(hash);
		return publicSignature.verify(signature);
	}

	public static void writeByte(byte[] bytes, String fileName) {
		try {
			OutputStream os = new FileOutputStream(fileName);
			os.write(bytes);
			os.close();
		}
		catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
}
