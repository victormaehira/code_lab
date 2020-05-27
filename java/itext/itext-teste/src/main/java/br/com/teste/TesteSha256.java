package br.com.teste;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.encoders.Base64;

public class TesteSha256 {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		byte[] bFile = Files.readAllBytes(Paths.get("./atestado_medico_oid_preenchido.pdf"));
		//byte[] bFile = Files.readAllBytes(Paths.get("./temp.pdf"));
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(bFile);
	
		 //SALVAR HASH EM ARQUIVO
        Files.write(Paths.get("./base64_test.txt"), Base64.encode(encodedhash));
        System.out.println("base64 = " + new String(Base64.encode(encodedhash)));
        System.out.println("hexa = " + bytesToHex(encodedhash));
        System.out.println("Done");
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
}
