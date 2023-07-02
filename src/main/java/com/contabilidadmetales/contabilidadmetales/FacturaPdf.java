package com.contabilidadmetales.contabilidadmetales;

import com.contabilidadmetales.contabilidadmetales.controlador.CInventario;
import com.contabilidadmetales.contabilidadmetales.controlador.Materiales;
import com.contabilidadmetales.contabilidadmetales.modelo.Pesada;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.text.DecimalFormat;

public class FacturaPdf {
    private Double totalAnteriord;
public String Numero_en_formato_COP="";
    public void setTotalAnteriord(Double totalAnteriord) {
        this.totalAnteriord = totalAnteriord;
    }
    public void generarFacturaPdf(Boolean es_copia, String nombreArchivo, String Tipo, Integer idfactura, String cliente, String direccion,
            String fecha, ArrayList<Pesada> pesadas, double total, Double kilos) throws IOException, DocumentException, SQLException {
        // Crear documento PDF
        FacturaPdf facturaPdf = new FacturaPdf();
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
        document.open();
        
         // Agregar imagen de encabezado
       String rutaImagen = "Imagenes/MetalesDeSantander/Acerca de/favicon.png"; // Reemplaza con la ruta y nombre de tu imagen
// Crear objeto Image
Image imagen = Image.getInstance(rutaImagen);

// Ajustar posición y tamaño de la imagen
imagen.setAbsolutePosition(document.left(), document.top());
imagen.scaleToFit(100, 100); // Ajusta el tamaño según tus necesidades
float marginLeft = 10; // Margen izquierda
float marginTop = document.top() - 30; // Margen superior
float width = 80; // Ancho de la imagen
float height = 80; // Alto de la imagen

imagen.setAbsolutePosition(marginLeft, marginTop);
imagen.scaleToFit(width, height);
        document.add(imagen);

        // Estilo para títulos
        Font tituloFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);

        // Estilo para datos de cliente y fecha
        Font datosFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);

        // Estilo para encabezado de tabla
        Font encabezadoTablaFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

        // Estilo para datos de tabla
        Font datosTablaFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);

        // Agregar encabezado
        Paragraph encabezado = new Paragraph();
        encabezado.add(new Phrase("CONTABILIDAD METALES  \n \n" + "Nit : 900405928\n" + "\n FACTURA # " + idfactura + "", tituloFont));
        encabezado.setAlignment(Element.ALIGN_CENTER);
        encabezado.setSpacingAfter(10f);
        document.add(encabezado);

        
        
        // Agregar datos del cliente
        Paragraph datosCliente = new Paragraph();
        datosCliente.add(new Phrase("Cliente: " + cliente, datosFont));
        datosCliente.add("\n");
        datosCliente.add(new Phrase("Descripción: " + direccion, datosFont));
        datosCliente.add("\n");
        datosCliente.add(new Phrase("Fecha: " + fecha, datosFont));
        datosCliente.setSpacingAfter(10f);
        document.add(datosCliente);

        // Agregar tabla de conceptos e importes
        float[] columnWidths = {3f, 1f, 1f, 1f};
        com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Encabezado de tabla
        com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Concepto", encabezadoTablaFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5f);
        table.addCell(cell);

        cell = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Cantidad (KG)", encabezadoTablaFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5f);
        table.addCell(cell);

        cell = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Precio (COP)", encabezadoTablaFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5f);
        table.addCell(cell);

        cell = new com.itextpdf.text.pdf.PdfPCell(new Phrase("Costo (COP)", encabezadoTablaFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5f);
        table.addCell(cell);
        CInventario inventario = new CInventario();
        // Datos de tabla
        for (Pesada pesada : pesadas) {

            Materiales materiales = new Materiales();
            String material = pesada.getMaterial();
            Double cantidad = pesada.getPesada();
            System.out.println(cantidad);
            Double costo = pesada.getValor();
            Double total1 = pesada.getTotal();
            int idMaterial = materiales.obtenerIdMaterialPorNombre(material);

            // Agregar el material al inventario en MySQL dependiendo del tipo de factura
            if (es_copia.equals(false)) {
                switch (Tipo) {
                    case "Compra" ->
                        inventario.agregarMaterial(-cantidad, idfactura.toString(), idMaterial, total1);
                    case "Venta" ->
                        inventario.agregarMaterial(cantidad, idfactura.toString(), idMaterial, -total1);
                    default ->
                        throw new AssertionError();
                }
            }

            cell = new com.itextpdf.text.pdf.PdfPCell(new Phrase(material, datosTablaFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(5f);
            table.addCell(cell);

            
           
            cell = new com.itextpdf.text.pdf.PdfPCell(new Phrase(String.format("%.2f", cantidad), datosTablaFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(5f);
            table.addCell(cell);

            
            DecimalFormat formato = new DecimalFormat("#,###.##");
         Numero_en_formato_COP = formato.format(costo);
            cell = new com.itextpdf.text.pdf.PdfPCell(new Phrase("$" + String.format( Numero_en_formato_COP), datosTablaFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(5f);
            table.addCell(cell);

            
                formato = new DecimalFormat("#,###.##");
         Numero_en_formato_COP = formato.format(total1);
            cell = new com.itextpdf.text.pdf.PdfPCell(new Phrase("$" + String.format( Numero_en_formato_COP), datosTablaFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setPadding(5f);
            table.addCell(cell);
        }

        document.add(table);

        if (es_copia) {
           if(totalAnteriord==null || totalAnteriord==0){
            Paragraph totalAnterior = new Paragraph();
            totalAnterior.add(new Phrase("\nTotal Anterior: $" + 0, datosFont));
            totalAnterior.setAlignment(Element.ALIGN_RIGHT);
            totalAnterior.setSpacingBefore(10f);
            document.add(totalAnterior);
           }
           else{
           DecimalFormat formato = new DecimalFormat("#,###.##");
         Numero_en_formato_COP = formato.format(totalAnteriord);
         
             Paragraph totalAnterior = new Paragraph();
            totalAnterior.add(new Phrase("\nTotal Anterior: $" + Numero_en_formato_COP, datosFont));
            totalAnterior.setAlignment(Element.ALIGN_RIGHT);
            totalAnterior.setSpacingBefore(10f);
            document.add(totalAnterior);
           }
         
            
           
        }

        // Agregar total
        DecimalFormat formato = new DecimalFormat("#,###.##");
          Numero_en_formato_COP = formato.format(total);
        
        Paragraph totalP = new Paragraph();
        totalP.add(new Phrase("Total: $" + Numero_en_formato_COP, datosFont));
        totalP.setAlignment(Element.ALIGN_RIGHT);
        totalP.setSpacingBefore(10f);
        document.add(totalP);

        // Agregar firma
        Paragraph firma = new Paragraph();
        firma.add("\n\n\nFirma:_________________________");
        firma.setAlignment(Element.ALIGN_CENTER);
        document.add(firma);

        // Cerrar documento PDF
        document.close();

        // Abrir archivo PDF generado
        abrirArchivo(nombreArchivo);
    }

    public void abrirArchivo(String archivo) {
        try {
            File objetofile = new File(archivo);
            Desktop.getDesktop().open(objetofile);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
