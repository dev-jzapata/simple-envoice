package com.devjzapata.simpleenvoice.reports;

import com.devjzapata.simpleenvoice.albaranes.entities.Albaran;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AlbaranExporterPDF {

    private Albaran albaran;

    public AlbaranExporterPDF(Albaran albaran){
        this.albaran = albaran;
    }

    private void writeTableCabecera(PdfPTable table){
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Autolavado Jzapata", font));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell.setPhrase(new Phrase(albaran.getCliente().getNombre(), font));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell.setPhrase(new Phrase("CIF: xxxxxxxxx"));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell.setPhrase(new Phrase(albaran.getCliente().getCif()));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell.setPhrase(new Phrase("C/Virgel del P, Nº x"));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell.setPhrase(new Phrase(albaran.getCliente().getDireccion()));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell.setPhrase(new Phrase("C.P. 30310, Cartagena, Murcia, España"));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell.setPhrase(new Phrase(albaran.getCliente().getCp()
                +", "+albaran.getCliente().getCiudad()
                +", "+albaran.getCliente().getProvincia()
                +", "+ albaran.getCliente().getPais()));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);


        cell.setPhrase(new Phrase("Tel: 968080808 / 609696969"));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell.setPhrase(new Phrase(albaran.getCliente().getTelefono()+" / "+ albaran.getCliente().getTelefono2()));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell.setPhrase(new Phrase("Email: dev.jzapata@gmail.com"));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);

        cell.setPhrase(new Phrase(albaran.getCliente().getEmail()));
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);


    }

    private void writeTableAlbaranes(PdfPTable table){

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.white);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Fecha", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Matricula", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cliente", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Lavado", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("precio", font));
        table.addCell(cell);


    }

    private void writeTableDataAlbaranes(PdfPTable table){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        table.addCell(String.valueOf(albaran.getId()));
        table.addCell(String.valueOf(albaran.getFecha().format(formatter)));
        table.addCell(albaran.getMatricula());
        table.addCell(albaran.getCliente().getNombre());
        table.addCell(albaran.getLavado().getNombre());
        table.addCell(String.valueOf(albaran.getPrecio()));

    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);
        font.setColor(Color.BLACK);


        Paragraph p1 = new Paragraph("Albaran: " + albaran.getId(), font);
        p1.setAlignment(Paragraph.ALIGN_CENTER);
        p1.setSpacingBefore(15);
        document.add(p1);



        PdfPTable tableCabecera = new PdfPTable(2);
        tableCabecera.setWidthPercentage(100f);
        tableCabecera.setWidths(new float[]{8f, 5f});
        tableCabecera.setSpacingBefore(10);
        writeTableCabecera(tableCabecera);
        document.add(tableCabecera);


        Paragraph p2 = new Paragraph("Albaranes", font);
        p2.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p2);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3f, 3f, 3.5f, 3.5f, 2f});
        table.setSpacingBefore(10);

        writeTableAlbaranes(table);
        writeTableDataAlbaranes(table);
        document.add(table);
        table.setSpacingBefore(10);

        document.close();
    }


}
