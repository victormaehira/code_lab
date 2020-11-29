package br.com.test;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.KeyAgreement;

import org.json.JSONObject;

import br.com.test.crypto.DHimpl;
import br.com.test.util.Post;

public class TestGenKeyAndPostAndSaveToKeystore {

	

	public static void main(String[] args) throws Exception {
		// GENKEY
		DHimpl dhimpl = new DHimpl();
		KeyPair keyPair = dhimpl.createSpecificKey();
		PublicKey pubKey = keyPair.getPublic();
		String pubKeyBase64 = Base64.getEncoder().encodeToString(pubKey.getEncoded());

		// POST
		String json = montaJsonToPost("R8UIV", pubKeyBase64);
		Post post = new Post();
		String resposta = post.postMessageWithHost("https://certinext-homolog.certisign.com.br/",
				"CertisignerServices/totp/payload", json);

		System.out.println("resposta = " + resposta);

		JSONObject jsonResposta = new JSONObject(resposta);
		String serverDhPublicKey = jsonResposta.getString("serverDhPublicKey");

		System.out.println("serverDhPublicKey = " + serverDhPublicKey);

		KeyFactory kf = KeyFactory.getInstance("DH");
		byte pubKeyOther[] = Base64.getDecoder().decode(serverDhPublicKey);
		X509EncodedKeySpec pksOther = new X509EncodedKeySpec(pubKeyOther);
		PublicKey publicKeyOther = kf.generatePublic(pksOther);

		// KEY AGREEMENT
		KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
		keyAgreement.init(keyPair.getPrivate());
		keyAgreement.doPhase(publicKeyOther, true);

		byte[] largeSecretKey = keyAgreement.generateSecret();
		byte[] secretKey = dhimpl.extractToSmallKey(largeSecretKey);

		if (secretKey != null) {
			System.out.println("secret key size = " + secretKey.length);
		}
		System.out.println("Gerou secretKey!");
		dhimpl.setSecretKey("teste", secretKey);
		System.out.println("Done!");
	}

	private static String montaJsonToPost(String senhaTemporaria, String pubKeyBase64) {
		String json = "{" + "\"temporaryPassCode\": \"" + senhaTemporaria + "\"," + "\"clientDhPublicKey\": \""
				+ pubKeyBase64 + "\"," + "}";
		return json;
	}

	
}
