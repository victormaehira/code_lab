package br.com.test;

import java.io.IOException;

import br.com.test.util.Post;

public class TestPostJson {
	public static void main(String[] args) throws IOException {
		Post post = new Post();
		String json = "{" +
			    "\"temporaryPassCode\": \"758IB\"," +
			    "\"clientDhPublicKey\": \"MIICKTCCARsGCSqGSIb3DQEDATCCAQwCggEBAMGZ0UEGh8q1BIZXmVx0kIZIcTheujcCfmuUQeAuVKzSds7CZ2GbsuAzBTXvZwQGQRfADTcEPRTh68Cl8D5xmnvykWzGpNJzcKxWl6XzBWFBShecf7+V09cgxTThxkQBKEFsJcdZF/GkE+Tu9Irlo5Kl/3Bdcz0BifZYx3TZwQ6JoMh5eVB4q6rOvm92cSiXqeqLkcUAXBEkKs1Xy6RldguAHEpxUJt2zDAQz8wR/skImaAZCHq3YGMOlsI4ELK4DkQCptSVkfMbL+cPRvhfnPTEOePYO2IcX/SaR63AEe5vchxnEa3oqZMN9p9wXWPB+zRqUr5rFxabetR7Xf0vH2MCAQICAgQAA4IBBgACggEBAL4Dh1lB/JZFWfoimsoAYsrtOmXMVchDr0/R2quhhfsVneVZ6jT2qnJa+7nHHKKC0QbKLWEV8alVetrxYeLrJa/9dIA13FXvbIPEB1Tr8C4GmHJwYSr7Uct3y+tDl4LhdIwR//DmBUr99AYedmOjf1D7jsAc74yrA/VXfPJ4p4hKyLYIp2Sq+H7fw9Es5JxKTApHwb/QOr6wWV6jlTaDtrottlwzrvh+gteQrLoDjrWyn7RBPuHRX6Mtoc0O8+X7cQ6qSFdyFhyIE5npoZeIvOmts5CW9BHpaUjaDcWFcD2Y/4bIH2kEjcAH6kIbfNF4GxrwvdadmB7nGz1x2JE4YzA=\"" +
			"}";
		String resposta = post.postMessageWithHost("https://certinext-homolog.certisign.com.br/", "CertisignerServices/totp/payload", json);
		System.out.println("resposta = " + resposta);
	}
}
