package com.outstandingboy.pdfboxexample;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PdfboxExample {
    public static void write() throws IOException {
        PDDocument doc = new PDDocument();

        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);

        InputStream fontStream = new FileInputStream("BMHANNAPro.ttf");
        PDType0Font fontGulim = PDType0Font.load(doc, fontStream);

        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
        contentStream.beginText();
        contentStream.setFont(fontGulim, 12.0f);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(0, page.getBBox().getHeight() - 12);
        contentStream.showText("Hello PDF");
        contentStream.showText(" HIHI");
        contentStream.newLine();
        contentStream.showText("반가워요~");
        contentStream.endText();

        PDImageXObject image = PDImageXObject.createFromFile("image.jpg", doc);
        contentStream.drawImage(image, 0, page.getBBox().getHeight() - (image.getHeight() / 10 + 14.5f * 3), image.getWidth() / 10, image.getHeight() / 10);

        contentStream.close();

        doc.save(new File("test.pdf"));
        doc.close();
    }

    public static void read() throws IOException {
        String fileName = "test.pdf";
        File source = new File(fileName);
        PDDocument pdfDoc = PDDocument.load(source);
        PDPage page = pdfDoc.getPage(0);

        PDFTextStripper textStripper = new PDFTextStripper();
//        textStripper.setStartPage(2);
//        textStripper.setEndPage(2);

        String text = textStripper.getText(pdfDoc);
        System.out.println(text);
    }

    public static void main(String[] args) throws IOException {
//        write(); // PDF파일 작성
        read(); // PDF파일 텍스트 읽기
    }
}
