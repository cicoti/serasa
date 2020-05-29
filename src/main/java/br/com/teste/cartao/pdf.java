package br.com.teste.cartao;
import java.io.FileOutputStream;

import java.io.IOException;

import com.lowagie.text.*;

import com.lowagie.text.pdf.*;

public class pdf {

    private static String INPUTFILE = "d:\\silvio\\agente.pdf" ;

    private static String OUTPUTFILE = "d:\\silvio\\agente2.pdf";

    public static void main(String[] args) throws DocumentException,
            IOException {

        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(OUTPUTFILE));

        document.open();

        PdfReader reader = new PdfReader(INPUTFILE);

        int n = reader.getNumberOfPages();

        PdfImportedPage page;


        for (int i = 1; i <= n; i++) {

                page = writer.getImportedPage(reader, i);

                Image instance = Image.getInstance(page);

                document.add(instance);

        }

        document.close();

    }

}