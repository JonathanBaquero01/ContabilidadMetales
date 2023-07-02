/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.vistas;

import Clases_FrontEnd.CustomComboBoxRenderer;
import Clases_FrontEnd.PlaceHolder;
import Clases_FrontEnd.RightAlignedTableCellRenderer;
import Clases_FrontEnd.RoundBorder;
import com.contabilidadmetales.contabilidadmetales.CajaMenorReporteGenerator;
import com.contabilidadmetales.contabilidadmetales.FacturaPdf;
import com.contabilidadmetales.contabilidadmetales.InventarioReportGenerator;
import com.contabilidadmetales.contabilidadmetales.controlador.CCuentas;
import com.contabilidadmetales.contabilidadmetales.controlador.CInventario;
import com.contabilidadmetales.contabilidadmetales.controlador.CPersona;
import com.contabilidadmetales.contabilidadmetales.controlador.CPrestamos;
import com.contabilidadmetales.contabilidadmetales.controlador.CajaMenorController;
import com.contabilidadmetales.contabilidadmetales.modelo.Cuenta;
import com.contabilidadmetales.contabilidadmetales.modelo.persona;
import java.awt.Color;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import com.contabilidadmetales.contabilidadmetales.controlador.Materiales;
import com.contabilidadmetales.contabilidadmetales.controlador.TipoMovimientoController;
import com.contabilidadmetales.contabilidadmetales.modelo.Inventario;
import com.contabilidadmetales.contabilidadmetales.modelo.Material;
import com.contabilidadmetales.contabilidadmetales.modelo.MovimientoCajaMenor;
import com.contabilidadmetales.contabilidadmetales.modelo.Pesada;
import com.contabilidadmetales.contabilidadmetales.modelo.TipoMovimiento;
import com.itextpdf.text.DocumentException;
import java.awt.ComponentOrientation;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.sql.Date;
import java.util.*;
import java.time.ZoneId;
import java.time.Instant;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author narut
 */
public class MetalesDeSantander extends javax.swing.JFrame {

    public String Numero_en_formato_COP = "";
    FTipoMovimiento TipoMovimientos;

    private RegistrarPrestamo RegistrarPrestamos;
    private CPersona conp;
    private CPrestamos prestamos;
    private CInventario inve;
    private Materiales cc;
    private String listaTx = null;
    private int idselec = 0;

    private FacturaPdf facturaPdf = new FacturaPdf();
    private int idUsuario;
    private LocalDate fechaActual;
    private LocalDate fechaAnterior;
    private String material;

    public int selec() {
        int TipoDePersona;
        switch (TipoDePersonaComboBox.getSelectedItem().toString()) {
            case "Cliente" ->
                TipoDePersona = 2;
            case "Proveedor" ->
                TipoDePersona = 1;
            case "Usuario" ->
                TipoDePersona = 3;
            default ->
                throw new AssertionError();
        }
        return TipoDePersona;
    }

    public MetalesDeSantander(int idUsuario) {
        this.idUsuario = idUsuario;
        conp = new CPersona();
        initComponents();
        prestamos = new CPrestamos();
        prestamos.listaPrestamos(TPrestamos);
        optenerListaProbedor(selec());
        //  this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;//consigo el ancho y alto de la pantalla

        this.setBounds((ancho / 2) - (this.getWidth() / 2), (alto / 2) - (this.getHeight() / 2), 1480, 650); //determino el tamaño de la pantalla del sw , ancho , alto

        this.setLocationRelativeTo(this);//para q se ponga en el centro

        this.setIconImage(new ImageIcon("Imagenes/FormLogin/favicon.png").getImage());
        this.setTitle("Menú");

        //  this.setLocation((ancho / 2) - (this.getWidth() / 2), 0);//ancho , alto con esa formula consigo la mitad del largo de la pantalla, y el 0 pa que se situe en el top
        DefaultTableModel model = (DefaultTableModel) TInventario.getModel();
        this.setResizable(false);
        Agregacion_imagenes();
        Ajustamiento_Objetos();
        try {

            llenarTabla(model);
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "falla al llenar la sita");
        }
        CajaMenorController caja = new CajaMenorController();
        caja.obtenerMovimientosCajaMenortabla(TablaCajaMenor);
        MetodoComboboxInventario(ListMaterialesInve);

    }

    public void Agregacion_imagenes() {
        ImageIcon icon = new ImageIcon("Imagenes/MetalesDeSantander/Recuperacion de hierro.png");
        jLabelRecuperacion_de_hierro.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Cuenta/Generar factura.png");
        BotonabonarCC1.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Cuenta/Abonar.png");
        BotonabonarCC.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Cuenta/Cancelar cuenta.png");
        CancelarCuenta.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Cuenta/Nueva cuenta.png");
        jButton8.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Cuenta/Generar factura.png");
        BotonabonarCC2.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Degradado.png");
        jLabelDegradadoMenu.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Cuenta/PersonasRegistradas.png");
        jLabelPersonasregistradas_icono.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Cuenta/Tipo de Cuenta.png");
        jLabeltipo_de_cuenta.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Cuenta/Categoria.png");
        jLabelCategoria_icono.setIcon(icon);

        //APARTADO PERSONAS
        icon = new ImageIcon("Imagenes/MetalesDeSantander/Recuperacion de hierro.png");
        jLabelRecuperacion_de_hierro2.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Personas/AgregarPersona.png");
        BAgregarClientes.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Personas/EditarPersona.png");
        EditarCliente.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Personas/EliminarPersona.png");
        eliminarPersona.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Degradado.png");
        jLabelDegradadoMenu_Personas.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Personas/Identificacion2.png");
        jLabelIdentificacion.setIcon(icon);

//APARTADO PRESTAMOS
        icon = new ImageIcon("Imagenes/MetalesDeSantander/Recuperacion de hierro.png");
        jLabelRecuperacion_de_hierro4.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Degradado.png");
        jLabelDegradadoMenu_Prestamos.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Prestamos/Registrar prestamo.png");
        jButtonRegistrar_Prestamo.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Prestamos/Actualizar prestamo.png");
        jButtonActualizar_Prestamo.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Prestamos/Cancelar prestamo.png");
        jButtonCancelar_Prestamo.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Prestamos/Prestamos.png");
        jLabel1.setIcon(icon);

        //APARTADO INVENTARIO
        icon = new ImageIcon("Imagenes/MetalesDeSantander/Recuperacion de hierro.png");
        jLabelRecuperacion_de_hierro5.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Degradado.png");
        jLabelDegradadoMenu_Inventario.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Inventario/Aplicar filtro.png");
        jButton7.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Inventario/Informe general.png");
        jButton12.setIcon(icon);

        //le digo q donde posicione lo q se escriba
        JTpeso.setHorizontalAlignment(JTpeso.CENTER);
        JTValor.setHorizontalAlignment(JTpeso.CENTER);
         Descripcion.setHorizontalAlignment(Descripcion.CENTER);
          Valor_dinero.setHorizontalAlignment(Valor_dinero.CENTER);

        //aqui le asigon lo q se vera en el text q se borra al escribir
        PlaceHolder placeholder = new PlaceHolder("Peso (KG)", JTpeso, PlaceHolder.Show.ALWAYS, JTpeso.CENTER);
        placeholder = new PlaceHolder("(COP)", JTValor, PlaceHolder.Show.ALWAYS, JTValor.CENTER);
        // Establece el color del borde y el radio de los bordes redondos de los Jtext,todo esto se envia a la clase RoundBorder
        JTpeso.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
        JTValor.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Inventario/Material.png");
        jLabel13.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Inventario/Inventario.png");
        jLabel14.setIcon(icon);
        
        //APARTADO INGRESOS Y EGRESOS
         icon = new ImageIcon("Imagenes/MetalesDeSantander/Recuperacion de hierro.png");
        jLabelRecuperacion_de_hierro3.setIcon(icon);

        icon = new ImageIcon("Imagenes/MetalesDeSantander/Degradado.png");
        jLabelDegradadoMenu_Ingresis_y_egresos.setIcon(icon);
        
          icon = new ImageIcon("Imagenes/MetalesDeSantander/Ingresos y egresos/Agregar movimiento.png");
        jButton9.setIcon(icon);
        
         icon = new ImageIcon("Imagenes/MetalesDeSantander/Ingresos y egresos/Agregar tipo de movimiento.png");
        jButton6.setIcon(icon);
        
        icon = new ImageIcon("Imagenes/MetalesDeSantander/Ingresos y egresos/Eliminar movimiento.png");
        EliminarTipoMovimiento.setIcon(icon);
        
          icon = new ImageIcon("Imagenes/MetalesDeSantander/Ingresos y egresos/Generar informe.png");
        jButton10.setIcon(icon);
        
          icon = new ImageIcon("Imagenes/MetalesDeSantander/Ingresos y egresos/Ingreso o egreso.png");
        jLabel6.setIcon(icon);
          icon = new ImageIcon("Imagenes/MetalesDeSantander/Ingresos y egresos/Tipo de movimiento.png");
        jLabel9.setIcon(icon);
          icon = new ImageIcon("Imagenes/MetalesDeSantander/Ingresos y egresos/Tipo de transaccion.png");
        jLabel15.setIcon(icon);
        
        
        
        
        

       placeholder = new PlaceHolder("Por favor, ingrese la descripción", Descripcion, PlaceHolder.Show.ALWAYS, Descripcion.CENTER);
        placeholder = new PlaceHolder("Valor dinero (COP)", Valor_dinero, PlaceHolder.Show.ALWAYS, Valor_dinero.CENTER);
        // Establece el color del borde y el radio de los bordes redondos de los Jtext,todo esto se envia a la clase RoundBorder
        Descripcion.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
        Valor_dinero.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));

        Tipo.setRenderer(new CustomComboBoxRenderer());
        TipoInforme.setRenderer(new CustomComboBoxRenderer());
        TipoMovimiento.setRenderer(new CustomComboBoxRenderer());

    }

    public void Ajustamiento_Objetos() {
        //cuando seleccione una fila, el texto cambia de color
        TCuentaXC.setSelectionForeground(Color.BLACK);
        TCuentaXC1.setSelectionForeground(Color.BLACK);

        Dimension panelSize = jPanelBoton_Abonar.getSize();
        jPanelBoton_Abonar.setPreferredSize(new Dimension(panelSize.width, 36));

        panelSize = jPanelBoton_Abonar.getSize();
        jPanelBoton_Cancelar_cuenta.setPreferredSize(new Dimension(panelSize.width, 36));

        panelSize = jPanelBoton_Abonar.getSize();
        jPanelNuevaCuenta.setPreferredSize(new Dimension(panelSize.width, 36));

        panelSize = jPanelBoton_Abonar.getSize();
        jPanel3GenerarFactura.setPreferredSize(new Dimension(panelSize.width, 36));

        //Aqui le asigno el tipo de letra a los titulos de la tabla
        String fontName = "Arial"; // Nombre de la fuente deseada
        int fontSize = 14; // Tamaño de la fuente deseado
        Font font = new Font(fontName, Font.BOLD, fontSize);
        TCuentaXC1.getTableHeader().setFont(font);
        ((DefaultTableCellRenderer) TCuentaXC1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

//aqui le dire a la tabla cuanto espacio por defecto ocupe cada columna
        TCuentaXC1.getColumnModel().getColumn(0).setPreferredWidth(35); // Ejemplo de ancho de 100 píxeles para la primera columna
        TCuentaXC1.getColumnModel().getColumn(1).setPreferredWidth(85);
        TCuentaXC1.getColumnModel().getColumn(2).setPreferredWidth(70);
        TCuentaXC1.getColumnModel().getColumn(3).setPreferredWidth(150);
        TCuentaXC1.getColumnModel().getColumn(4).setPreferredWidth(85);

//Este de aqui, le digo que color ponga para el fondo de la tabla donde no hallan celdas
        TCuentaXC1.setBackground(new Color(234, 250, 254));
        JViewport viewport = (JViewport) TCuentaXC1.getParent();
        viewport.setBackground(new Color(234, 250, 254));
//este es para el color de fondo de las celdas
        TCuentaXC1.setBackground(new Color(241, 253, 253));

        EmptyBorder emptyBorder = new EmptyBorder(0, 0, 0, 0);
        TCuentaXC1.setBorder(emptyBorder);

        //ESTO ES PA Q EN UNA COLUMNA, EL TEXTO SE MUESTRE DE DERECHA A IZQUEIRDA
        TableColumn column = TCuentaXC1.getColumnModel().getColumn(3);
        column.setCellRenderer(new RightAlignedTableCellRenderer());

        //esto me alinea todas las filas
        TCuentaXC1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        });

        //LO MISMO PERO PA LA TABLA 2
        //Aqui le asigno el tipo de letra a los titulos de la tabla
        fontName = "Arial"; // Nombre de la fuente deseada
        fontSize = 14; // Tamaño de la fuente deseado
        font = new Font(fontName, Font.BOLD, fontSize);
        TCuentaXC.getTableHeader().setFont(font);
        ((DefaultTableCellRenderer) TCuentaXC.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

//aqui le dire a la tabla cuanto espacio por defecto ocupe cada columna
        TCuentaXC.getColumnModel().getColumn(0).setPreferredWidth(35); // Ejemplo de ancho de 100 píxeles para la primera columna
        TCuentaXC.getColumnModel().getColumn(1).setPreferredWidth(85);
        TCuentaXC.getColumnModel().getColumn(2).setPreferredWidth(70);
        TCuentaXC.getColumnModel().getColumn(3).setPreferredWidth(150);
        TCuentaXC.getColumnModel().getColumn(4).setPreferredWidth(85);

//Este de aqui, le digo que color ponga para el fondo de la tabla donde no hallan celdas
        TCuentaXC.setBackground(new Color(234, 250, 254));
        viewport = (JViewport) TCuentaXC.getParent();
        viewport.setBackground(new Color(234, 250, 254));
//este es para el color de fondo de las celdas
        TCuentaXC.setBackground(new Color(241, 253, 253));

        emptyBorder = new EmptyBorder(0, 0, 0, 0);
        TCuentaXC.setBorder(emptyBorder);

        //ESTO ES PA Q EN UNA COLUMNA, EL TEXTO SE MUESTRE DE DERECHA A IZQUEIRDA
        column = TCuentaXC.getColumnModel().getColumn(3);
        column.setCellRenderer(new RightAlignedTableCellRenderer());

        //aqui le q vaya a la clase que PERSONALIZA LOS COLORES DEL JCOMBOBX
        ListaClientesCuentaCB.setRenderer(new CustomComboBoxRenderer());
        TipoDePersonaComboBox.setRenderer(new CustomComboBoxRenderer());
        tipo_Cuenta.setRenderer(new CustomComboBoxRenderer());

        //esto me alinea todas las filas
        TCuentaXC.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        });

        //APARTADO PERSONAS
        TClientes.setSelectionForeground(Color.BLACK);
        //Aqui le asigno el tipo de letra a los titulos de la tabla
        fontName = "Arial"; // Nombre de la fuente deseada
        fontSize = 14; // Tamaño de la fuente deseado
        font = new Font(fontName, Font.BOLD, fontSize);
        TClientes.getTableHeader().setFont(font);
        ((DefaultTableCellRenderer) TClientes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

//Este de aqui, le digo que color ponga para el fondo de la tabla donde no hallan celdas
        TClientes.setBackground(new Color(234, 250, 254));
        viewport = (JViewport) TClientes.getParent();
        viewport.setBackground(new Color(234, 250, 254));
//este es para el color de fondo de las celdas
        TClientes.setBackground(new Color(241, 253, 253));

        emptyBorder = new EmptyBorder(0, 0, 0, 0);
        TClientes.setBorder(emptyBorder);

        //aqui le dire a la tabla cuanto espacio por defecto ocupe cada columna
        TClientes.getColumnModel().getColumn(0).setPreferredWidth(60); // Ejemplo de ancho de 100 píxeles para la primera columna
        TClientes.getColumnModel().getColumn(1).setPreferredWidth(2);
        TClientes.getColumnModel().getColumn(2).setPreferredWidth(20);
        TClientes.getColumnModel().getColumn(3).setPreferredWidth(35);
        TClientes.getColumnModel().getColumn(4).setPreferredWidth(220);

        TClientes.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        });
         //APARTADO IGRESOS Y EGRESOS
            //aqui le dire a la tabla cuanto espacio por defecto ocupe cada columna
         TablaCajaMenor.getColumnModel().getColumn(0).setPreferredWidth(60); // Ejemplo de ancho de 100 píxeles para la primera columna
        TablaCajaMenor.getColumnModel().getColumn(1).setPreferredWidth(2);
        TablaCajaMenor.getColumnModel().getColumn(2).setPreferredWidth(20);
        TablaCajaMenor.getColumnModel().getColumn(3).setPreferredWidth(35);
        TablaCajaMenor.getColumnModel().getColumn(4).setPreferredWidth(220);
            TablaCajaMenor.setSelectionForeground(Color.BLACK);
        //Aqui le asigno el tipo de letra a los titulos de la tabla
        fontName = "Arial"; // Nombre de la fuente deseada
        fontSize = 14; // Tamaño de la fuente deseado
        font = new Font(fontName, Font.BOLD, fontSize);
        TablaCajaMenor.getTableHeader().setFont(font);
        ((DefaultTableCellRenderer) TablaCajaMenor.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

//Este de aqui, le digo que color ponga para el fondo de la tabla donde no hallan celdas
        TablaCajaMenor.setBackground(new Color(234, 250, 254));
        viewport = (JViewport) TablaCajaMenor.getParent();
        viewport.setBackground(new Color(234, 250, 254));
//este es para el color de fondo de las celdas
        TablaCajaMenor.setBackground(new Color(241, 253, 253));

        emptyBorder = new EmptyBorder(0, 0, 0, 0);
        TablaCajaMenor.setBorder(emptyBorder);

     
        
        
      TablaCajaMenor.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        });
        
          //aqui le dire a la tabla cuanto espacio por defecto ocupe cada columna
         TablaCajaMenor.getColumnModel().getColumn(0).setPreferredWidth(60); // Ejemplo de ancho de 100 píxeles para la primera columna
        TablaCajaMenor.getColumnModel().getColumn(1).setPreferredWidth(2);
        TablaCajaMenor.getColumnModel().getColumn(2).setPreferredWidth(20);
        TablaCajaMenor.getColumnModel().getColumn(3).setPreferredWidth(35);
        TablaCajaMenor.getColumnModel().getColumn(4).setPreferredWidth(220);

//APARTADO PRESTAMOS
        TablaCajaMenor.setSelectionForeground(Color.BLACK);
        //Aqui le asigno el tipo de letra a los titulos de la tabla
        fontName = "Arial"; // Nombre de la fuente deseada
        fontSize = 14; // Tamaño de la fuente deseado
        font = new Font(fontName, Font.BOLD, fontSize);
        TPrestamos.getTableHeader().setFont(font);
        ((DefaultTableCellRenderer) TPrestamos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

//Este de aqui, le digo que color ponga para el fondo de la tabla donde no hallan celdas
        TPrestamos.setBackground(new Color(234, 250, 254));
        viewport = (JViewport) TPrestamos.getParent();
        viewport.setBackground(new Color(234, 250, 254));
//este es para el color de fondo de las celdas
        TPrestamos.setBackground(new Color(241, 253, 253));

        emptyBorder = new EmptyBorder(0, 0, 0, 0);
        TPrestamos.setBorder(emptyBorder);

        //aqui le dire a la tabla cuanto espacio por defecto ocupe cada columna
        TPrestamos.getColumnModel().getColumn(0).setPreferredWidth(50); // Ejemplo de ancho de 100 píxeles para la primera columna
        TPrestamos.getColumnModel().getColumn(1).setPreferredWidth(40);
        TPrestamos.getColumnModel().getColumn(2).setPreferredWidth(75);
        TPrestamos.getColumnModel().getColumn(3).setPreferredWidth(35);
        TPrestamos.getColumnModel().getColumn(4).setPreferredWidth(40);
        TPrestamos.getColumnModel().getColumn(5).setPreferredWidth(250);
        TPrestamos.getColumnModel().getColumn(6).setPreferredWidth(35);

        TPrestamos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        });

        //APARTADO INVENTARIO
        ListMaterialesInve.setRenderer(new CustomComboBoxRenderer());

        TInventario.setSelectionForeground(Color.BLACK);
        //Aqui le asigno el tipo de letra a los titulos de la tabla
        fontName = "Arial"; // Nombre de la fuente deseada
        fontSize = 14; // Tamaño de la fuente deseado
        font = new Font(fontName, Font.BOLD, fontSize);
        TInventario.getTableHeader().setFont(font);
        ((DefaultTableCellRenderer) TInventario.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

//Este de aqui, le digo que color ponga para el fondo de la tabla donde no hallan celdas
        TInventario.setBackground(new Color(234, 250, 254));
        viewport = (JViewport) TInventario.getParent();
        viewport.setBackground(new Color(234, 250, 254));
//este es para el color de fondo de las celdas
        TInventario.setBackground(new Color(241, 253, 253));

        emptyBorder = new EmptyBorder(0, 0, 0, 0);
        TInventario.setBorder(emptyBorder);

        //aqui le dire a la tabla cuanto espacio por defecto ocupe cada columna
        /*   TInventario.getColumnModel().getColumn(0).setPreferredWidth(50); // Ejemplo de ancho de 100 píxeles para la primera columna
        TInventario.getColumnModel().getColumn(1).setPreferredWidth(40);
        TInventario.getColumnModel().getColumn(2).setPreferredWidth(75);
        TInventario.getColumnModel().getColumn(3).setPreferredWidth(35);
        TInventario.getColumnModel().getColumn(4).setPreferredWidth(40);
        TInventario.getColumnModel().getColumn(5).setPreferredWidth(250);
        TInventario.getColumnModel().getColumn(6).setPreferredWidth(35);*/
        TInventario.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        });

        
        //INGRESOS Y EGRESOS
    }

    public void MEtodoMesesDias(JComboBox ListMeses, JComboBox ListDias) {
        // Obtener el mes actual en un objeto LocalDate
        LocalDate currentDate = LocalDate.now();
        // Obtener el número del mes actual
        int currentMonthValue = currentDate.getMonthValue();
        // Crear una matriz que incluya el mes actual al principio
        String[] months = {currentMonthValue + "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        // Crear el modelo del JComboBox utilizando la matriz actualizada
        DefaultComboBoxModel<String> monthModel = new DefaultComboBoxModel<>(months);
        // Establecer el modelo en el JComboBox
        ListMeses.setModel(monthModel);

        // Agregar el día actual al principio del JComboBox de días
        int currentDay = currentDate.getDayOfMonth();
        DefaultComboBoxModel<String> modeloDias = new DefaultComboBoxModel<>();
        modeloDias.addElement(String.format("%02d", currentDay));

        for (int i = 1; i <= 31; i++) {

            if (i == currentDay) {
            } else {
                modeloDias.addElement(String.format("%02d", i));
            }
        }
        ListDias.setModel(modeloDias);
    }

    public void llenarTabla(DefaultTableModel modeloTabla) throws SQLException {
        inve = new CInventario();
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        // Restar un mes a la fecha actual
        LocalDate fechaUnMesAtras = fechaActual.minusMonths(1);

        cc = new Materiales();
        ArrayList<Inventario> materiales = inve.obtenerMaterialesPorFechas(fechaUnMesAtras, fechaActual);
        inve.obtenerSumaPesoYValorPorFechas(fechaUnMesAtras, fechaActual, JTpeso, JTValor);
        for (Inventario material : materiales) {
            String mate = cc.obtenerNombreMaterial(material.getIdMaterial());
            Object[] fila = {material.getId(), material.getPeso(), material.getDescripcion(), mate, material.getValor(), material.getFecha()};
            modeloTabla.addRow(fila);
        }
    }

    public void llenarTabla2(LocalDate fechaSuperior, LocalDate fechainferior, int idMaterial) throws SQLException {
        inve = new CInventario();
        cc = new Materiales();
        String[] va=  {"idInventario", "peso", "Descripcion", "idMaterial", "Valor", "fecha"};
        TInventario.setModel(new DefaultTableModel(va,0 ));
        ArrayList<Inventario> materiales = inve.obtenerMaterialesPorFechasXidMaterial(TInventario, fechainferior, fechaSuperior, idMaterial);
        /* DefaultTableModel modeloTabl=(DefaultTableModel)TInventario.getModel();
        for (Inventario material : materiales) {
            String mate = cc.obtenerNombreMaterial(material.getIdMaterial());
            Object[] fila = {material.getId(), material.getPeso(), material.getDescripcion(), mate, material.getValor(), material.getFecha()};
            modeloTabl.addRow(fila);
        }
        TInventario.setModel(modeloTabl);*/
    }

    public void MetodoComboboxInventario(JComboBox Combobox2) {
        cc = new Materiales();
        List<Material> materiales = cc.listarMateriales();
        //  List<String> nombresMateriales = new ArrayList<>();
        for (Material material : materiales) {
            Combobox2.addItem(material.getNombre());
        }
        // DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>(nombresMateriales.toArray(new String[0]));
        // Combobox2.setModel(modeloComboBox);
    }

    public void MetodoAnoComboBox(JComboBox Combobox) {
        DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>();
        Calendar calendar = Calendar.getInstance();
        int anioActual = calendar.get(Calendar.YEAR);
        modeloComboBox.addElement(String.valueOf(anioActual));
        for (Integer i = anioActual - 5; i <= anioActual + 70; i++) {
            if (i != anioActual) {
                modeloComboBox.addElement(i.toString());
            }
        }
        Combobox.setModel(modeloComboBox);
    }

    public void ImprimirImpresoraTextoPlano(String fileName, Double total, ArrayList<Pesada> listaPesadas) throws IOException {

        String filePath = fileName + ".txt";
        String titulo = "Contabilidad Metales\n\n" + "Numero de cuenta: " + fileName + "\n\n";
        String pesadastx = "";

        DecimalFormat formato = new DecimalFormat("#,###.##");

        for (Pesada pesada : listaPesadas) {
            String[] partes = pesada.toString2().split("=");
            String[] valores = partes[0].split("X");
            double cantidad = Double.parseDouble(valores[1]);
            String cantidadEnCOP = formato.format(cantidad);
            total = Double.parseDouble(partes[1]);
            String totalEnCOP = formato.format(total);
            pesadastx = pesadastx + valores[0] + "X" + cantidadEnCOP + "=" + totalEnCOP + "\n";

        }

        // Crear y escribir en el archivo de texto
        try {
            formato = new DecimalFormat("#,###.##");
            Numero_en_formato_COP = formato.format(total);

            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(titulo + pesadastx + "Total cuenta: " + Numero_en_formato_COP);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leer el archivo de texto
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Obtener las impresoras disponibles
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        // Verificar si hay impresoras disponibles
        if (printServices.length > 0) {
            // Imprimir el archivo de texto
            try {
                // Crear el documento a imprimir
                // Crear el documento a imprimir
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                InputStream inputStream = new ByteArrayInputStream(content.toString().getBytes());
                Doc doc = new SimpleDoc(inputStream, flavor, null);

                // Configurar la impresión
                PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
                PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
                DocPrintJob printJob = printService.createPrintJob();

                // Enviar el documento a imprimir
                printJob.print(doc, attributeSet);
            } catch (PrintException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se encontraron impresoras disponibles.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelCuenta = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        TCuentaXC = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        DeudaLabel = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        TCuentaXC1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabelRecuperacion_de_hierro = new javax.swing.JLabel();
        jPanelMenu = new javax.swing.JPanel();
        BotonabonarCC1 = new javax.swing.JButton();
        jPanelBoton_Abonar = new javax.swing.JPanel();
        BotonabonarCC = new javax.swing.JButton();
        jPanelBoton_Cancelar_cuenta = new javax.swing.JPanel();
        CancelarCuenta = new javax.swing.JButton();
        jPanelNuevaCuenta = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel3GenerarFactura = new javax.swing.JPanel();
        BotonabonarCC2 = new javax.swing.JButton();
        jLabelTexto_Cuenta = new javax.swing.JLabel();
        jLabelDegradadoMenu = new javax.swing.JLabel();
        jPanelMen2 = new javax.swing.JPanel();
        TipoDePersonaComboBox = new javax.swing.JComboBox<>();
        jLabeltipo_de_cuenta = new javax.swing.JLabel();
        jLabelCategoria = new javax.swing.JLabel();
        JlabelPersonasregistradas = new javax.swing.JLabel();
        jLabelPersonasregistradas_icono = new javax.swing.JLabel();
        jLabelCategoria_icono = new javax.swing.JLabel();
        tipo_Cuenta = new javax.swing.JComboBox<>();
        ListaClientesCuentaCB = new javax.swing.JComboBox<>();
        jLabelTipo_de_cuenta = new javax.swing.JLabel();
        jPanelPersonas = new javax.swing.JPanel();
        jLabelPersonas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TClientes = new javax.swing.JTable();
        jLabelRecuperacion_de_hierro2 = new javax.swing.JLabel();
        jPanelMenuPersonas = new javax.swing.JPanel();
        jPanelBoton_Abonar1 = new javax.swing.JPanel();
        BAgregarClientes = new javax.swing.JButton();
        jPanelEditarPersona = new javax.swing.JPanel();
        EditarCliente = new javax.swing.JButton();
        jPanelEliminarProveedor = new javax.swing.JPanel();
        eliminarPersona = new javax.swing.JButton();
        jLabelDegradadoMenu_Personas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelIdentificacion = new javax.swing.JLabel();
        jPanelIngresos_y_egresos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCajaMenor = new javax.swing.JTable();
        jLabelRecuperacion_de_hierro3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelDegradadoMenu_Ingresis_y_egresos = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        Descripcion = new javax.swing.JTextField();
        Valor_dinero = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        EliminarTipoMovimiento = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Tipo = new javax.swing.JComboBox<>();
        TipoInforme = new javax.swing.JComboBox<>();
        TipoMovimiento = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanelPrestamos = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TPrestamos = new javax.swing.JTable();
        jPanelMenuPrestamos = new javax.swing.JPanel();
        jPanelRegistrar_Prestamo = new javax.swing.JPanel();
        jButtonRegistrar_Prestamo = new javax.swing.JButton();
        jPanelCancelar_Prestamo = new javax.swing.JPanel();
        jButtonCancelar_Prestamo = new javax.swing.JButton();
        jPanelActualizar_prestamo = new javax.swing.JPanel();
        jButtonActualizar_Prestamo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabelRecuperacion_de_hierro4 = new javax.swing.JLabel();
        jLabelDegradadoMenu_Prestamos = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelInventario = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TInventario = new javax.swing.JTable();
        jLabelInventario = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabelRecuperacion_de_hierro5 = new javax.swing.JLabel();
        jLabelDegradadoMenu_Inventario = new javax.swing.JLabel();
        jPanelMenuInventario = new javax.swing.JPanel();
        JTpeso = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JTValor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        CalendarioSuperior = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        Calendarioinferior = new com.toedter.calendar.JDateChooser();
        jPanelBoton_AplicarFiltro = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        ListMaterialesInve = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1480, 650));
        setResizable(false);
        setSize(new java.awt.Dimension(1480, 650));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1480, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(1480, 650));
        jPanel1.setPreferredSize(new java.awt.Dimension(1480, 650));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jTabbedPane1.setBackground(new java.awt.Color(233, 233, 233));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1490, 2000));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanelCuenta.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCuenta.setMinimumSize(new java.awt.Dimension(1480, 650));
        jPanelCuenta.setPreferredSize(new java.awt.Dimension(1480, 650));
        jPanelCuenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TCuentaXC.setBackground(new java.awt.Color(241, 253, 253));
        TCuentaXC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TCuentaXC.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        TCuentaXC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "a", "b", "c", "d"
            }
        ));
        TCuentaXC.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TCuentaXC.setSelectionBackground(new java.awt.Color(255, 255, 153));
        jScrollPane12.setViewportView(TCuentaXC);
        if (TCuentaXC.getColumnModel().getColumnCount() > 0) {
            TCuentaXC.getColumnModel().getColumn(0).setMinWidth(80);
            TCuentaXC.getColumnModel().getColumn(0).setPreferredWidth(80);
            TCuentaXC.getColumnModel().getColumn(0).setMaxWidth(80);
            TCuentaXC.getColumnModel().getColumn(1).setMinWidth(80);
            TCuentaXC.getColumnModel().getColumn(1).setPreferredWidth(80);
            TCuentaXC.getColumnModel().getColumn(1).setMaxWidth(80);
            TCuentaXC.getColumnModel().getColumn(2).setMinWidth(80);
            TCuentaXC.getColumnModel().getColumn(2).setPreferredWidth(80);
            TCuentaXC.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        jPanelCuenta.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(748, 100, -1, -1));

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("CUENTAS POR COBRAR:");
        jPanelCuenta.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 15, -1, -1));

        DeudaLabel.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        DeudaLabel.setForeground(new java.awt.Color(51, 51, 51));
        DeudaLabel.setText("500.000 $");
        jPanelCuenta.add(DeudaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1018, 60, 180, 34));

        jScrollPane13.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane13.setBorder(null);
        jScrollPane13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        TCuentaXC1.setBackground(new java.awt.Color(241, 253, 253));
        TCuentaXC1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        TCuentaXC1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "fecha", "Valor", "Cuenta", "tipoCuenta"
            }
        ));
        TCuentaXC1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TCuentaXC1.setInheritsPopupMenu(true);
        TCuentaXC1.setSelectionBackground(new java.awt.Color(255, 255, 153));
        TCuentaXC1.setShowGrid(true);
        TCuentaXC1.setUpdateSelectionOnSort(false);
        TCuentaXC1.setVerifyInputWhenFocusTarget(false);
        jScrollPane13.setViewportView(TCuentaXC1);
        if (TCuentaXC1.getColumnModel().getColumnCount() > 0) {
            TCuentaXC1.getColumnModel().getColumn(0).setMinWidth(20);
            TCuentaXC1.getColumnModel().getColumn(0).setPreferredWidth(20);
            TCuentaXC1.getColumnModel().getColumn(0).setMaxWidth(20);
            TCuentaXC1.getColumnModel().getColumn(1).setMinWidth(100);
            TCuentaXC1.getColumnModel().getColumn(1).setPreferredWidth(100);
            TCuentaXC1.getColumnModel().getColumn(1).setMaxWidth(100);
            TCuentaXC1.getColumnModel().getColumn(2).setMinWidth(30);
            TCuentaXC1.getColumnModel().getColumn(2).setPreferredWidth(30);
            TCuentaXC1.getColumnModel().getColumn(2).setMaxWidth(30);
        }

        jPanelCuenta.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 100, 450, -1));

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Capital:");
        jPanelCuenta.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(922, 58, -1, -1));

        jLabel12.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("500.000 $");
        jPanelCuenta.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1018, 10, -1, -1));

        jLabelRecuperacion_de_hierro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelCuenta.add(jLabelRecuperacion_de_hierro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 220));

        jPanelMenu.setBackground(new java.awt.Color(234, 250, 254));
        jPanelMenu.setPreferredSize(new java.awt.Dimension(297, 300));

        BotonabonarCC1.setBackground(new java.awt.Color(218, 247, 254));
        BotonabonarCC1.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        BotonabonarCC1.setForeground(new java.awt.Color(51, 51, 51));
        BotonabonarCC1.setText("Generar factura");
        BotonabonarCC1.setBorderPainted(false);
        BotonabonarCC1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonabonarCC1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotonabonarCC1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotonabonarCC1MouseExited(evt);
            }
        });
        BotonabonarCC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonabonarCC1ActionPerformed(evt);
            }
        });

        jPanelBoton_Abonar.setBackground(new java.awt.Color(218, 247, 254));
        jPanelBoton_Abonar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelBoton_Abonar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelBoton_AbonarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelBoton_AbonarMouseExited(evt);
            }
        });

        BotonabonarCC.setBackground(new java.awt.Color(218, 247, 254));
        BotonabonarCC.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        BotonabonarCC.setForeground(new java.awt.Color(51, 51, 51));
        BotonabonarCC.setText("Abonar");
        BotonabonarCC.setBorderPainted(false);
        BotonabonarCC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonabonarCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotonabonarCCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotonabonarCCMouseExited(evt);
            }
        });
        BotonabonarCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonabonarCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBoton_AbonarLayout = new javax.swing.GroupLayout(jPanelBoton_Abonar);
        jPanelBoton_Abonar.setLayout(jPanelBoton_AbonarLayout);
        jPanelBoton_AbonarLayout.setHorizontalGroup(
            jPanelBoton_AbonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelBoton_AbonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBoton_AbonarLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(BotonabonarCC, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(99, Short.MAX_VALUE)))
        );
        jPanelBoton_AbonarLayout.setVerticalGroup(
            jPanelBoton_AbonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
            .addGroup(jPanelBoton_AbonarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(BotonabonarCC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelBoton_Cancelar_cuenta.setBackground(new java.awt.Color(218, 247, 254));
        jPanelBoton_Cancelar_cuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelBoton_Cancelar_cuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelBoton_Cancelar_cuentaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelBoton_Cancelar_cuentaMouseExited(evt);
            }
        });

        CancelarCuenta.setBackground(new java.awt.Color(218, 247, 254));
        CancelarCuenta.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        CancelarCuenta.setForeground(new java.awt.Color(51, 51, 51));
        CancelarCuenta.setText("Cancelar cuenta");
        CancelarCuenta.setBorderPainted(false);
        CancelarCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CancelarCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CancelarCuentaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CancelarCuentaMouseExited(evt);
            }
        });
        CancelarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarCuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBoton_Cancelar_cuentaLayout = new javax.swing.GroupLayout(jPanelBoton_Cancelar_cuenta);
        jPanelBoton_Cancelar_cuenta.setLayout(jPanelBoton_Cancelar_cuentaLayout);
        jPanelBoton_Cancelar_cuentaLayout.setHorizontalGroup(
            jPanelBoton_Cancelar_cuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBoton_Cancelar_cuentaLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(CancelarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBoton_Cancelar_cuentaLayout.setVerticalGroup(
            jPanelBoton_Cancelar_cuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBoton_Cancelar_cuentaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(CancelarCuenta))
        );

        jPanelNuevaCuenta.setBackground(new java.awt.Color(218, 247, 254));
        jPanelNuevaCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelNuevaCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelNuevaCuentaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelNuevaCuentaMouseExited(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(218, 247, 254));
        jButton8.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jButton8.setForeground(new java.awt.Color(51, 51, 51));
        jButton8.setText("Nueva cuenta");
        jButton8.setBorderPainted(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton8MouseExited(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNuevaCuentaLayout = new javax.swing.GroupLayout(jPanelNuevaCuenta);
        jPanelNuevaCuenta.setLayout(jPanelNuevaCuentaLayout);
        jPanelNuevaCuentaLayout.setHorizontalGroup(
            jPanelNuevaCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNuevaCuentaLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelNuevaCuentaLayout.setVerticalGroup(
            jPanelNuevaCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNuevaCuentaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton8))
        );

        jPanel3GenerarFactura.setBackground(new java.awt.Color(218, 247, 254));
        jPanel3GenerarFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3GenerarFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3GenerarFacturaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3GenerarFacturaMouseExited(evt);
            }
        });

        BotonabonarCC2.setBackground(new java.awt.Color(218, 247, 254));
        BotonabonarCC2.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        BotonabonarCC2.setForeground(new java.awt.Color(51, 51, 51));
        BotonabonarCC2.setText("Generar factura 2");
        BotonabonarCC2.setBorderPainted(false);
        BotonabonarCC2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonabonarCC2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotonabonarCC2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotonabonarCC2MouseExited(evt);
            }
        });
        BotonabonarCC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonabonarCC2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3GenerarFacturaLayout = new javax.swing.GroupLayout(jPanel3GenerarFactura);
        jPanel3GenerarFactura.setLayout(jPanel3GenerarFacturaLayout);
        jPanel3GenerarFacturaLayout.setHorizontalGroup(
            jPanel3GenerarFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3GenerarFacturaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(BotonabonarCC2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3GenerarFacturaLayout.setVerticalGroup(
            jPanel3GenerarFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3GenerarFacturaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BotonabonarCC2))
        );

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelNuevaCuenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBoton_Cancelar_cuenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBoton_Abonar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BotonabonarCC1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addComponent(jPanel3GenerarFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(BotonabonarCC1)
                .addGap(6, 6, 6)
                .addComponent(jPanelBoton_Abonar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanelBoton_Cancelar_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanelNuevaCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel3GenerarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelCuenta.add(jPanelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 226, 300, 304));

        jLabelTexto_Cuenta.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTexto_Cuenta.setFont(new java.awt.Font("Comic Sans MS", 0, 45)); // NOI18N
        jLabelTexto_Cuenta.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTexto_Cuenta.setText("Cuentas");
        jPanelCuenta.add(jLabelTexto_Cuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 15, -1, -1));

        jLabelDegradadoMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelCuenta.add(jLabelDegradadoMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 910, 100));

        jPanelMen2.setBackground(new java.awt.Color(234, 250, 254));

        TipoDePersonaComboBox.setBackground(new java.awt.Color(218, 247, 254));
        TipoDePersonaComboBox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        TipoDePersonaComboBox.setForeground(new java.awt.Color(51, 51, 51));
        TipoDePersonaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Proveedor", "Cliente" }));
        TipoDePersonaComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TipoDePersonaComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoDePersonaComboBoxActionPerformed(evt);
            }
        });

        jLabelCategoria.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabelCategoria.setForeground(new java.awt.Color(51, 51, 51));
        jLabelCategoria.setText("Categoría");

        JlabelPersonasregistradas.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        JlabelPersonasregistradas.setForeground(new java.awt.Color(51, 51, 51));
        JlabelPersonasregistradas.setText("Personas registradas");

        tipo_Cuenta.setBackground(new java.awt.Color(218, 247, 254));
        tipo_Cuenta.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tipo_Cuenta.setForeground(new java.awt.Color(51, 51, 51));
        tipo_Cuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compra", "Venta" }));
        tipo_Cuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tipo_Cuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipo_CuentaMouseClicked(evt);
            }
        });
        tipo_Cuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_CuentaActionPerformed(evt);
            }
        });

        ListaClientesCuentaCB.setBackground(new java.awt.Color(218, 247, 254));
        ListaClientesCuentaCB.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        ListaClientesCuentaCB.setForeground(new java.awt.Color(51, 51, 51));
        ListaClientesCuentaCB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListaClientesCuentaCB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaClientesCuentaCBMouseClicked(evt);
            }
        });
        ListaClientesCuentaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListaClientesCuentaCBActionPerformed(evt);
            }
        });

        jLabelTipo_de_cuenta.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabelTipo_de_cuenta.setForeground(new java.awt.Color(51, 51, 51));
        jLabelTipo_de_cuenta.setText("Tipo de cuenta");

        javax.swing.GroupLayout jPanelMen2Layout = new javax.swing.GroupLayout(jPanelMen2);
        jPanelMen2.setLayout(jPanelMen2Layout);
        jPanelMen2Layout.setHorizontalGroup(
            jPanelMen2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMen2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMen2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelPersonasregistradas_icono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCategoria_icono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabeltipo_de_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMen2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TipoDePersonaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTipo_de_cuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tipo_Cuenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ListaClientesCuentaCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JlabelPersonasregistradas, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMen2Layout.setVerticalGroup(
            jPanelMen2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMen2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMen2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMen2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(JlabelPersonasregistradas))
                    .addComponent(jLabelPersonasregistradas_icono, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListaClientesCuentaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMen2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabeltipo_de_cuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTipo_de_cuenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TipoDePersonaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanelMen2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCategoria_icono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCategoria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipo_Cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelCuenta.add(jPanelMen2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 260, 530));

        jTabbedPane1.addTab("Cuentas", jPanelCuenta);

        jPanelPersonas.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPersonas.setForeground(new java.awt.Color(255, 255, 255));
        jPanelPersonas.setPreferredSize(new java.awt.Dimension(1480, 650));
        jPanelPersonas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPersonas.setFont(new java.awt.Font("Comic Sans MS", 0, 45)); // NOI18N
        jLabelPersonas.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPersonas.setText("Personas");
        jPanelPersonas.add(jLabelPersonas, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 15, -1, -1));

        TClientes.setBackground(new java.awt.Color(241, 253, 253));
        TClientes.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        TClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nombre", "Celular", "identificacion", "TipoDocumento_nombre", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TClientes.setEditingColumn(0);
        TClientes.setEditingRow(0);
        TClientes.setSelectionBackground(new java.awt.Color(255, 255, 153));
        jScrollPane2.setViewportView(TClientes);

        jPanelPersonas.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 900, 430));

        jLabelRecuperacion_de_hierro2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPersonas.add(jLabelRecuperacion_de_hierro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 220));

        jPanelMenuPersonas.setBackground(new java.awt.Color(234, 250, 254));
        jPanelMenuPersonas.setPreferredSize(new java.awt.Dimension(297, 300));

        jPanelBoton_Abonar1.setBackground(new java.awt.Color(218, 247, 254));
        jPanelBoton_Abonar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelBoton_Abonar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelBoton_Abonar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelBoton_Abonar1MouseExited(evt);
            }
        });

        BAgregarClientes.setBackground(new java.awt.Color(218, 247, 254));
        BAgregarClientes.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        BAgregarClientes.setForeground(new java.awt.Color(51, 51, 51));
        BAgregarClientes.setText("Agregar proveedor");
        BAgregarClientes.setToolTipText("");
        BAgregarClientes.setBorderPainted(false);
        BAgregarClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BAgregarClientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BAgregarClientesMouseExited(evt);
            }
        });
        BAgregarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBoton_Abonar1Layout = new javax.swing.GroupLayout(jPanelBoton_Abonar1);
        jPanelBoton_Abonar1.setLayout(jPanelBoton_Abonar1Layout);
        jPanelBoton_Abonar1Layout.setHorizontalGroup(
            jPanelBoton_Abonar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBoton_Abonar1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(BAgregarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanelBoton_Abonar1Layout.setVerticalGroup(
            jPanelBoton_Abonar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBoton_Abonar1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BAgregarClientes)
                .addGap(112, 112, 112))
        );

        jPanelEditarPersona.setBackground(new java.awt.Color(218, 247, 254));
        jPanelEditarPersona.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelEditarPersona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelEditarPersonaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelEditarPersonaMouseExited(evt);
            }
        });

        EditarCliente.setBackground(new java.awt.Color(218, 247, 254));
        EditarCliente.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        EditarCliente.setForeground(new java.awt.Color(51, 51, 51));
        EditarCliente.setText("Editar persona");
        EditarCliente.setBorderPainted(false);
        EditarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EditarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EditarClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EditarClienteMouseExited(evt);
            }
        });
        EditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelEditarPersonaLayout = new javax.swing.GroupLayout(jPanelEditarPersona);
        jPanelEditarPersona.setLayout(jPanelEditarPersonaLayout);
        jPanelEditarPersonaLayout.setHorizontalGroup(
            jPanelEditarPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditarPersonaLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(EditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanelEditarPersonaLayout.setVerticalGroup(
            jPanelEditarPersonaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EditarCliente)
        );

        jPanelEliminarProveedor.setBackground(new java.awt.Color(218, 247, 254));
        jPanelEliminarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelEliminarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelEliminarProveedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelEliminarProveedorMouseExited(evt);
            }
        });

        eliminarPersona.setBackground(new java.awt.Color(218, 247, 254));
        eliminarPersona.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        eliminarPersona.setForeground(new java.awt.Color(51, 51, 51));
        eliminarPersona.setText("Eliminar proveedor");
        eliminarPersona.setBorderPainted(false);
        eliminarPersona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eliminarPersonaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                eliminarPersonaMouseExited(evt);
            }
        });
        eliminarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPersonaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelEliminarProveedorLayout = new javax.swing.GroupLayout(jPanelEliminarProveedor);
        jPanelEliminarProveedor.setLayout(jPanelEliminarProveedorLayout);
        jPanelEliminarProveedorLayout.setHorizontalGroup(
            jPanelEliminarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelEliminarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEliminarProveedorLayout.createSequentialGroup()
                    .addContainerGap(31, Short.MAX_VALUE)
                    .addComponent(eliminarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(18, Short.MAX_VALUE)))
        );
        jPanelEliminarProveedorLayout.setVerticalGroup(
            jPanelEliminarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(jPanelEliminarProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelEliminarProveedorLayout.createSequentialGroup()
                    .addComponent(eliminarPersona)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanelMenuPersonasLayout = new javax.swing.GroupLayout(jPanelMenuPersonas);
        jPanelMenuPersonas.setLayout(jPanelMenuPersonasLayout);
        jPanelMenuPersonasLayout.setHorizontalGroup(
            jPanelMenuPersonasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuPersonasLayout.createSequentialGroup()
                .addGroup(jPanelMenuPersonasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelEditarPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelEliminarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanelBoton_Abonar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelMenuPersonasLayout.setVerticalGroup(
            jPanelMenuPersonasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuPersonasLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanelBoton_Abonar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanelEditarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanelEliminarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelPersonas.add(jPanelMenuPersonas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 226, 300, 304));

        jLabelDegradadoMenu_Personas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPersonas.add(jLabelDegradadoMenu_Personas, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 910, 100));

        jPanel2.setBackground(new java.awt.Color(234, 250, 254));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelIdentificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabelIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(249, Short.MAX_VALUE))
        );

        jPanelPersonas.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 260, 530));

        jTabbedPane1.addTab("Personas", jPanelPersonas);

        jPanelIngresos_y_egresos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelIngresos_y_egresos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaCajaMenor.setBackground(new java.awt.Color(241, 253, 253));
        TablaCajaMenor.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        TablaCajaMenor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        TablaCajaMenor.setSelectionBackground(new java.awt.Color(255, 255, 153));
        jScrollPane1.setViewportView(TablaCajaMenor);
        if (TablaCajaMenor.getColumnModel().getColumnCount() > 0) {
            TablaCajaMenor.getColumnModel().getColumn(0).setMinWidth(50);
            TablaCajaMenor.getColumnModel().getColumn(0).setPreferredWidth(50);
            TablaCajaMenor.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jPanelIngresos_y_egresos.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 900, 430));

        jLabelRecuperacion_de_hierro3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelIngresos_y_egresos.add(jLabelRecuperacion_de_hierro3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 220));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 45)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ingresos y egresos");
        jPanelIngresos_y_egresos.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 15, 410, 70));

        jLabelDegradadoMenu_Ingresis_y_egresos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelIngresos_y_egresos.add(jLabelDegradadoMenu_Ingresis_y_egresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 910, 100));

        jPanel5.setBackground(new java.awt.Color(234, 250, 254));

        Descripcion.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        Descripcion.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        Descripcion.setSelectionColor(new java.awt.Color(200, 244, 254));

        Valor_dinero.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        Valor_dinero.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        Valor_dinero.setSelectionColor(new java.awt.Color(200, 244, 254));
        Valor_dinero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Valor_dineroActionPerformed(evt);
            }
        });
        Valor_dinero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Valor_dineroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Valor_dineroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Valor_dineroKeyTyped(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(218, 247, 254));
        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel7MouseExited(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(218, 247, 254));
        jButton9.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jButton9.setText("Agregar movimiento");
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton9MouseExited(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton9))
        );

        jPanel8.setBackground(new java.awt.Color(218, 247, 254));
        jPanel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel8MouseExited(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(218, 247, 254));
        jButton6.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jButton6.setText("Agregar tipo movimiento");
        jButton6.setToolTipText("");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton6))
        );

        jPanel9.setBackground(new java.awt.Color(218, 247, 254));
        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel9MouseExited(evt);
            }
        });

        EliminarTipoMovimiento.setBackground(new java.awt.Color(218, 247, 254));
        EliminarTipoMovimiento.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        EliminarTipoMovimiento.setText("Eliminar tipo  movimiento");
        EliminarTipoMovimiento.setBorderPainted(false);
        EliminarTipoMovimiento.setContentAreaFilled(false);
        EliminarTipoMovimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EliminarTipoMovimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EliminarTipoMovimientoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EliminarTipoMovimientoMouseExited(evt);
            }
        });
        EliminarTipoMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarTipoMovimientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(EliminarTipoMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(EliminarTipoMovimiento))
        );

        jPanel10.setBackground(new java.awt.Color(218, 247, 254));
        jPanel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel10MouseExited(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(218, 247, 254));
        jButton10.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jButton10.setText("Generar informe");
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton10MouseExited(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton10))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Descripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(Valor_dinero))
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Valor_dinero, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanelIngresos_y_egresos.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 226, 300, 304));

        jPanel11.setBackground(new java.awt.Color(234, 250, 254));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Ingreso o egreso:");

        Tipo.setBackground(new java.awt.Color(218, 247, 254));
        Tipo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Egresos", "Ingreso" }));
        Tipo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoActionPerformed(evt);
            }
        });

        TipoInforme.setBackground(new java.awt.Color(218, 247, 254));
        TipoInforme.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        TipoInforme.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General", "por movimiento", "por fechas" }));
        TipoInforme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TipoInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoInformeActionPerformed(evt);
            }
        });

        TipoMovimiento.setBackground(new java.awt.Color(218, 247, 254));
        TipoMovimiento.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        TipoMovimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TipoMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoMovimientoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Tipo de movimiento");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Tipo de transacción");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TipoInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TipoMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TipoInforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TipoMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(299, Short.MAX_VALUE))
        );

        jPanelIngresos_y_egresos.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 290, 530));

        jTabbedPane1.addTab("Ingresos y egresos ", jPanelIngresos_y_egresos);

        jPanelPrestamos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPrestamos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TPrestamos.setBackground(new java.awt.Color(241, 253, 253));
        TPrestamos.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        TPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TPrestamos.setSelectionBackground(new java.awt.Color(255, 255, 153));
        TPrestamos.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jScrollPane4.setViewportView(TPrestamos);

        jPanelPrestamos.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 900, 430));

        jPanelMenuPrestamos.setBackground(new java.awt.Color(234, 250, 254));
        jPanelMenuPrestamos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelRegistrar_Prestamo.setBackground(new java.awt.Color(218, 247, 254));
        jPanelRegistrar_Prestamo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelRegistrar_Prestamo.setPreferredSize(new java.awt.Dimension(298, 30));
        jPanelRegistrar_Prestamo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelRegistrar_PrestamoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelRegistrar_PrestamoMouseExited(evt);
            }
        });
        jPanelRegistrar_Prestamo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonRegistrar_Prestamo.setBackground(new java.awt.Color(218, 247, 254));
        jButtonRegistrar_Prestamo.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jButtonRegistrar_Prestamo.setForeground(new java.awt.Color(51, 51, 51));
        jButtonRegistrar_Prestamo.setText("Registrar préstamo");
        jButtonRegistrar_Prestamo.setBorderPainted(false);
        jButtonRegistrar_Prestamo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRegistrar_Prestamo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonRegistrar_PrestamoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonRegistrar_PrestamoMouseExited(evt);
            }
        });
        jButtonRegistrar_Prestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrar_PrestamoActionPerformed(evt);
            }
        });
        jPanelRegistrar_Prestamo.add(jButtonRegistrar_Prestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 0, 239, 31));

        jPanelMenuPrestamos.add(jPanelRegistrar_Prestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 17, 300, 37));

        jPanelCancelar_Prestamo.setBackground(new java.awt.Color(218, 247, 254));
        jPanelCancelar_Prestamo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelCancelar_Prestamo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelCancelar_PrestamoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelCancelar_PrestamoMouseExited(evt);
            }
        });
        jPanelCancelar_Prestamo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonCancelar_Prestamo.setBackground(new java.awt.Color(218, 247, 254));
        jButtonCancelar_Prestamo.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jButtonCancelar_Prestamo.setForeground(new java.awt.Color(51, 51, 51));
        jButtonCancelar_Prestamo.setText("Cancelar préstamo ");
        jButtonCancelar_Prestamo.setBorderPainted(false);
        jButtonCancelar_Prestamo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCancelar_Prestamo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonCancelar_PrestamoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonCancelar_PrestamoMouseExited(evt);
            }
        });
        jButtonCancelar_Prestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelar_PrestamoActionPerformed(evt);
            }
        });
        jPanelCancelar_Prestamo.add(jButtonCancelar_Prestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 0, 244, -1));

        jPanelMenuPrestamos.add(jPanelCancelar_Prestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 300, 37));

        jPanelActualizar_prestamo.setBackground(new java.awt.Color(218, 247, 254));
        jPanelActualizar_prestamo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelActualizar_prestamo.setPreferredSize(new java.awt.Dimension(300, 35));
        jPanelActualizar_prestamo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelActualizar_prestamoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelActualizar_prestamoMouseExited(evt);
            }
        });
        jPanelActualizar_prestamo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonActualizar_Prestamo.setBackground(new java.awt.Color(218, 247, 254));
        jButtonActualizar_Prestamo.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jButtonActualizar_Prestamo.setForeground(new java.awt.Color(51, 51, 51));
        jButtonActualizar_Prestamo.setText("Actualizar préstamo");
        jButtonActualizar_Prestamo.setBorderPainted(false);
        jButtonActualizar_Prestamo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonActualizar_Prestamo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonActualizar_PrestamoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonActualizar_PrestamoMouseExited(evt);
            }
        });
        jButtonActualizar_Prestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizar_PrestamoActionPerformed(evt);
            }
        });
        jPanelActualizar_prestamo.add(jButtonActualizar_Prestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 0, 248, -1));

        jPanelMenuPrestamos.add(jPanelActualizar_prestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 300, 37));

        jPanelPrestamos.add(jPanelMenuPrestamos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 226, 300, 304));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 45)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Préstamos");
        jPanelPrestamos.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 15, -1, -1));

        jLabelRecuperacion_de_hierro4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPrestamos.add(jLabelRecuperacion_de_hierro4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 220));

        jLabelDegradadoMenu_Prestamos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelPrestamos.add(jLabelDegradadoMenu_Prestamos, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 910, 100));

        jPanel3.setBackground(new java.awt.Color(234, 250, 254));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        jPanelPrestamos.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 290, 530));

        jTabbedPane1.addTab("Préstamos", jPanelPrestamos);

        jPanelInventario.setBackground(new java.awt.Color(255, 255, 255));
        jPanelInventario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TInventario.setBackground(new java.awt.Color(241, 253, 253));
        TInventario.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        TInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdInventario", "Peso", "#Cuenta", "Material", "Valor", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TInventario.setSelectionBackground(new java.awt.Color(255, 255, 153));
        jScrollPane3.setViewportView(TInventario);

        jPanelInventario.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 900, 430));

        jLabelInventario.setBackground(new java.awt.Color(255, 255, 255));
        jLabelInventario.setFont(new java.awt.Font("Comic Sans MS", 0, 45)); // NOI18N
        jLabelInventario.setForeground(new java.awt.Color(255, 255, 255));
        jLabelInventario.setText("Inventario");
        jLabelInventario.setToolTipText("");
        jPanelInventario.add(jLabelInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 15, 240, 70));

        jLabel28.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jPanelInventario.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 50, -1, 38));

        jLabelRecuperacion_de_hierro5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelInventario.add(jLabelRecuperacion_de_hierro5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 220));

        jLabelDegradadoMenu_Inventario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelInventario.add(jLabelDegradadoMenu_Inventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 910, 100));

        jPanelMenuInventario.setBackground(new java.awt.Color(234, 250, 254));

        JTpeso.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        JTpeso.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        JTpeso.setSelectionColor(new java.awt.Color(200, 244, 254));

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel3.setText("Peso (KG)");

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel4.setText("Valor (COP)");

        JTValor.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        JTValor.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        JTValor.setSelectionColor(new java.awt.Color(200, 244, 254));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel8.setText("Fecha inicio:");

        CalendarioSuperior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CalendarioSuperior.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel11.setText("Fecha fin:");

        Calendarioinferior.setBackground(new java.awt.Color(255, 255, 255));
        Calendarioinferior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Calendarioinferior.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jPanelBoton_AplicarFiltro.setBackground(new java.awt.Color(218, 247, 254));
        jPanelBoton_AplicarFiltro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanelBoton_AplicarFiltro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelBoton_AplicarFiltroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelBoton_AplicarFiltroMouseExited(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(218, 247, 254));
        jButton7.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jButton7.setText("Aplicar filtro");
        jButton7.setBorderPainted(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton7MouseExited(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBoton_AplicarFiltroLayout = new javax.swing.GroupLayout(jPanelBoton_AplicarFiltro);
        jPanelBoton_AplicarFiltro.setLayout(jPanelBoton_AplicarFiltroLayout);
        jPanelBoton_AplicarFiltroLayout.setHorizontalGroup(
            jPanelBoton_AplicarFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelBoton_AplicarFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelBoton_AplicarFiltroLayout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(48, Short.MAX_VALUE)))
        );
        jPanelBoton_AplicarFiltroLayout.setVerticalGroup(
            jPanelBoton_AplicarFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
            .addGroup(jPanelBoton_AplicarFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(218, 247, 254));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel4MouseExited(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(218, 247, 254));
        jButton12.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jButton12.setText("Informe general");
        jButton12.setBorderPainted(false);
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton12MouseExited(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton12))
        );

        javax.swing.GroupLayout jPanelMenuInventarioLayout = new javax.swing.GroupLayout(jPanelMenuInventario);
        jPanelMenuInventario.setLayout(jPanelMenuInventarioLayout);
        jPanelMenuInventarioLayout.setHorizontalGroup(
            jPanelMenuInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuInventarioLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuInventarioLayout.createSequentialGroup()
                        .addGroup(jPanelMenuInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JTpeso, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelMenuInventarioLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(JTValor, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())
                    .addGroup(jPanelMenuInventarioLayout.createSequentialGroup()
                        .addGroup(jPanelMenuInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelMenuInventarioLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CalendarioSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelMenuInventarioLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Calendarioinferior, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jPanelBoton_AplicarFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelMenuInventarioLayout.setVerticalGroup(
            jPanelMenuInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTpeso, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTValor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(CalendarioSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenuInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Calendarioinferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelBoton_AplicarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanelInventario.add(jPanelMenuInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 226, 300, 304));

        jPanel6.setBackground(new java.awt.Color(234, 250, 254));

        ListMaterialesInve.setBackground(new java.awt.Color(218, 247, 254));
        ListMaterialesInve.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        ListMaterialesInve.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        ListMaterialesInve.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Material");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(ListMaterialesInve, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(43, 43, 43))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ListMaterialesInve, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
        );

        jPanelInventario.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 290, 530));

        jTabbedPane1.addTab("Inventario", jPanelInventario);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jMenuBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenu1.setText("Acerca de...");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.setFont(new java.awt.Font("Dialog", 2, 13)); // NOI18N
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Guía de usuario");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu2.setFont(new java.awt.Font("Dialog", 2, 13)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        Acerca_de acerca_de = new Acerca_de();
        acerca_de.setVisible(true);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        listaProbedor = null;
        listaProbedor = conp.listaPersonas(selec());
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            // TODO add your handling code here:
            CInventario cv = new CInventario();
            InventarioReportGenerator reporte = new InventarioReportGenerator();
            fechaActual = cv.obtenerFechaUltimoRegistroInventario();
            fechaAnterior = cv.obtenerFechaPrimerRegistroInventario();
            Double peso = cv.obtenerSumaPesoTotalInventario();
            Double cas = cv.obtenerSumaValorTotalInventario();
             ArrayList<Inventario> inventarioLista =cv.obtenerRegistrosInventario();
            reporte.generateReport(peso, cas, inventarioLista, fechaAnterior, fechaActual, material, "informe general inventario.pdf");
        } catch (SQLException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        java.util.Date fechaAnterior1 = Calendarioinferior.getDate();
        java.util.Date fechaActual1 = CalendarioSuperior.getDate();

        long milliseconds = fechaActual1.getTime();
        long milliseconds2 = fechaAnterior1.getTime();

        fechaActual = LocalDate.ofEpochDay(milliseconds / (24 * 60 * 60 * 1000));
        fechaAnterior = LocalDate.ofEpochDay(milliseconds2 / (24 * 60 * 60 * 1000));

        material = ListMaterialesInve.getSelectedItem().toString();
        int idMaterialo = cc.obtenerIdMaterialPorNombre(material);
        inve.obtenerSumaPesoYValorPorFechasYMaterial(fechaAnterior, fechaActual, idMaterialo, JTpeso, JTValor);
        String[] va=  {"IdInventario", "Peso", "Descripción", "IdMaterial", "Valor", "Fecha"};
        TInventario.setModel(new DefaultTableModel(va,0 ));
        ArrayList<Inventario> inventarioList = inve.obtenerMaterialesPorFechasXidMaterial(TInventario, fechaAnterior, fechaActual, idMaterialo);
        InventarioReportGenerator ss = new InventarioReportGenerator();
        // Generar el informe en PDF
        Double peso = Double.parseDouble(JTpeso.getText());
        Double valorTotal = Double.parseDouble(JTValor.getText());
        ss.generateReport(peso, valorTotal, inventarioList, fechaAnterior, fechaActual, material, "Informe_" + fechaActual + ".pdf");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jPanelActualizar_prestamoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelActualizar_prestamoMouseExited
        jPanelActualizar_prestamo.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jButtonActualizar_Prestamo.setBackground(new Color(218, 247, 254));
    }//GEN-LAST:event_jPanelActualizar_prestamoMouseExited

    private void jPanelActualizar_prestamoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelActualizar_prestamoMouseEntered
        jPanelActualizar_prestamo.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jButtonActualizar_Prestamo.setBackground(new Color(182, 238, 252));
    }//GEN-LAST:event_jPanelActualizar_prestamoMouseEntered

    private void jButtonActualizar_PrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizar_PrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonActualizar_PrestamoActionPerformed

    private void jButtonActualizar_PrestamoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonActualizar_PrestamoMouseExited
        jPanelActualizar_prestamo.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jButtonActualizar_Prestamo.setBackground(new Color(218, 247, 254));
    }//GEN-LAST:event_jButtonActualizar_PrestamoMouseExited

    private void jButtonActualizar_PrestamoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonActualizar_PrestamoMouseEntered
        jPanelActualizar_prestamo.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jButtonActualizar_Prestamo.setBackground(new Color(182, 238, 252));
    }//GEN-LAST:event_jButtonActualizar_PrestamoMouseEntered

    private void jPanelCancelar_PrestamoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCancelar_PrestamoMouseExited
        jPanelCancelar_Prestamo.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jButtonCancelar_Prestamo.setBackground(new Color(218, 247, 254));
    }//GEN-LAST:event_jPanelCancelar_PrestamoMouseExited

    private void jPanelCancelar_PrestamoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCancelar_PrestamoMouseEntered
        jPanelCancelar_Prestamo.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jButtonCancelar_Prestamo.setBackground(new Color(182, 238, 252));
    }//GEN-LAST:event_jPanelCancelar_PrestamoMouseEntered

    private void jButtonCancelar_PrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelar_PrestamoActionPerformed
        // TODCPrestamoO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) TPrestamos.getModel();
        String aa = (String) modelo.getValueAt(TPrestamos.getSelectedRow(), 0);
        Integer numeroid = Integer.valueOf(aa);
        String fac = (String) modelo.getValueAt(TPrestamos.getSelectedRow(), 1);
        Integer numeroidpersona = Integer.valueOf(fac);
        conp = new CPersona();
        persona pepe = conp.Leerpersonas(numeroidpersona);
        int i = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea dar por cancelada esa deuda de " + pepe.getNombre() + "?");
        if (i == 0) {
            prestamos.RemoverPrestamos(numeroid);
            JOptionPane.showConfirmDialog(null, "Se ha eliminado con éxito");
            TPrestamos.remove(numeroid);
        }
    }//GEN-LAST:event_jButtonCancelar_PrestamoActionPerformed

    private void jButtonCancelar_PrestamoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancelar_PrestamoMouseExited
        jPanelCancelar_Prestamo.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jButtonCancelar_Prestamo.setBackground(new Color(218, 247, 254));
    }//GEN-LAST:event_jButtonCancelar_PrestamoMouseExited

    private void jButtonCancelar_PrestamoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancelar_PrestamoMouseEntered
        jPanelCancelar_Prestamo.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jButtonCancelar_Prestamo.setBackground(new Color(182, 238, 252));
    }//GEN-LAST:event_jButtonCancelar_PrestamoMouseEntered

    private void jPanelRegistrar_PrestamoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRegistrar_PrestamoMouseExited
        jPanelRegistrar_Prestamo.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jButtonRegistrar_Prestamo.setBackground(new Color(218, 247, 254));
    }//GEN-LAST:event_jPanelRegistrar_PrestamoMouseExited

    private void jPanelRegistrar_PrestamoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelRegistrar_PrestamoMouseEntered
        jPanelRegistrar_Prestamo.setBackground(new Color(182, 238, 252));// Restablecer color de fondo predeterminado
        jButtonRegistrar_Prestamo.setBackground(new Color(182, 238, 252));
    }//GEN-LAST:event_jPanelRegistrar_PrestamoMouseEntered

    private void jButtonRegistrar_PrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrar_PrestamoActionPerformed
        // TODO add your handling code here:
        RegistrarPrestamos = new RegistrarPrestamo(TPrestamos);
        RegistrarPrestamos.setVisible(true);
    }//GEN-LAST:event_jButtonRegistrar_PrestamoActionPerformed

    private void jButtonRegistrar_PrestamoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRegistrar_PrestamoMouseExited
        jPanelRegistrar_Prestamo.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jButtonRegistrar_Prestamo.setBackground(new Color(218, 247, 254));
    }//GEN-LAST:event_jButtonRegistrar_PrestamoMouseExited

    private void jButtonRegistrar_PrestamoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRegistrar_PrestamoMouseEntered
        jPanelRegistrar_Prestamo.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jButtonRegistrar_Prestamo.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jButtonRegistrar_PrestamoMouseEntered

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:

        try {
            String tipoi = TipoInforme.getSelectedItem().toString();
            String TipoMovimientos = TipoMovimiento.getSelectedItem().toString();
            CajaMenorReporteGenerator reporte = new CajaMenorReporteGenerator();
            CajaMenorController caja = new CajaMenorController();
            List<MovimientoCajaMenor> movimientosCajaMenor = null;
            movimientosCajaMenor = caja.obtenerMovimientosCajaMenor();
            reporte.generateReport(caja.obtenerSumaMontoMovimientosCajaMenor(), movimientosCajaMenor, caja.obtenerFechaPrimerRegistro(), caja.obtenerFechaUltimoRegistro(), TipoMovimientos, "ReporteGeneral.pdf");
        } catch (SQLException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void TipoInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoInformeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipoInformeActionPerformed

    private void EliminarTipoMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarTipoMovimientoActionPerformed
        // TODO add your handling code here:
        TipoMovimientoController nuevo = new TipoMovimientoController();
        nuevo.eliminarTipoMovimientoPorNombre(TipoMovimiento.getSelectedItem().toString());
    }//GEN-LAST:event_EliminarTipoMovimientoActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String tipom = TipoMovimiento.getSelectedItem().toString();
        String Descripcion = this.Descripcion.getText();
        Double monto = Double.parseDouble(Valor_dinero.getText().replaceAll(",", "").replaceAll("\\.", ""));
        System.out.println(monto);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date fechaActual = new java.sql.Date(utilDate.getTime());
        String Tipoget = Tipo.getSelectedItem().toString();
        CajaMenorController cajamenorcontroller = new CajaMenorController();
        TipoMovimientoController tipomovimientocontrolador = new TipoMovimientoController();
        TipoMovimiento tipomode = tipomovimientocontrolador.obtenerTipoMovimientoPorNombre(tipom);
        Boolean es_gasto = false;
        if (tipom == "Ingresos") {
            es_gasto = true;
        }
        MovimientoCajaMenor modelo = new MovimientoCajaMenor(fechaActual, tipomode.getId(), monto, Descripcion, es_gasto);
        if (Tipoget == "Egresos") {
            cajamenorcontroller.agregarMovimientoCajaMenor(modelo, false);
        } else {
            cajamenorcontroller.agregarMovimientoCajaMenor(modelo, true);
        }
        this.Descripcion.setText("");
        this.Valor_dinero.setText("");
        CajaMenorController caja = new CajaMenorController();
        caja.obtenerMovimientosCajaMenortabla(TablaCajaMenor);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void TipoMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoMovimientoActionPerformed
        // TODO add your handling code here:
        CajaMenorController caja = new CajaMenorController();
        String nombre = TipoMovimiento.getSelectedItem().toString();
        caja.obtenerMovimientosCajaMenortabla_x_nombre(TablaCajaMenor, nombre);
    }//GEN-LAST:event_TipoMovimientoActionPerformed

    private void TipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoActionPerformed
        // TODO add your handling code here:
        TipoMovimientoController nuevo = new TipoMovimientoController();
        CajaMenorController caja = new CajaMenorController();
        switch (Tipo.getSelectedItem().toString()) {
            case "Ingreso":
                String[] listaGastos = nuevo.obtenerTiposGastos2();
                DefaultComboBoxModel cx = new DefaultComboBoxModel(listaGastos);
                TipoMovimiento.setModel(cx);
                caja.obtenerMovimientosCajaMenortablaIngresos(TablaCajaMenor);
                break;

            case "Egresos":
                String[] listaIngresos = nuevo.obtenerTiposIngresos2();
                DefaultComboBoxModel cc = new DefaultComboBoxModel(listaIngresos);
                TipoMovimiento.setModel(cc);
                caja.obtenerMovimientosCajaMenortablaEgresos(TablaCajaMenor);
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_TipoActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        FTipoMovimiento objetoFTipoMovimiento = new FTipoMovimiento();
        objetoFTipoMovimiento.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void Valor_dineroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Valor_dineroKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Valor_dineroKeyPressed

    private void Valor_dineroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Valor_dineroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Valor_dineroActionPerformed

    private void jPanelEliminarProveedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEliminarProveedorMouseExited
        jPanelEliminarProveedor.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        eliminarPersona.setBackground(new Color(218, 247, 254));
    }//GEN-LAST:event_jPanelEliminarProveedorMouseExited

    private void jPanelEliminarProveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEliminarProveedorMouseEntered
        jPanelEliminarProveedor.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        eliminarPersona.setBackground(new Color(182, 238, 252));
    }//GEN-LAST:event_jPanelEliminarProveedorMouseEntered

    private void eliminarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarPersonaActionPerformed
        // TODO add your handling code here:
        CPersona perso = new CPersona();
        perso.eliminarPersona(listaProbedor.get(TClientes.getSelectedRow()).getId());
        perso.listaPersonas(TClientes, selec());
    }//GEN-LAST:event_eliminarPersonaActionPerformed

    private void eliminarPersonaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarPersonaMouseExited
        jPanelEliminarProveedor.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        eliminarPersona.setBackground(new Color(218, 247, 254));
    }//GEN-LAST:event_eliminarPersonaMouseExited

    private void eliminarPersonaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarPersonaMouseEntered
        jPanelEliminarProveedor.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        eliminarPersona.setBackground(new Color(182, 238, 252));
    }//GEN-LAST:event_eliminarPersonaMouseEntered

    private void jPanelEditarPersonaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEditarPersonaMouseExited
        jPanelEditarPersona.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        EditarCliente.setBackground(new Color(218, 247, 254));
    }//GEN-LAST:event_jPanelEditarPersonaMouseExited

    private void jPanelEditarPersonaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEditarPersonaMouseEntered
        jPanelEditarPersona.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        EditarCliente.setBackground(new Color(182, 238, 252));
    }//GEN-LAST:event_jPanelEditarPersonaMouseEntered

    private void EditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarClienteActionPerformed
        // TODO add your handling code here:
        EditarProveedor Editar = new EditarProveedor(listaProbedor.get(TClientes.getSelectedRow()).getId());
        Editar.setVisible(true);
    }//GEN-LAST:event_EditarClienteActionPerformed

    private void EditarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarClienteMouseExited
        jPanelEditarPersona.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        EditarCliente.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
    }//GEN-LAST:event_EditarClienteMouseExited

    private void EditarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarClienteMouseEntered
        jPanelEditarPersona.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        EditarCliente.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_EditarClienteMouseEntered

    private void jPanelBoton_Abonar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoton_Abonar1MouseExited
        jPanelBoton_Abonar1.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        BAgregarClientes.setBackground(new Color(218, 247, 254));
    }//GEN-LAST:event_jPanelBoton_Abonar1MouseExited

    private void jPanelBoton_Abonar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoton_Abonar1MouseEntered
        jPanelBoton_Abonar1.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        BAgregarClientes.setBackground(new Color(182, 238, 252));
    }//GEN-LAST:event_jPanelBoton_Abonar1MouseEntered

    private void BAgregarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarClientesActionPerformed
        RegistrarPersona registrarPersona = new RegistrarPersona(TClientes);
        registrarPersona.setVisible(true);
    }//GEN-LAST:event_BAgregarClientesActionPerformed

    private void BAgregarClientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BAgregarClientesMouseExited
        jPanelBoton_Abonar1.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        BAgregarClientes.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_BAgregarClientesMouseExited

    private void BAgregarClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BAgregarClientesMouseEntered
        jPanelBoton_Abonar1.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        BAgregarClientes.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_BAgregarClientesMouseEntered

    private void ListaClientesCuentaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListaClientesCuentaCBActionPerformed
        try {
            String[] cn = {"Id", "Fecha", "Valor", "Cuenta", "TipoCuenta"};
            TCuentaXC.setModel(new DefaultTableModel(cn, 0));
            TCuentaXC1.setModel(new DefaultTableModel(cn, 0));
            CCuentas cunetaobjeto = new CCuentas();
            String[] idString = ListaClientesCuentaCB.getSelectedItem().toString().split("-");
            idselec = Integer.parseInt(idString[0]);
            listaTx = conp.listaPresios_idpersonas(idselec);
            Double Deuda = 0.0;
            switch (tipo_Cuenta.getSelectedItem().toString()) {
                case "Compra" -> {
                    cunetaobjeto.lista_de_Cuentas_x_persona(Integer.parseInt(idString[0]), TCuentaXC1, 1);
                    cunetaobjeto.lista_de_Cuentas_x_cobrar(Integer.parseInt(idString[0]), TCuentaXC, 0);
                }
                case "Venta" -> {
                    cunetaobjeto.lista_de_Cuentas_x_persona(Integer.parseInt(idString[0]), TCuentaXC1, 2);
                    cunetaobjeto.lista_de_Cuentas_x_cobrar(Integer.parseInt(idString[0]), TCuentaXC, 0);

                }
                default ->
                    throw new AssertionError();
            }
            Deuda = cunetaobjeto.SumCuentasxd(Integer.parseInt(idString[0]));
            DeudaLabel.setText(Deuda.toString());
            if (Deuda > 0) {
                DeudaLabel.setForeground(Color.WHITE);
            } else {
                DeudaLabel.setForeground(new Color(255, 75, 75));
            }
            //255,112,28));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_ListaClientesCuentaCBActionPerformed

    private void ListaClientesCuentaCBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaClientesCuentaCBMouseClicked

    }//GEN-LAST:event_ListaClientesCuentaCBMouseClicked

    private void tipo_CuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_CuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_CuentaActionPerformed

    private void tipo_CuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipo_CuentaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_CuentaMouseClicked

    private void TipoDePersonaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoDePersonaComboBoxActionPerformed
        // TODO add your handling code here:
        optenerListaProbedor(selec());
        ListaClientesCuentaCB.removeAllItems();
        for (persona object : listaProbedor) {
            ListaClientesCuentaCB.addItem(object.getId() + "-" + object.getNombre());
        }
    }//GEN-LAST:event_TipoDePersonaComboBoxActionPerformed

    private void jPanel3GenerarFacturaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3GenerarFacturaMouseExited
        jPanel3GenerarFactura.setBackground(new Color(218, 247, 254)); // Establecer color en formato RGB
        BotonabonarCC2.setBackground(new Color(218, 247, 254)); // Establecer color en formato RGB
    }//GEN-LAST:event_jPanel3GenerarFacturaMouseExited

    private void jPanel3GenerarFacturaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3GenerarFacturaMouseEntered
        jPanel3GenerarFactura.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        BotonabonarCC2.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jPanel3GenerarFacturaMouseEntered

    private void BotonabonarCC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonabonarCC2ActionPerformed
        // TODO add your handling code here:
        ArrayList<Pesada> listaPesadas = new ArrayList<>();
        String cuenta = null;
        int selectedRow = TCuentaXC.getSelectedRow(); // obtiene el número de fila seleccionado
        Integer value = Integer.parseInt(TCuentaXC.getValueAt(selectedRow, 0).toString()); // obtiene el valor de la primera columna de la fila seleccionada
        Double totalx = Double.parseDouble(TCuentaXC.getValueAt(selectedRow, 2).toString());
        cuenta = TCuentaXC.getValueAt(selectedRow, 3).toString();
        String[] pesadas = cuenta.split("\n");
        for (String pesadaString : pesadas) {
            Numero_en_formato_COP = pesadaString;

            if (Numero_en_formato_COP.contains("el valor total")) {
                double total = Double.parseDouble(Numero_en_formato_COP.replaceAll("[^\\d.]", ""));
                // Crear el formato con el separador de miles
                Numero_en_formato_COP = "el valor total= " + new DecimalFormat("#,###.##").format(total);
                JOptionPane.showConfirmDialog(null, Numero_en_formato_COP);
            } else {
                JOptionPane.showConfirmDialog(null, pesadaString);
            }

            if (pesadaString.isEmpty()) {
                continue; // Ignorar líneas vacías o la línea "el valor total"
            }
            if (pesadaString.startsWith("el valor total")) {
                try {
                    String[] cadena = pesadaString.split("_");

                    String[] cadena1 = cadena[0].split("=");
                    Double totalInicial = Double.parseDouble(cadena1[1]);
                    facturaPdf.setTotalAnteriord(totalInicial);

                    for (String parte : cadena) {
                        if (parte.startsWith("el valor total")) {
                            continue;
                        }
                        String[] partes = parte.split(", ");
                        int id = Integer.parseInt(partes[0].split("=")[1]);
                        String material = partes[1].split(" ")[1];
                        double valor = Double.parseDouble(partes[1].split(" ")[3]);
                        double pesada = Double.parseDouble(partes[1].split(" ")[5]);
                        Pesada pesadaObj = new Pesada(id, material, pesada, valor);
                        listaPesadas.add(pesadaObj);
                    }
                } catch (Exception e) {
                    continue;
                }
                continue;
            }
            String[] partes = pesadaString.split(", ");
            int id = Integer.parseInt(partes[0].split("=")[1]);
            String material = partes[1].split(" ")[1];
            double pesada = Double.parseDouble(partes[1].split(" ")[3]);
            double valor = Double.parseDouble(partes[1].split(" ")[5]);
            Pesada pesadaObj = new Pesada(id, material, pesada, valor);
            listaPesadas.add(pesadaObj);
        }

        try {
            pdf(true, value, totalx, listaPesadas);
        } catch (SQLException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ImprimirImpresoraTextoPlano("Cuenta " + value, totalx, listaPesadas);
        } catch (IOException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonabonarCC2ActionPerformed

    private void BotonabonarCC2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonabonarCC2MouseExited
        jPanel3GenerarFactura.setBackground(new Color(218, 247, 254)); // Establecer color en formato RGB
        BotonabonarCC2.setBackground(new Color(218, 247, 254)); // Establecer color en formato RGB
    }//GEN-LAST:event_BotonabonarCC2MouseExited

    private void BotonabonarCC2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonabonarCC2MouseEntered
        jPanel3GenerarFactura.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        BotonabonarCC2.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_BotonabonarCC2MouseEntered

    private void jPanelNuevaCuentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelNuevaCuentaMouseExited
        jPanelNuevaCuenta.setBackground(new Color(218, 247, 254)); // Establecer color en formato RGB
        jButton8.setBackground(new Color(218, 247, 254)); // Establecer color en formato RGB
    }//GEN-LAST:event_jPanelNuevaCuentaMouseExited

    private void jPanelNuevaCuentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelNuevaCuentaMouseEntered
        jPanelNuevaCuenta.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jButton8.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jPanelNuevaCuentaMouseEntered

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        Cuentas vv = new Cuentas(TipoDePersonaComboBox.getSelectedItem().toString());
        vv.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseExited
        jPanelNuevaCuenta.setBackground(new Color(218, 247, 254)); // Establecer color en formato RGB
        jButton8.setBackground(new Color(218, 247, 254)); // Establecer color en formato RGB
    }//GEN-LAST:event_jButton8MouseExited

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        jPanelNuevaCuenta.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jButton8.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jButton8MouseEntered

    private void jPanelBoton_Cancelar_cuentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoton_Cancelar_cuentaMouseExited
        jPanelBoton_Cancelar_cuenta.setBackground(new Color(218, 247, 254)); // Establecer color en formato RGB
        CancelarCuenta.setBackground(new Color(218, 247, 254)); // Establecer color en formato RGB
    }//GEN-LAST:event_jPanelBoton_Cancelar_cuentaMouseExited

    private void jPanelBoton_Cancelar_cuentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoton_Cancelar_cuentaMouseEntered
        jPanelBoton_Cancelar_cuenta.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        CancelarCuenta.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jPanelBoton_Cancelar_cuentaMouseEntered

    private void CancelarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarCuentaActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel modelo = (DefaultTableModel) TCuentaXC.getModel();
            int i = TCuentaXC.getSelectedRow();
            CCuentas nue = new CCuentas();
            Boolean j = nue.modificarCuentaEstado(Integer.parseInt((String) modelo.getValueAt(i, 0)));
            if (j) {
                JOptionPane.showConfirmDialog(null, "Se ha actualizado");
                String[] cn = {"Id", "Fecha", "Valor", "Cuenta", "TipoCuenta"};
                TCuentaXC.setModel(new DefaultTableModel(cn, 0));
                String[] idString = ListaClientesCuentaCB.getSelectedItem().toString().split("-");
                CCuentas cunetaobjeto = new CCuentas();
                cunetaobjeto.lista_de_Cuentas_x_cobrar(Integer.parseInt(idString[0]), TCuentaXC, 0);
            } else {
                JOptionPane.showConfirmDialog(null, "No se ha actualizado");
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Necesita seleccionar una cuenta");
        }
    }//GEN-LAST:event_CancelarCuentaActionPerformed

    private void CancelarCuentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelarCuentaMouseExited
        jPanelBoton_Cancelar_cuenta.setBackground(new Color(218, 247, 254)); // Establecer color en formato RGB
        CancelarCuenta.setBackground(new Color(218, 247, 254)); // Establecer color en formato RGB
    }//GEN-LAST:event_CancelarCuentaMouseExited

    private void CancelarCuentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelarCuentaMouseEntered
        jPanelBoton_Cancelar_cuenta.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        CancelarCuenta.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_CancelarCuentaMouseEntered

    private void jPanelBoton_AbonarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoton_AbonarMouseExited
        jPanelBoton_Abonar.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        BotonabonarCC.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_jPanelBoton_AbonarMouseExited

    private void jPanelBoton_AbonarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoton_AbonarMouseEntered
        jPanelBoton_Abonar.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        BotonabonarCC.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jPanelBoton_AbonarMouseEntered

    private void BotonabonarCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonabonarCCActionPerformed
        // TODO add your handling code here
        try {
            DefaultTableModel modelo = (DefaultTableModel) TCuentaXC.getModel();
            int i = TCuentaXC.getSelectedRow();
            CCuentas nue = new CCuentas();
            Integer valIN = Integer.parseInt(JOptionPane.showInputDialog("Digite el valor que desea abonar"));
            JOptionPane.showConfirmDialog(null, modelo.getValueAt(i, 2));
            Double valTB = Double.parseDouble((String) modelo.getValueAt(i, 2));
            Double total = valTB - valIN;
            if (total > 0) {
                String g = (String) modelo.getValueAt(i, 0);
                String cadena = "_id=-1,  Abono  " + valIN + " X 1 = " + total;
                Boolean j = nue.modificarCuentaValor(Integer.parseInt(g), total, cadena);
                if (j) {
                    JOptionPane.showConfirmDialog(null, "Se ha actualizado");
                } else {
                    JOptionPane.showConfirmDialog(null, "No se ha actualizado");
                }
                String[] cn = {"Id", "Fecha", "Valor", "Cuenta", "TipoCuenta"};
                TCuentaXC.setModel(new DefaultTableModel(cn, 0));
                String[] idString = ListaClientesCuentaCB.getSelectedItem().toString().split("-");
                CCuentas cunetaobjeto = new CCuentas();
                cunetaobjeto.lista_de_Cuentas_x_cobrar(Integer.parseInt(idString[0]), TCuentaXC, 0);
            }
            if (total == 0) {
                String g = (String) modelo.getValueAt(i, 0);
                nue.modificarCuentaEstado(Integer.parseInt(g));
                String[] cn = {"Id", "Fecha", "Valor", "Cuenta", "TipoCuenta"};
                TCuentaXC.setModel(new DefaultTableModel(cn, 0));
                String[] idString = ListaClientesCuentaCB.getSelectedItem().toString().split("-");
                CCuentas cunetaobjeto = new CCuentas();
                cunetaobjeto.lista_de_Cuentas_x_cobrar(Integer.parseInt(idString[0]), TCuentaXC, 0);
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Necesita seleccionar una cuenta");
        }
    }//GEN-LAST:event_BotonabonarCCActionPerformed

    private void BotonabonarCCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonabonarCCMouseExited
        jPanelBoton_Abonar.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        BotonabonarCC.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_BotonabonarCCMouseExited

    private void BotonabonarCCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonabonarCCMouseEntered
        jPanelBoton_Abonar.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        BotonabonarCC.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_BotonabonarCCMouseEntered

    private void BotonabonarCC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonabonarCC1ActionPerformed
        // TODO add your handling code here:
        ArrayList<Pesada> listaPesadas = new ArrayList<>();
        String cuenta = null;

        int selectedRow = TCuentaXC1.getSelectedRow(); // obtiene el número de fila seleccionado
        Integer value = Integer.parseInt(TCuentaXC1.getValueAt(selectedRow, 0).toString()); // obtiene el valor de la primera columna de la fila seleccionada
        Double totalx = Double.parseDouble(TCuentaXC1.getValueAt(selectedRow, 2).toString());
        cuenta = TCuentaXC1.getValueAt(selectedRow, 3).toString();
        String[] pesadas = cuenta.split("\n");

        for (String pesadaString : pesadas) {
            if (pesadaString.isEmpty() || pesadaString.startsWith("el valor total")) {
                continue; // Ignorar líneas vacías o la línea "el valor total"
            }
            String[] partes = pesadaString.split(", ");
            int id = Integer.parseInt(partes[0].split("=")[1]);
            String material = partes[1].split(" ")[1];
            double pesada = Double.parseDouble(partes[1].split(" ")[3]);
            double valor = Double.parseDouble(partes[1].split(" ")[5]);
            Pesada pesadaObj = new Pesada(id, material, pesada, valor);
            listaPesadas.add(pesadaObj);
        }

        try {
            pdf(true, value, totalx, listaPesadas);
        } catch (SQLException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ImprimirImpresoraTextoPlano("Cuenta " + value, totalx, listaPesadas);
        } catch (IOException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonabonarCC1ActionPerformed

    private void BotonabonarCC1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonabonarCC1MouseExited
        BotonabonarCC1.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
    }//GEN-LAST:event_BotonabonarCC1MouseExited

    private void BotonabonarCC1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonabonarCC1MouseEntered
        BotonabonarCC1.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_BotonabonarCC1MouseEntered

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        jButton7.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jPanelBoton_AplicarFiltro.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        jButton7.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jPanelBoton_AplicarFiltro.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_jButton7MouseExited

    private void jPanelBoton_AplicarFiltroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoton_AplicarFiltroMouseEntered
        jButton7.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jPanelBoton_AplicarFiltro.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jPanelBoton_AplicarFiltroMouseEntered

    private void jPanelBoton_AplicarFiltroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBoton_AplicarFiltroMouseExited
        jButton7.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jPanelBoton_AplicarFiltro.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_jPanelBoton_AplicarFiltroMouseExited

    private void jButton12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseEntered
        jButton12.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jPanel4.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jButton12MouseEntered

    private void jButton12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseExited
        jButton12.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jPanel4.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_jButton12MouseExited

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        jButton12.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jPanel4.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jPanel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseExited
        jButton12.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jPanel4.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_jPanel4MouseExited

    private void Valor_dineroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Valor_dineroKeyTyped
    char C = evt.getKeyChar();

        if (Character.isLetter(C)) {
            getToolkit().beep();  //esto lo que ahce es hacer un sonido de beep
            evt.consume();
            //  AudioClip  sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Sonidos/Advertencia.wav"));
            //    sonido.play();
            //   Icon icon = new ImageIcon(getClass().getResource("/Imagenes/123.png")); //para poner icono al aviso
            //   JOptionPane.showMessageDialog(null, "<html><b><span style=\"font-size:1.3em\">Solo caracteres numericos</b>", "Error✘", JOptionPane.INFORMATION_MESSAGE, icon);

        } else if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64
                || (int) evt.getKeyChar() >= 91 && (int) evt.getKeyChar() <= 96
                || (int) evt.getKeyChar() >= 123 && (int) evt.getKeyChar() <= 255) {
            getToolkit().beep();// //esto lo que ahce es hacer un sonido de beep
            evt.consume();

        } else if (evt.getKeyChar() == KeyEvent.VK_SPACE) {//para no permitir enter
            getToolkit().beep();// //esto lo que ahce es hacer un sonido de beep
            evt.consume();
        }
    }//GEN-LAST:event_Valor_dineroKeyTyped

    private void Valor_dineroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Valor_dineroKeyReleased
        try {
            // Obtener el texto ingresado en el campo de texto
                String input = Valor_dinero.getText();

                // Eliminar caracteres no numéricos
                String numericInput = input.replaceAll("[^0-9]", "");

                // Convertir el texto numérico a un valor entero
                int number = Integer.parseInt(numericInput);

                // Formatear el número en formato COP con separación de miles
                NumberFormat numberFormat = NumberFormat.getInstance();
                String formattedInput = numberFormat.format(number);

                // Establecer el texto formateado en el campo de texto
                Valor_dinero.setText(formattedInput);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_Valor_dineroKeyReleased

    private void jButton9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseEntered
      jButton9.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jPanel7.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jButton9MouseEntered

    private void jButton9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseExited
jButton9.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jPanel7.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_jButton9MouseExited

    private void jPanel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseEntered
      jButton9.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jPanel7.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jPanel7MouseEntered

    private void jPanel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseExited
       jButton9.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jPanel7.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_jPanel7MouseExited

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
     jButton6.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jPanel8.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
jButton6.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jPanel8.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_jButton6MouseExited

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
       jButton6.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jPanel8.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGBd
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
           jButton6.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jPanel8.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_jPanel8MouseExited

    private void EliminarTipoMovimientoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarTipoMovimientoMouseEntered
   EliminarTipoMovimiento.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jPanel9.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_EliminarTipoMovimientoMouseEntered

    private void EliminarTipoMovimientoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarTipoMovimientoMouseExited
       EliminarTipoMovimiento.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jPanel9.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_EliminarTipoMovimientoMouseExited

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered
        EliminarTipoMovimiento.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jPanel9.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jPanel9MouseEntered

    private void jPanel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseExited
     jPanel9.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        EliminarTipoMovimiento.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_jPanel9MouseExited

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
        jButton10.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jPanel10.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jButton10MouseEntered

    private void jButton10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseExited
          jButton10.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jPanel10.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_jButton10MouseExited

    private void jPanel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseEntered
     jButton10.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
        jPanel10.setBackground(new Color(182, 238, 252)); // Establecer color en formato RGB
    }//GEN-LAST:event_jPanel10MouseEntered

    private void jPanel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseExited
              jButton10.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminado
        jPanel10.setBackground(new Color(218, 247, 254)); // Restablecer color de fondo predeterminad
    }//GEN-LAST:event_jPanel10MouseExited

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
       try {
            // Ruta del archivo PDF
            File file = new File("Recursos tesis/Manual de usuario/Manual de usuario.pdf");

            // Verifica si el Desktop es compatible con la apertura de archivos
            if (Desktop.isDesktopSupported()) {
                // Obtiene una instancia del Desktop
                Desktop desktop = Desktop.getDesktop();

                // Verifica si el archivo es válido
                if (file.exists()) {
                    // Abre el archivo PDF con la aplicación predeterminada
                    desktop.open(file);
                } else {
                    System.out.println("El archivo no existe.");
                }
            } else {
                System.out.println("El Desktop no es compatible.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenu2MouseClicked

    public void pdf(Boolean es_impresion, Integer idfac, Double total, ArrayList<Pesada> pesadasx) throws SQLException, IOException, DocumentException {

        String fac = (String) ListaClientesCuentaCB.getSelectedItem();
        String[] fac2 = fac.split("-");

        Integer numeroidpersona = Integer.parseInt(fac2[0]);
        conp = new CPersona();
        persona pepe = conp.Leerpersonas(numeroidpersona);
        String nombreArchivo = "Factura de " + pepe.getNombre() + ".pdf";
        String cliente = pepe.getNombre();
        String direccion = pepe.getDescripcion();
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String fecha = fechaActual.format(formatoFecha);
        double importe = Double.parseDouble(total.toString());
        Double kilos = 4.0;

        facturaPdf.generarFacturaPdf(es_impresion, nombreArchivo, tipo_Cuenta.getSelectedItem().toString(), idfac, cliente, direccion, fecha, pesadasx, importe, kilos);
        pesadasx = null;
    }
    Double ValorTotal = 0.0;
    ArrayList<Cuenta> listaCuenta;
    ArrayList<Cuenta> listaCuenta2;
    ArrayList<persona> listaProbedor;

    public void optenerListaProbedor(int tipopersona) {
        listaProbedor = null;
        listaProbedor = conp.listaPersonas(tipopersona);
        String[] names = {"Nombre", "Celular", "Identificación", "Tipo de Documento", "Descripción"};
        DefaultTableModel model = new DefaultTableModel(names, 0);
        for (persona provedor : listaProbedor) {
            String[] pro = new String[5];
            pro[0] = provedor.getNombre();
            pro[1] = provedor.getCelular();
            pro[2] = provedor.getIdentificacion();
            pro[3] = provedor.getTipoDocumento_nombre();
            pro[4] = provedor.getDescripcion();
            model.addRow(pro);
            TClientes.setModel(model);
            ListaClientesCuentaCB.addItem(provedor.getId() + "-" + provedor.getNombre());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAgregarClientes;
    private javax.swing.JButton BotonabonarCC;
    private javax.swing.JButton BotonabonarCC1;
    private javax.swing.JButton BotonabonarCC2;
    private com.toedter.calendar.JDateChooser CalendarioSuperior;
    private com.toedter.calendar.JDateChooser Calendarioinferior;
    private javax.swing.JButton CancelarCuenta;
    private javax.swing.JTextField Descripcion;
    private javax.swing.JLabel DeudaLabel;
    private javax.swing.JButton EditarCliente;
    private javax.swing.JButton EliminarTipoMovimiento;
    private javax.swing.JTextField JTValor;
    private javax.swing.JTextField JTpeso;
    private javax.swing.JLabel JlabelPersonasregistradas;
    private javax.swing.JComboBox<String> ListMaterialesInve;
    private javax.swing.JComboBox<String> ListaClientesCuentaCB;
    private javax.swing.JTable TClientes;
    private javax.swing.JTable TCuentaXC;
    private javax.swing.JTable TCuentaXC1;
    private javax.swing.JTable TInventario;
    private javax.swing.JTable TPrestamos;
    private javax.swing.JTable TablaCajaMenor;
    private javax.swing.JComboBox<String> Tipo;
    private javax.swing.JComboBox<String> TipoDePersonaComboBox;
    private javax.swing.JComboBox<String> TipoInforme;
    private javax.swing.JComboBox<String> TipoMovimiento;
    private javax.swing.JTextField Valor_dinero;
    private javax.swing.JButton eliminarPersona;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonActualizar_Prestamo;
    private javax.swing.JButton jButtonCancelar_Prestamo;
    private javax.swing.JButton jButtonRegistrar_Prestamo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCategoria;
    private javax.swing.JLabel jLabelCategoria_icono;
    private javax.swing.JLabel jLabelDegradadoMenu;
    private javax.swing.JLabel jLabelDegradadoMenu_Ingresis_y_egresos;
    private javax.swing.JLabel jLabelDegradadoMenu_Inventario;
    private javax.swing.JLabel jLabelDegradadoMenu_Personas;
    private javax.swing.JLabel jLabelDegradadoMenu_Prestamos;
    private javax.swing.JLabel jLabelIdentificacion;
    private javax.swing.JLabel jLabelInventario;
    private javax.swing.JLabel jLabelPersonas;
    private javax.swing.JLabel jLabelPersonasregistradas_icono;
    private javax.swing.JLabel jLabelRecuperacion_de_hierro;
    private javax.swing.JLabel jLabelRecuperacion_de_hierro2;
    private javax.swing.JLabel jLabelRecuperacion_de_hierro3;
    private javax.swing.JLabel jLabelRecuperacion_de_hierro4;
    private javax.swing.JLabel jLabelRecuperacion_de_hierro5;
    private javax.swing.JLabel jLabelTexto_Cuenta;
    private javax.swing.JLabel jLabelTipo_de_cuenta;
    private javax.swing.JLabel jLabeltipo_de_cuenta;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel3GenerarFactura;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelActualizar_prestamo;
    private javax.swing.JPanel jPanelBoton_Abonar;
    private javax.swing.JPanel jPanelBoton_Abonar1;
    private javax.swing.JPanel jPanelBoton_AplicarFiltro;
    private javax.swing.JPanel jPanelBoton_Cancelar_cuenta;
    private javax.swing.JPanel jPanelCancelar_Prestamo;
    private javax.swing.JPanel jPanelCuenta;
    private javax.swing.JPanel jPanelEditarPersona;
    private javax.swing.JPanel jPanelEliminarProveedor;
    private javax.swing.JPanel jPanelIngresos_y_egresos;
    private javax.swing.JPanel jPanelInventario;
    private javax.swing.JPanel jPanelMen2;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelMenuInventario;
    private javax.swing.JPanel jPanelMenuPersonas;
    private javax.swing.JPanel jPanelMenuPrestamos;
    private javax.swing.JPanel jPanelNuevaCuenta;
    private javax.swing.JPanel jPanelPersonas;
    private javax.swing.JPanel jPanelPrestamos;
    private javax.swing.JPanel jPanelRegistrar_Prestamo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> tipo_Cuenta;
    // End of variables declaration//GEN-END:variables
}
