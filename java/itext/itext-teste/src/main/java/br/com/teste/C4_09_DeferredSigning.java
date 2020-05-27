package br.com.teste;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.Certificate;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.StampingProperties;
import com.itextpdf.signatures.BouncyCastleDigest;
import com.itextpdf.signatures.DigestAlgorithms;
import com.itextpdf.signatures.ExternalBlankSignatureContainer;
import com.itextpdf.signatures.IExternalSignatureContainer;
import com.itextpdf.signatures.PdfPKCS7;
import com.itextpdf.signatures.PdfSigner;
import com.itextpdf.signatures.PrivateKeySignature;


//fonte
//https://itextpdf.com/en/resources/examples/itext-7/digital-signatures-chapter-4
public class C4_09_DeferredSigning {
    //public static final String DEST = "./target/signatures/chapter04/";
	public static final String DEST = "./";
 
    //public static final String SRC = "./src/test/resources/pdfs/hello.pdf";
    public static final String SRC = "./atestado_medico_oid_preenchido.pdf";
	//public static final String SRC = "./atestado_medico.pdf";
	//public static final String SRC = "./atestado_medico_com_crm.pdf";
    public static final String TEMP = "./temp.pdf";
    //public static final String KEYSTORE = "./src/test/resources/encryption/ks";
    public static final String KEYSTORE = "./27709797873_05_11_2020.pfx";
    
    //public static final char[] PASSWORD = "password".toCharArray();
    public static final char[] PASSWORD = "12345678".toCharArray();
 
    public static final String[] RESULT_FILES = new String[] {
            //"hello_sig_ok.pdf"
    		"atestado_medico_assinado_cms_2020-05-20.pdf"
    };
 
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        File file = new File(DEST);
        file.mkdirs();
 
        BouncyCastleProvider providerBC = new BouncyCastleProvider();
        Security.addProvider(providerBC);
 
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(KEYSTORE), PASSWORD);
        String alias = ks.aliases().nextElement();
        Certificate[] chain = ks.getCertificateChain(alias);
        PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
 
        C4_09_DeferredSigning app = new C4_09_DeferredSigning();
        //app.emptySignature(SRC, TEMP, "sig", chain);
        app.emptySignature(SRC, TEMP, "03_Signature Emitente", chain);
        //app.createSignature(TEMP, DEST + RESULT_FILES[0], "sig", pk, chain);
        app.createSignature(TEMP, DEST + RESULT_FILES[0], "03_Signature Emitente", pk, chain);
        
        System.out.println("Done");
    }
 
    public void emptySignature(String src, String dest, String fieldname, Certificate[] chain)
            throws IOException, GeneralSecurityException {
        PdfReader reader = new PdfReader(src);
        reader.setUnethicalReading(true);
        PdfSigner signer = new PdfSigner(reader, new FileOutputStream(dest), new StampingProperties());
//        PdfSignatureAppearance appearance = signer.getSignatureAppearance();
//        appearance
//                .setPageRect(new Rectangle(36, 748, 200, 100))
//                .setPageNumber(1)
//                .setCertificate(chain[0]);
        signer.setFieldName(fieldname);
        //signer.setFieldName("03_Signature Emitente");
 
        /* ExternalBlankSignatureContainer constructor will create the PdfDictionary for the signature
         * information and will insert the /Filter and /SubFilter values into this dictionary.
         * It will leave just a blank placeholder for the signature that is to be inserted later.
         */
        IExternalSignatureContainer external = new ExternalBlankSignatureContainer(PdfName.Adobe_PPKLite,
                PdfName.Adbe_pkcs7_detached);
 
        // Sign the document using an external container.
        // 8192 is the size of the empty signature placeholder.
        signer.signExternalContainer(external, 8192);
    }
 
    public void createSignature(String src, String dest, String fieldName, PrivateKey pk, Certificate[] chain)
            throws IOException, GeneralSecurityException {
        PdfReader reader = new PdfReader(src);
        reader.setUnethicalReading(true);
        try(FileOutputStream os = new FileOutputStream(dest)) {
            PdfSigner signer = new PdfSigner(reader, os, new StampingProperties());
 
            IExternalSignatureContainer external = new MyExternalSignatureContainer(pk, chain);
 
            // Signs a PDF where space was already reserved. The field must cover the whole document.
            signer.signDeferred(signer.getDocument(), fieldName, os, external);
        }
    }
 
    class MyExternalSignatureContainer implements IExternalSignatureContainer {
 
        protected PrivateKey pk;
        protected Certificate[] chain;
 
        public MyExternalSignatureContainer(PrivateKey pk, Certificate[] chain) {
            this.pk = pk;
            this.chain = chain;
        }
 
        public byte[] sign(InputStream is) throws GeneralSecurityException {
            try {
                //PrivateKeySignature signature = new PrivateKeySignature(pk, "SHA256", "BC");
                //String hashAlgorithm = signature.getHashAlgorithm();
                BouncyCastleDigest digest = new BouncyCastleDigest();
 
                //PdfPKCS7 sgn = new PdfPKCS7(null, chain, hashAlgorithm, null, digest, false);
                PdfPKCS7 sgn = new PdfPKCS7(null, chain, "SHA256", null, digest, false);
                //byte hash[] = DigestAlgorithms.digest(is, digest.getMessageDigest(hashAlgorithm));
                byte hash[] = DigestAlgorithms.digest(is, digest.getMessageDigest("SHA256"));
               
                //SALVAR HASH EM ARQUIVO
                System.out.println("base64 = " + new String(Base64.encode(hash)));
                
                byte[] sh = sgn.getAuthenticatedAttributeBytes(hash, PdfSigner.CryptoStandard.CMS, null, null);
                
                //teste assinatura
                Signature signatureJava = Signature.getInstance("SHA256WithRSA");
        		signatureJava.initSign(pk);
        		signatureJava.update(sh); 
        		byte[] extSignature = signatureJava.sign();
                //byte[] extSignature = signature.sign(sh);
        		
                //System.out.println(signature.getEncryptionAlgorithm());
                //sgn.setExternalDigest(extSignature, null, signature.getEncryptionAlgorithm());
        		sgn.setExternalDigest(extSignature, null, "RSA");
 
                return sgn.getEncodedPKCS7(hash, PdfSigner.CryptoStandard.CMS, null, null, null);
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
 
        public void modifySigningDictionary(PdfDictionary signDic) {
        }
    }
}
