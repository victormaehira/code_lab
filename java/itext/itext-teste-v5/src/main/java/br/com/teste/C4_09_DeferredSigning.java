package br.com.teste;
 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.Certificate;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.itextpdf.text.pdf.security.ExternalBlankSignatureContainer;
import com.itextpdf.text.pdf.security.ExternalSignatureContainer;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;
import com.itextpdf.text.pdf.security.PdfPKCS7;
import com.itextpdf.text.pdf.security.PrivateKeySignature;

//https://itextpdf.com/en/resources/examples/itext-5-legacy/digital-signatures-chapter-4
public class C4_09_DeferredSigning {
	public static final String DEST = "./atestado_medico_oid_preenchido_assinado_2020-05-26-20h56.pdf";
	 
    public static final String SRC = "./atestado_medico_oid_preenchido.pdf";
    public static final String TEMP = "./temp.pdf";
    public static final String KEYSTORE = "./27709797873_05_11_2020.pfx";
    
    public static final char[] PASSWORD = "12345678".toCharArray();
 
    class MyExternalSignatureContainer implements ExternalSignatureContainer {
 
        protected PrivateKey pk;
        protected Certificate[] chain;
        
        public MyExternalSignatureContainer(PrivateKey pk, Certificate[] chain) {
            this.pk = pk;
            this.chain = chain;
        }
        
        public byte[] sign(InputStream is) throws GeneralSecurityException {
            try {
                PrivateKeySignature signature = new PrivateKeySignature(pk, "SHA256", "BC");
                String hashAlgorithm = signature.getHashAlgorithm();
                BouncyCastleDigest digest = new BouncyCastleDigest();
                
                PdfPKCS7 sgn = new PdfPKCS7(null, chain, hashAlgorithm, null, digest, false);
                byte hash[] = DigestAlgorithms.digest(is, digest.getMessageDigest(hashAlgorithm));
                
                //Calendar cal = Calendar.getInstance();
                byte[] sh = sgn.getAuthenticatedAttributeBytes(hash, null, null, CryptoStandard.CMS);
                
                //teste assinatura
                Signature signatureJava = Signature.getInstance("SHA256WithRSA");
        		signatureJava.initSign(pk);
        		signatureJava.update(sh);
        		byte[] extSignature = signatureJava.sign();
        		
                sgn.setExternalDigest(extSignature, null, signature.getEncryptionAlgorithm());
                return sgn.getEncodedPKCS7(hash, null, null, null, CryptoStandard.CMS);
            }
            catch (IOException ioe) {
                throw new ExceptionConverter(ioe);
            }
        }
 
        public void modifySigningDictionary(PdfDictionary signDic) {
        }
        
    }
    
    public void emptySignature(String src, String dest, String fieldname, Certificate[] chain) throws IOException, DocumentException, GeneralSecurityException {
        PdfReader reader = new PdfReader(src);
        reader.unethicalreading = true;
        FileOutputStream os = new FileOutputStream(dest);
        PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setVisibleSignature(fieldname);
        appearance.setCertificate(chain[0]);
        ExternalSignatureContainer external = new ExternalBlankSignatureContainer(PdfName.ADOBE_PPKLITE, PdfName.ADBE_PKCS7_DETACHED);
        MakeSignature.signExternalContainer(appearance, external, 8192);
    }
    
    public void createSignature(String src, String dest, String fieldname, PrivateKey pk, Certificate[] chain) throws IOException, DocumentException, GeneralSecurityException {
        
        PdfReader reader = new PdfReader(src);
        reader.unethicalreading = true;
        FileOutputStream os = new FileOutputStream(dest);
        ExternalSignatureContainer external = new MyExternalSignatureContainer(pk, chain);
        MakeSignature.signDeferred(reader, fieldname, os, external);
    }
    
    public static void main(String[] args) throws IOException, GeneralSecurityException, DocumentException {
        BouncyCastleProvider providerBC = new BouncyCastleProvider();
        Security.addProvider(providerBC);
 
        // we load our private key from the key store
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(KEYSTORE), PASSWORD);
        String alias = (String)ks.aliases().nextElement();
        Certificate[] chain = ks.getCertificateChain(alias);
        
        System.out.println("Certificate chain.length " + chain.length);
        for (Certificate cert: chain) {
        	System.out.println("cert : " + cert.toString());
        }
        
        PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
        
        C4_09_DeferredSigning app = new C4_09_DeferredSigning();
        app.emptySignature(SRC, TEMP, "03_Signature Emitente", chain);
        app.createSignature(TEMP, DEST, "03_Signature Emitente", pk, chain);
        System.out.println("Done");
    }
}

