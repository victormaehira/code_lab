package br.com.teste;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.xml.xmp.XmpWriter;

public class Teste {
	//public static final String SRC = "c:/java/zAssinatura_prontuario/atestado_medico_template.pdf";
	//public static final String SRC = "./arqPDFexemplo.pdf";
	public static final String SRC = "./atestado_medico.pdf";
    //public static final String DEST = "c:/java/zAssinatura_prontuario/atestado_medico_template_add_metadata.pdf";
	public static final String DEST = "./atestado_medico_oid.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Teste().manipulatePdf(SRC, DEST);
    }
       
    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfReader.unethicalreading = true;
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        Map info = reader.getInfo();
        info.put("Title", "Teste OID");
        //info.put("CreationDate", new PdfDate().toString());
        info.put("2.16.76.1.4.2.2.1", "52-75014-0");
        info.put("2.16.76.1.4.2.2.2", "RJ");
        info.put("2.16.76.1.4.2.2.3", "GERAL");
        info.put("2.16.76.1.4.2.2", "Conselho Federal de Medicina");
        info.put("2.16.76.1.12.1.2", "Atestado médico");
        
        
        
        stamper.setMoreInfo(info);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmpWriter xmp = new XmpWriter(baos, info);
        xmp.close();
        stamper.setXmpMetadata(baos.toByteArray());
        stamper.close();
        reader.close();
        System.out.println("Done");
    }

}
