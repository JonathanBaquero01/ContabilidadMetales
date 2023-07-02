/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.vistas;

import Clases_FrontEnd.CustomComboBoxRenderer;
import Clases_FrontEnd.PlaceHolder;
import Clases_FrontEnd.RoundBorder;
import com.contabilidadmetales.contabilidadmetales.FacturaPdf;
import com.contabilidadmetales.contabilidadmetales.controlador.CCuentas;
import com.contabilidadmetales.contabilidadmetales.controlador.CInventario;
import com.contabilidadmetales.contabilidadmetales.controlador.CPersona;
import com.contabilidadmetales.contabilidadmetales.controlador.CPrestamos;
import com.contabilidadmetales.contabilidadmetales.modelo.Cuenta;
import com.contabilidadmetales.contabilidadmetales.modelo.Pesada;
import com.contabilidadmetales.contabilidadmetales.modelo.persona;
import com.itextpdf.text.DocumentException;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Cuentas extends javax.swing.JFrame {

    ArrayList<persona> listaProbedor;
    ArrayList<Pesada> pesadas;
    ArrayList<persona> ListaPersonas;
    CPersona objetopersona;
    int tipo = 0;
String Numero_en_formato_COP="";
    public void getTipo(String TipoPersona) {
        ListaPersonas = null;
        objetopersona = null;
        switch (TipoPersona) {
            case "Proveedor":
                tipo = 1;
                break;
            case "Cliente":
                tipo = 2;
                break;
            default:
        }
        objetopersona = new CPersona();
        ListaPersonas = objetopersona.listaPersonas(tipo);
        Vector<String> items = new Vector<>();
        for (persona ListaPersona : ListaPersonas) {
            items.add(ListaPersona.getId() + "_" + ListaPersona.getNombre());
        }
        ComboBoxModel<String> aModel = new DefaultComboBoxModel<>(items);
        ListaXclienteCB.setModel(aModel);
    }

    public void ImprimirImpresoraTextoPlano(String fileName,Double total) throws IOException {

        String filePath = fileName + ".txt";
        String titulo = "Contabilidad Metales\n\n" + "Número de cuenta: " + fileName + "\n\n";
        String pesadastx = "";
        for (Pesada pesada : pesadas) {
            pesadastx = pesadastx + pesada.toString2() + "\n";
        }

        // Crear y escribir en el archivo de texto
        try {
               DecimalFormat formato = new DecimalFormat("#,###.##");
         Numero_en_formato_COP = formato.format(total);
            
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(titulo + pesadastx+"Total Cuenta: "+Numero_en_formato_COP);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leer el archivo de texto
        StringBuilder content = new StringBuilder();
        try ( BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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

    public Cuentas(String TipoPersona) {
        pesadas = new ArrayList<>();
        initComponents();
        objetopersona = new CPersona();
        getTipo(TipoPersona);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
          this.setResizable(false);
         int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;//consigo el ancho y alto de la pantalla

        this.setBounds((ancho / 2) - (this.getWidth() / 2), (alto / 2) - (this.getHeight() / 2), 860, 625); //determino el tamaño de la pantalla del sw , ancho , alto

        this.setLocationRelativeTo(this);//para q se ponga en el centro

        this.setIconImage(new ImageIcon("Imagenes/FormLogin/favicon.png").getImage());
        this.setTitle("Nueva cuenta");
        
        agregarImagenes();
        Ajustamiento_Objetos();
    }
    
    public void Ajustamiento_Objetos() {
     TipoDePersona.setRenderer(new CustomComboBoxRenderer());
      ListMaterialesCB.setRenderer(new CustomComboBoxRenderer());
       tipo_compra.setRenderer(new CustomComboBoxRenderer());
        CheckCancelado.setRenderer(new CustomComboBoxRenderer());
          ListaXclienteCB.setRenderer(new CustomComboBoxRenderer());
          
           PlaceHolder  placeholder = new PlaceHolder("Peso (KG)", TextPesada, PlaceHolder.Show.ALWAYS, TextPesada.CENTER);
               placeholder = new PlaceHolder("Cuenta", Cuenta, PlaceHolder.Show.ALWAYS, TextPesada.CENTER);
        // Establece el color del borde y el radio de los bordes redondos de los Jtext,todo esto se envia a la clase RoundBorder
        TextPesada.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
      jScrollPane1.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
      
         TextPesada.setHorizontalAlignment(TextPesada.CENTER);
    }
    
    public void agregarImagenes(){
     ImageIcon icon = new ImageIcon("Imagenes/MetalesDeSantander/Acerca de/favicon.png");
     //esto es pa reducir la imagen
 Image imagenRedimensionada = icon.getImage().getScaledInstance(
        (int) (icon.getIconWidth() * 50 / 100),//el primer numero es el porcentage pa reducir la imagen, si es mas grande q 100 aumenta
        (int) (icon.getIconHeight() * 50 / 100),//64
        Image.SCALE_SMOOTH
    );
      jLabel1.setIcon(new ImageIcon(imagenRedimensionada));
    
        icon = new ImageIcon("Imagenes/Cuentas/Degradado.png");
        jLabel5.setIcon(icon);
        
       
        
         icon = new ImageIcon("Imagenes/Cuentas/Agregar a la cuenta.png");
          imagenRedimensionada = icon.getImage().getScaledInstance(
        (int) (icon.getIconWidth() * 96 / 100),//el primer numero es el porcentage pa reducir la imagen, si es mas grande q 100 aumenta
        (int) (icon.getIconHeight() * 96 / 100),//64
        Image.SCALE_SMOOTH
    );
             jMenu1.setIcon(new ImageIcon(imagenRedimensionada));
        
         icon = new ImageIcon("Imagenes/Cuentas/Editar lista.png");
           imagenRedimensionada = icon.getImage().getScaledInstance(
        (int) (icon.getIconWidth() * 96 / 100),//el primer numero es el porcentage pa reducir la imagen, si es mas grande q 100 aumenta
        (int) (icon.getIconHeight() * 96 / 100),//64
        Image.SCALE_SMOOTH
    );
        jMenu2.setIcon(new ImageIcon(imagenRedimensionada));
        
           icon = new ImageIcon("Imagenes/Cuentas/Terminar cuenta.png");
             imagenRedimensionada = icon.getImage().getScaledInstance(
        (int) (icon.getIconWidth() * 96 / 100),//el primer numero es el porcentage pa reducir la imagen, si es mas grande q 100 aumenta
        (int) (icon.getIconHeight() * 96 / 100),//64
        Image.SCALE_SMOOTH
    );
        jMenu3.setIcon(new ImageIcon(imagenRedimensionada));
        
      
    }
    
   
   
        
      
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        ValorTotalLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TipoDePersona = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        ListaXclienteCB = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        ListMaterialesCB = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        tipo_compra = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        CheckCancelado = new javax.swing.JComboBox<>();
        TextPesada = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Cuenta = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(860, 623));
        jPanel1.setMinimumSize(new java.awt.Dimension(860, 623));
        jPanel1.setPreferredSize(new java.awt.Dimension(860, 625));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 38)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nueva cuenta");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 28, -1, -1));

        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 0, 598, 110));

        jPanel2.setBackground(new java.awt.Color(234, 250, 254));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(203, 247, 254));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("TOTAL:");
        jLabel4.setToolTipText("");

        ValorTotalLabel.setBackground(new java.awt.Color(255, 75, 75));
        ValorTotalLabel.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ValorTotalLabel.setForeground(new java.awt.Color(255, 75, 75));
        ValorTotalLabel.setText("0.0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ValorTotalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ValorTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, -1, 40));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 6, 180, 110));

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel3.setText("Acción");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, 30));

        TipoDePersona.setBackground(new java.awt.Color(218, 247, 254));
        TipoDePersona.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        TipoDePersona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Proveedor", "Cliente" }));
        TipoDePersona.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TipoDePersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoDePersonaActionPerformed(evt);
            }
        });
        jPanel2.add(TipoDePersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 220, -1));

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel7.setText("Tipo de usuario");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, 30));

        ListaXclienteCB.setBackground(new java.awt.Color(218, 247, 254));
        ListaXclienteCB.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        ListaXclienteCB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListaXclienteCB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaXclienteCBMouseClicked(evt);
            }
        });
        ListaXclienteCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListaXclienteCBActionPerformed(evt);
            }
        });
        jPanel2.add(ListaXclienteCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 220, -1));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel8.setText("Usuario");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 30));

        ListMaterialesCB.setBackground(new java.awt.Color(218, 247, 254));
        ListMaterialesCB.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        ListMaterialesCB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ListMaterialesCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListMaterialesCBActionPerformed(evt);
            }
        });
        jPanel2.add(ListMaterialesCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 220, 30));

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel9.setText("Material");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, 30));

        tipo_compra.setBackground(new java.awt.Color(218, 247, 254));
        tipo_compra.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tipo_compra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compra", "Venta" }));
        tipo_compra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tipo_compra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipo_compraMouseClicked(evt);
            }
        });
        jPanel2.add(tipo_compra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 220, -1));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel10.setText("Movimiento");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, 30));

        CheckCancelado.setBackground(new java.awt.Color(218, 247, 254));
        CheckCancelado.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        CheckCancelado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cancelado", "Por Cancelar" }));
        CheckCancelado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckCancelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckCanceladoActionPerformed(evt);
            }
        });
        jPanel2.add(CheckCancelado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 220, 30));

        TextPesada.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        TextPesada.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        TextPesada.setSelectionColor(new java.awt.Color(200, 244, 254));
        TextPesada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextPesadaActionPerformed(evt);
            }
        });
        TextPesada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextPesadaKeyPressed(evt);
            }
        });
        jPanel2.add(TextPesada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 220, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 590));

        Cuenta.setColumns(20);
        Cuenta.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        Cuenta.setLineWrap(true);
        Cuenta.setRows(5);
        Cuenta.setWrapStyleWord(true);
        Cuenta.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        Cuenta.setSelectionColor(new java.awt.Color(200, 244, 254));
        jScrollPane1.setViewportView(Cuenta);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 110, 599, 436));

        jMenuBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenu1.setText("Agregar a la cuenta");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar lista");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(255, 75, 75));
        jMenu3.setForeground(new java.awt.Color(255, 0, 0));
        jMenu3.setText("Terminar cuenta");
        jMenu3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu3.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 public void optenerListaProbedor(int tipopersona) {
        listaProbedor = null;
        listaProbedor = objetopersona.listaPersonas(tipopersona);
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
            // TClientes.setModel(model);
            // ListaClientesCuentaCB.addItem(provedor.getId() + "-" + provedor.getNombre());
        }
    }
    private void TipoDePersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoDePersonaActionPerformed
        String TipoPersona = TipoDePersona.getSelectedItem().toString();
        getTipo(TipoPersona);
    }//GEN-LAST:event_TipoDePersonaActionPerformed

    private void ListaXclienteCBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaXclienteCBMouseClicked

    }//GEN-LAST:event_ListaXclienteCBMouseClicked

    private void ListaXclienteCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListaXclienteCBActionPerformed
        String Textid = ListaXclienteCB.getSelectedItem().toString().split("_")[0];
        Integer Isid = Integer.parseInt(Textid);
        String lista = objetopersona.listaPresios_idpersonas(Isid);
        String[] as = lista.split(" ");
        ListMaterialesCB.removeAllItems();
        for (String a : as) {
            ListMaterialesCB.addItem(a);
        }
        if (Cuenta.getText() != ("")) {
            Cuenta.setText("");
            for (Pesada pesada : pesadas) {
                Double valora = pesada.getValor();
                String nombrea = pesada.getMaterial();
                for (String a : as) {
                    String[] material = a.split(",");
                    String nombreb = material[0];
                    Double valorb = Double.parseDouble(material[1]);
                    if (valora != valorb && nombrea.equals(nombreb)) {
                        pesada.setValor(valorb);
                    }
                }

                Cuenta.append(pesada.toString() + "\n");
            }
    }//GEN-LAST:event_ListaXclienteCBActionPerformed
    }

    private void ListMaterialesCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListMaterialesCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListMaterialesCBActionPerformed

    private void TextPesadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPesadaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_TextPesadaActionPerformed

    private void TextPesadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextPesadaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Double peso = Double.parseDouble(TextPesada.getText());
            String[] vv = ListMaterialesCB.getSelectedItem().toString().split(",");
            String textValor = vv[1];
            Double valor = Double.parseDouble(textValor);
            String material = vv[0];
            Pesada nuevaPesada = new Pesada(contado, material, peso, valor);
            Cuenta.append(nuevaPesada.toString() + "\n");
            pesadas.add(nuevaPesada);
            contado = contado + 1;
            TextPesada.setText("");
            total = total + nuevaPesada.getTotal().intValue();
            ValorTotalLabel.setText(total.toString());
        }
    }//GEN-LAST:event_TextPesadaKeyPressed

    private void CheckCanceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckCanceladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckCanceladoActionPerformed
   
    public void pdf(Integer idfac) throws SQLException, IOException, DocumentException {
        String fac = (String) ListaXclienteCB.getSelectedItem();
        String[] fac2 = fac.split("_");
        Integer numeroidpersona = Integer.parseInt(fac2[0]);
        objetopersona = new CPersona();
        persona pepe = objetopersona.Leerpersonas(numeroidpersona);

        String cliente = pepe.getNombre();
        String direccion = pepe.getDescripcion();
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String fecha = fechaActual.format(formatoFecha);
        String nombreArchivo = "factura de " + pepe.getNombre() + idfac + ".pdf";
        double importe = Double.parseDouble(total.toString());
        Double kilos = 4.0;
        FacturaPdf facturaPdf = new FacturaPdf();
        facturaPdf.generarFacturaPdf(false, nombreArchivo, tipo_compra.getSelectedItem().toString(), idfac, cliente, direccion, fecha, pesadas, importe, kilos);

    }


    private void tipo_compraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipo_compraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_compraMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
      
        Double peso = Double.parseDouble(TextPesada.getText());
        String[] vv = ListMaterialesCB.getSelectedItem().toString().split(",");
        String textValor = vv[1];
        Double valor = Double.parseDouble(textValor);
        String material = vv[0];
        Pesada nuevaPesada = new Pesada(contado, material, peso, valor);
        Cuenta.append(nuevaPesada.toString() + "\n");
        pesadas.add(nuevaPesada);
        contado = contado + 1;
        TextPesada.setText("");
        total = total + nuevaPesada.getTotal();
        ValorTotalLabel.setText(total.toString());
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
       String TextId = ListaXclienteCB.getSelectedItem().toString().split("_")[0];

        Integer identificacion = Integer.parseInt(TextId);
        String lista = ListaPersonas.get(ListaXclienteCB.getSelectedIndex()).getArchivoLista();
        ActualizarListaPrecios a = new ActualizarListaPrecios(lista, identificacion);
        a.setVisible(true);
    }//GEN-LAST:event_jMenu2MouseClicked
 int contado = 0;
    Double total = 0.0;
    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
          CCuentas cuenta = new CCuentas();
        String s = ListaXclienteCB.getSelectedItem().toString();
        String[] sa = s.split("_");
        // System.out.println(sa[0]);
        Integer id = Integer.parseInt(sa[0]);
        CPrestamos cP = new CPrestamos();
        Double de = cP.SumPrestamo(id);
        int abono = 0;
DecimalFormat formato = new DecimalFormat("#,###.##"); 
        if (de != 0) {
            abono = Integer.parseInt(JOptionPane.showInputDialog(null, "Esta persona tiene una deuda de: " + formato.format(de) + " ¿desea cruzar con la cuenta?"));
            Cuenta.append("Abona " + abono + " a la deuda de " + de);
            total = total - abono;
            ValorTotalLabel.setText(total.toString());
            cP.AbonoPrestamos(id, abono, de);
            //cP.listaPrestamos(TPrestamos);
        }

        //TextAreaCuentas.append(Cm.toString());
        Cuenta.append("\n" + "El valor total= " + total.toString());
        Cuenta nueva = null;

        if (tipo_compra.getSelectedItem().toString().equals("Compra")) {
            switch (CheckCancelado.getSelectedItem().toString()) {
                case "Cancelado":
                    nueva = new Cuenta(1, id, 1, Cuenta.getText(), total);
                    break;
                case "Por Cancelar":
                    nueva = new Cuenta(1, id, 0, Cuenta.getText(), total);
                    break;
                default:
                    throw new AssertionError();
            }
        } else {
            switch (CheckCancelado.getSelectedItem().toString()) {
                case "Cancelado":
                    nueva = new Cuenta(2, id, 1, Cuenta.getText(), total);
                    break;
                case "Por Cancelar":
                    nueva = new Cuenta(2, id, 0, Cuenta.getText(), total);
                    break;
                default:
                    throw new AssertionError();
            }

        }

             formato = new DecimalFormat("#,###.##");  
        JOptionPane.showConfirmDialog(null, "El valor que se debe pagar es: " + formato.format(total));
        Double num = 0.0;
        Integer idcuenta = cuenta.registrarCuenta(nueva, num);

        try {
            pdf(idcuenta);
        } catch (SQLException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        String nombreCuenta = "Cuenta" + idcuenta;
        try {

            ImprimirImpresoraTextoPlano(nombreCuenta,total);
        } catch (IOException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dispose();
    }//GEN-LAST:event_jMenu3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CheckCancelado;
    private javax.swing.JTextArea Cuenta;
    private javax.swing.JComboBox<String> ListMaterialesCB;
    private javax.swing.JComboBox<String> ListaXclienteCB;
    private javax.swing.JTextField TextPesada;
    private javax.swing.JComboBox<String> TipoDePersona;
    private javax.swing.JLabel ValorTotalLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> tipo_compra;
    // End of variables declaration//GEN-END:variables
}
