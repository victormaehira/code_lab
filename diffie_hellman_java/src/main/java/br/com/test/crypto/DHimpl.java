package br.com.test.crypto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStoreException;

import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.generators.HKDFBytesGenerator;
import org.bouncycastle.crypto.params.HKDFParameters;

public class DHimpl {
	private BigInteger p = new BigInteger(
			"C199D1410687CAB5048657995C7490864871385EBA37027E6B9441E02E54ACD276CEC267619BB2E0330535EF6704064117C00D37043D14E1EBC0A5F03E719A7BF2916CC6A4D27370AC5697A5F30561414A179C7FBF95D3D720C534E1C6440128416C25C75917F1A413E4EEF48AE5A392A5FF705D733D0189F658C774D9C10E89A0C879795078ABAACEBE6F76712897A9EA8B91C5005C11242ACD57CBA465760B801C4A71509B76CC3010CFCC11FEC90899A019087AB760630E96C23810B2B80E4402A6D49591F31B2FE70F46F85F9CF4C439E3D83B621C5FF49A47ADC011EE6F721C6711ADE8A9930DF69F705D63C1FB346A52BE6B17169B7AD47B5DFD2F1F63",
			16);
	private BigInteger g = new BigInteger("02", 16);
	private static byte[] DEFAULT_SALT = { (byte) 0xb5, 0x26, (byte) 0x8c, 0x16, 0x55, 0x5c, 0x1c, (byte) 0xa6, 0x64,
			0x6a, (byte) 0xa5, (byte) 0x86, 0x71, 0x59, (byte) 0xb8, 0x2c, 0x63, 0x22, (byte) 0xb7, (byte) 0xe2,
			(byte) 0xe3, (byte) 0xc9, 0x23, (byte) 0x98, (byte) 0xb7, (byte) 0xf8, 0x1a, 0x79, (byte) 0x9e, 0x3e,
			(byte) 0xea, (byte) 0xd6 };
	public KeyPair createSpecificKey() throws Exception {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("DiffieHellman");

		DHParameterSpec param = new DHParameterSpec(p, g);
		kpg.initialize(param);
		KeyPair kp = kpg.generateKeyPair();
	
		//System.out.println("P = " + p.toString());
		//System.out.println("G = " + g.intValue());
		
		return kp;
	}
	
	public byte[] extractToSmallKey(byte[] largeSecretKey) {
		SHA1Digest digest = new SHA1Digest();
		System.out.println("digest.getDigestSize() = " + digest.getDigestSize());
		HKDFBytesGenerator hkdf = new HKDFBytesGenerator(digest);
		hkdf.init(new HKDFParameters(largeSecretKey, DEFAULT_SALT, null));
		byte[] aesKey = new byte[digest.getDigestSize()];
		hkdf.generateBytes(aesKey, 0, aesKey.length);
		return aesKey;
	}

	public void setSecretKey(String alias, byte[] secretKey) throws KeyStoreException, IOException {
		//KeyStore keyStore = KeyStore.getInstance("JCEKS"); 
		KeyStore keyStore = KeyStore.getInstance("pkcs12");
		OutputStream fileOutputStream = new FileOutputStream("teste.jks");
		try {
			keyStore.load(null,null);
			SecretKeySpec key = new SecretKeySpec(secretKey, "HmacSHA1");
			SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(key);
			PasswordProtection pwd = new KeyStore.PasswordProtection("changeit".toCharArray());
			//keyStore.setEntry(alias, skEntry, pwd);
			keyStore.setEntry(alias, skEntry, pwd);
			keyStore.store(fileOutputStream, "changeit".toCharArray());
		} catch (Exception e) {
			fileOutputStream.close();
		}
	}
}
