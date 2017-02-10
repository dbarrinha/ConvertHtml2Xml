/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coverthtml2pdf;


import java.io.ByteArrayInputStream; 
import java.io.InputStream; 
import java.io.OutputStream; 
import org.w3c.dom.Document; 
import org.w3c.tidy.Tidy; 
import org.xhtmlrenderer.pdf.ITextRenderer; 
import com.lowagie.text.DocumentException; 
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/** * @Autor Eder Baum */ 
public class CovertHtml2Pdf { 
    public static void convert(String input, OutputStream out) throws DocumentException{ 
        convert(new ByteArrayInputStream(input.getBytes()), out); 
    } 
    
    public static void convert(InputStream input, OutputStream out) throws DocumentException{ 
        Tidy tidy = new Tidy(); Document doc = tidy.parseDOM(input, null); 
        ITextRenderer renderer = new ITextRenderer(); 
        renderer.setDocument(doc, null); 
        renderer.layout(); 
        renderer.createPDF(out); 
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException, DocumentException{
        OutputStream os = new FileOutputStream("TesteTabela.pdf");; 
        CovertHtml2Pdf.convert("<table border=\"1\">\n" +
"  <tr>\n" +
"    <td>Célula 1</td>\n" +
"    <td>Célula 2</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td>Célula 3</td>\n" +
"    <td>Célula 4</td>\n" +
"  </tr>\n" +
"</table>", os); 
        os.close();
    }
}