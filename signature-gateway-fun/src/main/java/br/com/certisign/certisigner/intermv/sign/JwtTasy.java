package br.com.certisign.certisigner.intermv.sign;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERInteger;
//import org.bouncycastle.openssl.PEMUtilities;
import org.bouncycastle.openssl.PasswordFinder;
import org.bouncycastle.util.encoders.Hex;
//import org.jboss.logging.Logger;
import org.json.JSONObject;

import com.microsoft.azure.functions.ExecutionContext;

import br.com.certisign.certisigner.pem.PEMReader;
import br.com.certisign.certisigner.pem.PEMUtilities;
import br.com.certisign.servicecertimed.webservicesmv.CertimedException;

public class JwtTasy {
	
	public static String createJwt(String kpName, char[] kpPassowrd, String organization, String system, ExecutionContext context) throws CertimedException {
		String jwt = "";
		
		try {
			JSONObject headerJson = new JSONObject();
			headerJson.put("alg","RS256");
			
			Calendar calendar = Calendar.getInstance();
			String iat = String.valueOf(calendar.getTimeInMillis()).substring(0, 10);
			
			calendar.add(Calendar.MINUTE, 35);
			String exp = String.valueOf(calendar.getTimeInMillis()).substring(0, 10);
			
			JSONObject payloadJson = new JSONObject();
			payloadJson.put("iss", system + "@" + organization);
			payloadJson.put("iat", iat);
			payloadJson.put("exp", exp);
			
			String header = Base64.getEncoder().encodeToString(headerJson.toString().getBytes("UTF-8"));
			String payload = Base64.getEncoder().encodeToString(payloadJson.toString().getBytes("UTF-8"));
			
			String messageString = header + "." + payload;
			
			//String keyPath = ConfigCloud.getInstance().getPathPrefix() + kpName + ".pk";
//            if (kpName == null || "".equals(kpName)) {
//                keyPath = ConfigCloud.getInstance().getPathKp();
//            }
			
			//KeyPair pair = readKeyPair(new File(keyPath), kpPassowrd);
			//String kp = System.getenv(kpName);
			//String kp = "G8VEtU21B31oHFibJM5hXbl9s9V2c4ZTXAUcTDhlEdU0H15UGehQ6BFBZegrpeNXVlm7bEvERnTbGazlT+mBlwQuKQd+EM0MIMEmycziPsHiLFu5noAbVo15QcgCiRHLquOdIOet7XUheB5IYtVzMuS+ISwV4+nWNAP1xrayytuAlDaNx4orEb+rTniaT793pJWfK4GZGUkn8vAbuw49rat6oyomYRJVBVUJlpfyA1xSc+k0GIkqTqEYYxkCKlYYOww5eLeCERsWlWhON/n0poIwPZf5RMDcSM0T0R6Bb/+D2lC88jOhrfierqjm73FRXK2ilaXTpEKxmN5De4Zxugw/EF7VXOhfLfj9ZRQ7nsGn9JJGkQA6SYGYaRW2N1xonwYd1TV1b4/nfYF+H1X7Pxk0u6lmCHIJmdDTeXfak62L1ecvp+vqefRbh/JOgA+D+QbzwwWAcBiPeYXm6c/g1E5su0duJeIV3EnekkpUMyRpuvvKWjHpdgUY8tpGrIHtPa2+Jb6LAiVZxQpenkP6gnnxiN1Nf33ibNOudUvzLZK/omB/AJSdPx4947Pm3yB+oQfAf8KI+3/LUcuEqTZgc58xKXUvZELaZgUNLEfvUCzx7kUVHHHVsydkJKB+n5kfz3krq0/mYG2SMP9L9BxzaXHd69mqDJ7ZrqO2lQGs33oq8en4S3362UcBpXN0H8NZNK1ugU/cqcDSG0UofxjDKjWGKqckEBQmDm+0HbuPDWjzu7ntmt+ldVAvNvlykwjvTCLwPiqVoeRo9KcBo1Nu0AP1hNgwozBYcQ45M3OpmZcElwNk/5wkZM6T9RBE+QIVc18H09BTagu1THXubJQgb0ZXdPtoumHJHiXJggOWHGPanBwfE/1gnin5GugUvAvkkhN5ffjVRDFcEekXVqGWKudWyemV4kMAJzgAOmMRT3kVTOQIqwD5ytU7eBk8M7rzWh7UVTtMMW37TA8GmGfj8gSRByw50UAjhgefZmL2Miz6gkd93bj1v/x4M22CVYcIJG542arZtfpE0ZYwYK4rPFSf+X855m52rGt9YYl/uO6LwNA+Q2MwH4EPwWc2SxKB3u4+qkTwe9QJO2Ip2ojzDaxlSVBZcASktjBrVm2jeFqIIstYqSNFZK5VDkHbsrXPbsLIcuyxRsexTzO0pxfIMoi+84/4GjspXnbtNeM+S2GAPBFUBCITSUp9GzFoD5RSXownxuSpZtOMhgNb8bj45TfULvwDASkUiNMj5WzztHydKQSeqZm18HjLyPtNDrn+DxMuzS2qdAH4BaT2JAxGgRgCNqUUVXhR+rVbs8fXbgXi+KSHvHGhYfwCjSZc0v4jTCt4Ag8jcMkv9LdTLeNX0BdFkWsSg5+5j7Hi+vYblkN2NzAzKBNRdgNYjCjCHxj+gTneRGsSFO6sJrwJBD6qAxkOon72dleSRK79g+qxkAdOb9s6RdWIxOFhmaNi35IGlNL1oXE2r32Bw7rO00rolX3P+bzIzif9ijh3MKlWsHYfRNPZ0ds2VReOVBdGmQLQCcKrde5T6zbCUEE8Dp1uJS+AHoXINOlEG/61GQANEvwXclwhoGXjNg==";
			String kp = "G8VEtU21B31oHFibJM5hXbl9s9V2c4ZTXAUcTDhlEdU0H15UGehQ6BFBZegrpeNX\r\n" + 
					"Vlm7bEvERnTbGazlT+mBlwQuKQd+EM0MIMEmycziPsHiLFu5noAbVo15QcgCiRHL\r\n" + 
					"quOdIOet7XUheB5IYtVzMuS+ISwV4+nWNAP1xrayytuAlDaNx4orEb+rTniaT793\r\n" + 
					"pJWfK4GZGUkn8vAbuw49rat6oyomYRJVBVUJlpfyA1xSc+k0GIkqTqEYYxkCKlYY\r\n" + 
					"Oww5eLeCERsWlWhON/n0poIwPZf5RMDcSM0T0R6Bb/+D2lC88jOhrfierqjm73FR\r\n" + 
					"XK2ilaXTpEKxmN5De4Zxugw/EF7VXOhfLfj9ZRQ7nsGn9JJGkQA6SYGYaRW2N1xo\r\n" + 
					"nwYd1TV1b4/nfYF+H1X7Pxk0u6lmCHIJmdDTeXfak62L1ecvp+vqefRbh/JOgA+D\r\n" + 
					"+QbzwwWAcBiPeYXm6c/g1E5su0duJeIV3EnekkpUMyRpuvvKWjHpdgUY8tpGrIHt\r\n" + 
					"Pa2+Jb6LAiVZxQpenkP6gnnxiN1Nf33ibNOudUvzLZK/omB/AJSdPx4947Pm3yB+\r\n" + 
					"oQfAf8KI+3/LUcuEqTZgc58xKXUvZELaZgUNLEfvUCzx7kUVHHHVsydkJKB+n5kf\r\n" + 
					"z3krq0/mYG2SMP9L9BxzaXHd69mqDJ7ZrqO2lQGs33oq8en4S3362UcBpXN0H8NZ\r\n" + 
					"NK1ugU/cqcDSG0UofxjDKjWGKqckEBQmDm+0HbuPDWjzu7ntmt+ldVAvNvlykwjv\r\n" + 
					"TCLwPiqVoeRo9KcBo1Nu0AP1hNgwozBYcQ45M3OpmZcElwNk/5wkZM6T9RBE+QIV\r\n" + 
					"c18H09BTagu1THXubJQgb0ZXdPtoumHJHiXJggOWHGPanBwfE/1gnin5GugUvAvk\r\n" + 
					"khN5ffjVRDFcEekXVqGWKudWyemV4kMAJzgAOmMRT3kVTOQIqwD5ytU7eBk8M7rz\r\n" + 
					"Wh7UVTtMMW37TA8GmGfj8gSRByw50UAjhgefZmL2Miz6gkd93bj1v/x4M22CVYcI\r\n" + 
					"JG542arZtfpE0ZYwYK4rPFSf+X855m52rGt9YYl/uO6LwNA+Q2MwH4EPwWc2SxKB\r\n" + 
					"3u4+qkTwe9QJO2Ip2ojzDaxlSVBZcASktjBrVm2jeFqIIstYqSNFZK5VDkHbsrXP\r\n" + 
					"bsLIcuyxRsexTzO0pxfIMoi+84/4GjspXnbtNeM+S2GAPBFUBCITSUp9GzFoD5RS\r\n" + 
					"XownxuSpZtOMhgNb8bj45TfULvwDASkUiNMj5WzztHydKQSeqZm18HjLyPtNDrn+\r\n" + 
					"DxMuzS2qdAH4BaT2JAxGgRgCNqUUVXhR+rVbs8fXbgXi+KSHvHGhYfwCjSZc0v4j\r\n" + 
					"TCt4Ag8jcMkv9LdTLeNX0BdFkWsSg5+5j7Hi+vYblkN2NzAzKBNRdgNYjCjCHxj+\r\n" + 
					"gTneRGsSFO6sJrwJBD6qAxkOon72dleSRK79g+qxkAdOb9s6RdWIxOFhmaNi35IG\r\n" + 
					"lNL1oXE2r32Bw7rO00rolX3P+bzIzif9ijh3MKlWsHYfRNPZ0ds2VReOVBdGmQLQ\r\n" + 
					"CcKrde5T6zbCUEE8Dp1uJS+AHoXINOlEG/61GQANEvwXclwhoGXjNg==";
			
			context.getLogger().info("CreateJwt kpName = " + kpName);
			context.getLogger().info("CreateJwt kp = " + kp);
			context.getLogger().info("CreateJwt kpPassword = " + new String(kpPassowrd));
			
			KeyPair pair = readKeyPair(kp, kpPassowrd, context);
			context.getLogger().info("CreateJwt executou KeyPair pair = readKeyPair(kp, kpPassowrd, context)");
			RSAPrivateKey priv = (RSAPrivateKey) pair.getPrivate();
			
	        Signature signature = Signature.getInstance("SHA256withRSA");
	        signature.initSign(priv);
	        signature.update(messageString.getBytes("UTF-8"));
	        byte[] sigBytes = signature.sign();
			
			String assinatura = Base64.getEncoder().encodeToString(sigBytes);
			jwt = messageString + "." + assinatura;
		} catch (Exception e) {
			//LOGGER.error(e);
			throw SignUtil.TrataDisparoErro(e, "Nao foi possivel criar o token.", 811);
		}
		
		return jwt; 
	}
	
	//private static KeyPair readKeyPair(File privateKey, char[] kpPassword) throws Exception {
	private static KeyPair readKeyPair(String privateKey, char[] kpPassword, ExecutionContext context) throws Exception {

		PasswordFinder passowordKp = new PasswordFinder() {
			@Override
			public char[] getPassword() {
				try {
					return kpPassword;
				} catch (Exception e) {
					//LOGGER.error(e);
					context.getLogger().info("Erro no getPassword");
				}
				return null; 								
			}
		};
		
		//FileReader fileReader = new FileReader(privateKey);
		context.getLogger().info("readKeyPair fileReader");
	    Reader fileReader = new StringReader(privateKey);
	    context.getLogger().info("readKeyPair fileReader loaded");
	    PEMReader pemReader = new PEMReader(fileReader, passowordKp);
	    context.getLogger().info("readKeyPair pemReader loaded");
	    try {
	    	context.getLogger().info("readKeyPair return readKeyPair");
	        //return (KeyPair) pemReader.readObject();
	        return readKeyPair("RSA", "-----END RSA PRIVATE KEY", kpPassword, privateKey, context);
	    } catch (Exception ex) {
	    	//LOGGER.error(ex);
	    	throw SignUtil.TrataDisparoErro(ex, "Erro ao criar token, kp nao encontrada ou senha invalida.", 812);
	    } finally {
	    	pemReader.close();
	        fileReader.close();
	    }
	}
	
	private static KeyPair readKeyPair(String type, String endMarker, char[] kpPassword, String privateKey, ExecutionContext context)
            throws Exception {
        boolean isEncrypted = false;
        String line = null;
        String dekInfo = null;
        StringBuffer buf = new StringBuffer();
        String provider = "BC";

//        while ((line = readLine()) != null) {
//            if (line.startsWith("Proc-Type: 4,ENCRYPTED")) {
//                isEncrypted = true;
//            } else if (line.startsWith("DEK-Info:")) {
//                dekInfo = line.substring(10);
//            } else if (line.indexOf(endMarker) != -1) {
//                break;
//            } else {
//                buf.append(line.trim());
//            }
//        }
        
        isEncrypted = true;

        //
        // extract the key
        //
        //byte[] keyBytes = org.bouncycastle.util.encoders.Base64.decode(buf.toString());
        context.getLogger().info("readKeyPair decode PrivateKey");
        byte[] keyBytes = org.bouncycastle.util.encoders.Base64.decode(privateKey);
        context.getLogger().info("readKeyPair decoded ok");
        
        if (isEncrypted) {
//            if (pFinder == null) {
//                throw new IOException(
//                        "No password finder specified, but a password is required");
//            }

            //char[] password = pFinder.getPassword();
            char[] password = kpPassword;

            if (password == null) {
                throw new IOException(
                        "Password is null, but a password is required");
            }

            //StringTokenizer tknz = new StringTokenizer(dekInfo, ",");
            //String dekAlgName = tknz.nextToken();
            
            String dekAlgName = "DES-EDE3-CBC";
            //byte[] iv = Hex.decode(tknz.nextToken());
            context.getLogger().info("readKeyPair decoded Hex");
            byte[] iv = Hex.decode("5C51AF79F4EB8A0F");
            

            context.getLogger().info("readKeyPair crypt");
            keyBytes =  PEMUtilities.crypt(false, provider, keyBytes,
                    password, dekAlgName, iv);
        }
        
        KeySpec pubSpec, privSpec;
        context.getLogger().info("readKeyPair ByteArrayInputStream bIn = new ByteArrayInputStream(keyBytes)");
        ByteArrayInputStream bIn = new ByteArrayInputStream(keyBytes);
        context.getLogger().info("readKeyPair ASN1InputStream aIn = new ASN1InputStream(bIn)");
        ASN1InputStream aIn = new ASN1InputStream(bIn);
        context.getLogger().info("readKeyPair ASN1Sequence seq = (ASN1Sequence) aIn.readObject()");
        ASN1Sequence seq = (ASN1Sequence) aIn.readObject();

        if (type.equals("RSA")) {
            //            DERInteger              v = (DERInteger)seq.getObjectAt(0);
            DERInteger mod = (DERInteger) seq.getObjectAt(1);
            DERInteger pubExp = (DERInteger) seq.getObjectAt(2);
            DERInteger privExp = (DERInteger) seq.getObjectAt(3);
            DERInteger p1 = (DERInteger) seq.getObjectAt(4);
            DERInteger p2 = (DERInteger) seq.getObjectAt(5);
            DERInteger exp1 = (DERInteger) seq.getObjectAt(6);
            DERInteger exp2 = (DERInteger) seq.getObjectAt(7);
            DERInteger crtCoef = (DERInteger) seq.getObjectAt(8);

            pubSpec = new RSAPublicKeySpec(mod.getValue(),
                    pubExp.getValue());
            privSpec = new RSAPrivateCrtKeySpec(mod.getValue(),
                    pubExp.getValue(), privExp.getValue(), p1.getValue(),
                    p2.getValue(), exp1.getValue(), exp2.getValue(),
                    crtCoef.getValue());
        } else // "DSA"
        {
            //            DERInteger              v = (DERInteger)seq.getObjectAt(0);
            DERInteger p = (DERInteger) seq.getObjectAt(1);
            DERInteger q = (DERInteger) seq.getObjectAt(2);
            DERInteger g = (DERInteger) seq.getObjectAt(3);
            DERInteger y = (DERInteger) seq.getObjectAt(4);
            DERInteger x = (DERInteger) seq.getObjectAt(5);

            privSpec = new DSAPrivateKeySpec(x.getValue(), p.getValue(),
                    q.getValue(), g.getValue());
            pubSpec = new DSAPublicKeySpec(y.getValue(), p.getValue(),
                    q.getValue(), g.getValue());
        }

        context.getLogger().info("readKeyPair KeyFactory fact = KeyFactory.getInstance(type, provider)");
        KeyFactory fact = KeyFactory.getInstance(type, provider);

        return new KeyPair(fact.generatePublic(pubSpec),
                fact.generatePrivate(privSpec));
    }    
}