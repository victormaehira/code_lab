package br.com.teste;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.Collection;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.StampingProperties;
import com.itextpdf.signatures.BouncyCastleDigest;
import com.itextpdf.signatures.DigestAlgorithms;
import com.itextpdf.signatures.ICrlClient;
import com.itextpdf.signatures.IExternalDigest;
import com.itextpdf.signatures.IExternalSignature;
import com.itextpdf.signatures.IOcspClient;
import com.itextpdf.signatures.ITSAClient;
import com.itextpdf.signatures.PdfSignatureAppearance;
import com.itextpdf.signatures.PdfSigner;
import com.itextpdf.signatures.PrivateKeySignature;

public class C3_01_SignWithCAcert {
	 public static final String DEST = "./";
	 
	    //public static final String SRC = "./arqPDFexemplo.pdf";
	 public static final String SRC = "./atestado_medico_oid_preenchido.pdf";
	 
	    public static final String[] RESULT_FILES = new String[] {
	            //"hello_cacert.pdf"
	    		"atestado_medico_assinado_cms_2020-05-20.pdf"
	    };
	 
	    public void sign(String src, String dest, Certificate[] chain, PrivateKey pk,
	            String digestAlgorithm, String provider, PdfSigner.CryptoStandard subfilter,
	            String reason, String location, Collection<ICrlClient> crlList,
	            IOcspClient ocspClient, ITSAClient tsaClient, int estimatedSize)
	            throws GeneralSecurityException, IOException {
	        PdfReader reader = new PdfReader(src);
	        reader.setUnethicalReading(true);
	        PdfSigner signer = new PdfSigner(reader, new FileOutputStream(dest), new StampingProperties());
	 
	        // Create the signature appearance
	        //Rectangle rect = new Rectangle(36, 648, 200, 100);
	        //PdfSignatureAppearance appearance = signer.getSignatureAppearance();
	        //appearance
	                //.setReason(reason)
	                //.setLocation(location)
	 
	                // Specify if the appearance before field is signed will be used
	                // as a background for the signed field. The "false" value is the default value.
	                //.setReuseAppearance(false)
	                //.setPageRect(rect)
	                //.setPageNumber(1);
	        //signer.setFieldName("sig");
	        signer.setFieldName("03_Signature Emitente");
	        
	        
	        IExternalSignature pks = new PrivateKeySignature(pk, digestAlgorithm, provider);
	        IExternalDigest digest = new BouncyCastleDigest();
	        
	        // Sign the document using the detached mode, CMS or CAdES equivalent.
	        signer.signDetached(digest, pks, chain, crlList, ocspClient, tsaClient, estimatedSize, subfilter);
	    }
	 
	    public static void main(String[] args) throws IOException, GeneralSecurityException {
	        File file = new File(DEST);
	        file.mkdirs();
	 
	        //Properties properties = new Properties();
	 
	        /* This properties file should contain a CAcert certificate that belongs to the user,
	         * according to the original sample purpose. However right now it contains a simple
	         * self-signed certificate in p12 format, which serves as a stub.
	         */
	        //properties.load(new FileInputStream("./src/test/resources/encryption/signkey.properties"));
	 
	        // Get path to the p12 file
	        String path = "./27709797873_05_11_2020.pfx";
	 
	        // Get a password
	        char[] pass = new String("12345678").toCharArray();
	 
	        BouncyCastleProvider provider = new BouncyCastleProvider();
	        Security.addProvider(provider);
	 
	        // The first argument defines that the keys and certificates are stored using PKCS#12
	        KeyStore ks = KeyStore.getInstance("pkcs12", provider.getName());
	        ks.load(new FileInputStream(path), pass);
	        String alias = ks.aliases().nextElement();
	        PrivateKey pk = (PrivateKey) ks.getKey(alias, pass);
	        Certificate[] chain = ks.getCertificateChain(alias);
	 
	        new C3_01_SignWithCAcert().sign(SRC, DEST + RESULT_FILES[0], chain, pk,
	                DigestAlgorithms.SHA256, provider.getName(), PdfSigner.CryptoStandard.CMS,
	                "Test", "Ghent", null, null, null, 0);
	        
	        System.out.println("Done");
	    }
}
