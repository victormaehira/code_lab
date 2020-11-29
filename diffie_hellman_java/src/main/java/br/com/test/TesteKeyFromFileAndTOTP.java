package br.com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Mac;

import br.com.test.crypto.DHimpl;

public class TesteKeyFromFileAndTOTP {
	public static void main(String[] args) throws KeyStoreException, IOException {
		File file = new File("C:\\ProgramData\\desktopID\\keystore.txt.back");
		byte[] secretKey = readFileToByteArray(file);
		
		System.out.println("Arrays.toString(bytes) = " + Arrays.toString(secretKey));
		System.out.println("secretKey.length = " + secretKey.length);
		
		DHimpl dhimpl = new DHimpl();
		dhimpl.setSecretKey("teste", secretKey);
		
		InputStream fileInputStream = new FileInputStream("teste.jks");
		try {
			KeyStore keyStore = KeyStore.getInstance("pkcs12");
			keyStore.load(fileInputStream, "changeit".toCharArray());

			KeyStore.Entry entry = keyStore.getEntry("teste",
					new KeyStore.PasswordProtection("changeit".toCharArray()));
			// SecretKeyEntry secretKeyEntry = (SecretKeyEntry) keyStore.getEntry("teste",
			// new KeyStore.PasswordProtection("changeit".toCharArray()));
			if (entry == null) {
				System.out.println("entry null");
			}
			if (!(entry instanceof KeyStore.SecretKeyEntry)) {
				// throw log.wrongTypeOfExternalStorageKey(keyAlias);
				System.out.println("entry not instanceof secretKeyEntry");
			}
			//Key storageSecretKey = ((KeyStore.SecretKeyEntry) entry).getSecretKey();
			Key secretKeyEntry = ((KeyStore.SecretKeyEntry) entry).getSecretKey();

			if (secretKeyEntry == null) {
				System.out.println("secretKeyEntry == null");
			} else {
				System.out.println("secretKeyEntry != null");
			}

			//byte[] currentCounter = getCurrentCounterAndUsedTime();
			
			byte[] currentCounter = {0, 0, 0, 0, 0, 41, 48, 7};
			
			System.out.println("currentCounter = " + Arrays.toString(currentCounter));
			
			byte[] authCode = hmac(secretKeyEntry, currentCounter);
			
			System.out.println("authCode.length = " + authCode.length);
			
			String totp = dynamicTruncate(authCode);
			System.out.println("totp = " + totp);

		} catch (Exception e) {
			System.out.println("Erro ao carregar keystore");
			e.printStackTrace();
		} finally {
			fileInputStream.close();
		}
		System.out.println("Fim!");
	}

	private static byte[] readFileToByteArray(File file) {
		FileInputStream fis = null;
		// Creating a byte array using the length of the file
		// file.length returns long which is cast to int
		byte[] bArray = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(bArray);
			fis.close();

		} catch (IOException ioExp) {
			ioExp.printStackTrace();
		}
		return bArray;
	}
	
	private static byte[] getCurrentCounterAndUsedTime() {
		long now = new java.util.Date().getTime();

		long millisecondsSinceBeginning = (now - Long.parseLong("1512151500000"));
		long millisecondsToStepBy = 30 * 1000;
		long counterAsNumber = millisecondsSinceBeginning / millisecondsToStepBy;

		// transforma a data em array de 8 bytes
		ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE / 8);
		buffer.order(ByteOrder.BIG_ENDIAN);
		buffer.putLong(counterAsNumber);
		buffer.flip();
		byte[] counterAsByteArray = buffer.array();
		return counterAsByteArray;
	}

	private static byte[] hmac(Key key, byte[] data) {
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(key);

			return mac.doFinal(data);
		} catch (InvalidKeyException e) {
			throw new RuntimeException("BUG", e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("BUG", e);
		} catch (IllegalStateException e) {
			throw new RuntimeException("BUG", e);
		}
	}

	private static String dynamicTruncate(byte[] hmacResult) {
		if (hmacResult.length != 20) {
			throw new UnsupportedOperationException("Atualmente, so se pode truncar hashes gerados por SHA-1");
		}

		int offset = hmacResult[19] & 0xf;

		int binCode = (hmacResult[offset] & 0x7f) << 24 | (hmacResult[offset + 1] & 0xff) << 16
				| (hmacResult[offset + 2] & 0xff) << 8 | (hmacResult[offset + 3] & 0xff);

		// short digits = totpConfiguration.getDigits();
		short digits = 6;

		int otpAsNumber = binCode % (int) Math.pow(10, digits);
		return String.format("%0" + digits + "d", otpAsNumber);
	}
}
