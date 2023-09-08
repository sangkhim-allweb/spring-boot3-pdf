package com.sangkhim.spring_boot3_pdf.service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PdfService {

  public void writeText() throws FileNotFoundException, DocumentException {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

    document.open();
    Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
    Chunk chunk = new Chunk("Hello World", font);

    document.add(chunk);
    document.close();
  }

  public void writeImage() throws URISyntaxException, IOException, DocumentException {
    Path path = Paths.get(ClassLoader.getSystemResource("logo.png").toURI());

    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("iTextImageExample.pdf"));
    document.open();
    Image img = Image.getInstance(path.toAbsolutePath().toString());
    document.add(img);

    document.close();
  }

  public void writeTable() throws IOException, DocumentException, URISyntaxException {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("iTextTable.pdf"));

    document.open();

    PdfPTable table = new PdfPTable(3);
    addTableHeader(table);
    addRows(table);
    addCustomRows(table);

    document.add(table);
    document.close();
  }

  private void addTableHeader(PdfPTable table) {
    Stream.of("column header 1", "column header 2", "column header 3")
        .forEach(
            columnTitle -> {
              PdfPCell header = new PdfPCell();
              header.setBackgroundColor(BaseColor.LIGHT_GRAY);
              header.setBorderWidth(2);
              header.setPhrase(new Phrase(columnTitle));
              table.addCell(header);
            });
  }

  private void addRows(PdfPTable table) {
    table.addCell("row 1, col 1");
    table.addCell("row 1, col 2");
    table.addCell("row 1, col 3");
  }

  private void addCustomRows(PdfPTable table)
      throws URISyntaxException, BadElementException, IOException {

    Path path = Paths.get(ClassLoader.getSystemResource("logo.png").toURI());
    Image img = Image.getInstance(path.toAbsolutePath().toString());
    img.scalePercent(10);

    PdfPCell imageCell = new PdfPCell(img);
    table.addCell(imageCell);

    PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
    horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(horizontalAlignCell);

    PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
    verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
    table.addCell(verticalAlignCell);
  }
}
