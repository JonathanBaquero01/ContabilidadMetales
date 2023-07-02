/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.vistas;

import Clases_FrontEnd.CustomComboBoxRenderer;
import Clases_FrontEnd.PlaceHolder;
import Clases_FrontEnd.RoundBorder;
import com.contabilidadmetales.contabilidadmetales.controlador.CPersona;
import com.contabilidadmetales.contabilidadmetales.controlador.Materiales;
import com.contabilidadmetales.contabilidadmetales.modelo.Material;
import com.contabilidadmetales.contabilidadmetales.modelo.persona;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author narut
 */
public class RegistrarPersona extends javax.swing.JFrame {

    /**
     * Creates new form RegistrarProveedor
     */
    private JTable table;
    int TipoDePersona;
    Materiales materiales;

    public RegistrarPersona(JTable table) {
        initComponents();
        this.table = table;
        Contraseña.setVisible(false);
        materiales = new Materiales();
        
         this.setLocationRelativeTo(this);//para q se ponga en el centro
          this.setResizable(false); //para que no modifiquen el ancho y no se agrande
          
           this.setIconImage(new ImageIcon("Imagenes/FormLogin/favicon.png").getImage());
         this.setTitle("Registrar Proveedor");
          
         Agregacion_imagenes();
        Ajustamiento_Objetos();
        
        listarMaterialesConValor();
    }
    
    public void Agregacion_imagenes(){
     ImageIcon icon = new ImageIcon("Imagenes/RegistrarPersona/favicon.png");

//esto es pa reducir la imagen
Image imagenRedimensionada = icon.getImage().getScaledInstance(
        (int) (icon.getIconWidth() * 55 / 100),//el primer numero es el porcentage pa reducir la imagen
        (int) (icon.getIconHeight() * 55 / 100),
        Image.SCALE_SMOOTH
    );
   jLabelIcono.setIcon(new ImageIcon(imagenRedimensionada));
   
   
    icon = new ImageIcon("Imagenes/RegistrarPersona/ImagenRegistro.png");
//esto es pa reducir la imagen
 imagenRedimensionada = icon.getImage().getScaledInstance(
        (int) (icon.getIconWidth() * 65 / 100),//el primer numero es el porcentage pa reducir la imagen
        (int) (icon.getIconHeight() * 65 / 100),
        Image.SCALE_SMOOTH
    );
  jLabelImagenRegistro.setIcon(new ImageIcon(imagenRedimensionada));
  
  
  //botones iconos
icon = new ImageIcon("Imagenes/RegistrarPersona/RegistrarLista.png");
BotonResitrar2.setIcon(icon);
 icon = new ImageIcon("Imagenes/RegistrarPersona/RegistrarLista2.png");
BotonResitrar2.setRolloverIcon(icon);//le asigno el relovvericon

icon = new ImageIcon("Imagenes/RegistrarPersona/Cancelar.png");
BotonResitrar1.setIcon(icon);

icon = new ImageIcon("Imagenes/RegistrarPersona/Registrar.png");
BotonResitrar.setIcon(icon);


    }
    
    public void Ajustamiento_Objetos(){
        TipoComboBox.setEnabled(false);
            //le digo q donde posicione lo q se escriba
      NombreTF.setHorizontalAlignment(NombreTF.CENTER);
      IdentificacionTF.setHorizontalAlignment(IdentificacionTF.CENTER);
        CelularTF.setHorizontalAlignment(CelularTF.CENTER);
        MaterialTF.setHorizontalAlignment(MaterialTF.CENTER);
           ValorTF.setHorizontalAlignment(ValorTF.CENTER);
 
      
       //aqui le asigon lo q se vera en el text q se borra al escribir
             PlaceHolder placeholder = new PlaceHolder("Nombre completo", NombreTF, PlaceHolder.Show.ALWAYS, NombreTF.CENTER);
             placeholder = new PlaceHolder("Número de identificación", IdentificacionTF, PlaceHolder.Show.ALWAYS, IdentificacionTF.CENTER);
              placeholder = new PlaceHolder("Número de celular", CelularTF, PlaceHolder.Show.ALWAYS, CelularTF.CENTER);
                placeholder = new PlaceHolder("Por favor, ingrese la descripción", DescripcionTA, PlaceHolder.Show.ALWAYS, NombreTF.CENTER);
                 placeholder = new PlaceHolder("Material", MaterialTF, PlaceHolder.Show.ALWAYS, MaterialTF.CENTER);
                 placeholder = new PlaceHolder("Valor(COP$)", ValorTF, PlaceHolder.Show.ALWAYS, ValorTF.CENTER);
              
             // Establece el color del borde y el radio de los bordes redondos de los Jtext,todo esto se envia a la clase RoundBorder
          NombreTF.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
           IdentificacionTF.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
            CelularTF.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
               MaterialTF.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
               ValorTF.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
               jScrollPane2.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
               jScrollPane1.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
          
     //aqui le q vaya a la clase que PERSONALIZA LOS COLORES DEL JCOMBOBX
    TipoIdentificacionTF.setRenderer(new CustomComboBoxRenderer());
         TipoComboBox.setRenderer(new CustomComboBoxRenderer());
    }

    public void listarMaterialesConValor() {
        ListaDePresiosTA.setText("");
        for (Material material : materiales.listarMateriales()) {
            ListaDePresiosTA.append( material.getNombre() + "," + material.getValor() + "\n");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        NombreTF = new javax.swing.JTextField();
        TipoIdentificacionTF = new javax.swing.JComboBox<>();
        IdentificacionTF = new javax.swing.JTextField();
        CelularTF = new javax.swing.JTextField();
        BotonResitrar = new javax.swing.JButton();
        BotonResitrar1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaDePresiosTA = new javax.swing.JTextArea();
        MaterialTF = new javax.swing.JTextField();
        ValorTF = new javax.swing.JTextField();
        BotonResitrar2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        DescripcionTA = new javax.swing.JTextArea();
        TipoComboBox = new javax.swing.JComboBox<>();
        Contraseña = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabelIcono = new javax.swing.JLabel();
        jLabelImagenRegistro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.white);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NombreTF.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        NombreTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        NombreTF.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        NombreTF.setSelectionColor(new java.awt.Color(200, 244, 254));
        NombreTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombreTFKeyTyped(evt);
            }
        });
        jPanel1.add(NombreTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 300, 30));

        TipoIdentificacionTF.setBackground(new java.awt.Color(218, 247, 254));
        TipoIdentificacionTF.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        TipoIdentificacionTF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cc" }));
        TipoIdentificacionTF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(TipoIdentificacionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 140, -1));

        IdentificacionTF.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        IdentificacionTF.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        IdentificacionTF.setSelectionColor(new java.awt.Color(200, 244, 254));
        IdentificacionTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                IdentificacionTFKeyTyped(evt);
            }
        });
        jPanel1.add(IdentificacionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 300, 30));

        CelularTF.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        CelularTF.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        CelularTF.setSelectionColor(new java.awt.Color(200, 244, 254));
        CelularTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CelularTFKeyTyped(evt);
            }
        });
        jPanel1.add(CelularTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 300, 30));

        BotonResitrar.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        BotonResitrar.setText("Registrar");
        BotonResitrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonResitrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonResitrarActionPerformed(evt);
            }
        });
        jPanel1.add(BotonResitrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 710, 150, -1));

        BotonResitrar1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        BotonResitrar1.setText("Cancelar");
        BotonResitrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonResitrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonResitrar1ActionPerformed(evt);
            }
        });
        jPanel1.add(BotonResitrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 710, 150, 40));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Lista de precios");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, -1, -1));

        ListaDePresiosTA.setEditable(false);
        ListaDePresiosTA.setColumns(20);
        ListaDePresiosTA.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        ListaDePresiosTA.setRows(5);
        ListaDePresiosTA.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        ListaDePresiosTA.setSelectionColor(new java.awt.Color(200, 244, 254));
        jScrollPane1.setViewportView(ListaDePresiosTA);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, 300, 140));

        MaterialTF.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        MaterialTF.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        MaterialTF.setSelectionColor(new java.awt.Color(200, 244, 254));
        jPanel1.add(MaterialTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 140, 30));

        ValorTF.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        ValorTF.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        ValorTF.setSelectionColor(new java.awt.Color(200, 244, 254));
        ValorTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ValorTFKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ValorTFKeyTyped(evt);
            }
        });
        jPanel1.add(ValorTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 490, 140, 30));

        BotonResitrar2.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BotonResitrar2.setBorderPainted(false);
        BotonResitrar2.setContentAreaFilled(false);
        BotonResitrar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonResitrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonResitrar2ActionPerformed(evt);
            }
        });
        jPanel1.add(BotonResitrar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 484, 40, 40));

        DescripcionTA.setColumns(20);
        DescripcionTA.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        DescripcionTA.setLineWrap(true);
        DescripcionTA.setRows(5);
        DescripcionTA.setWrapStyleWord(true);
        DescripcionTA.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        DescripcionTA.setSelectionColor(new java.awt.Color(200, 244, 254));
        jScrollPane2.setViewportView(DescripcionTA);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 300, 130));

        TipoComboBox.setBackground(new java.awt.Color(218, 247, 254));
        TipoComboBox.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        TipoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Proveedor", "Cliente", "Usuario" }));
        TipoComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TipoComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TipoComboBoxMouseClicked(evt);
            }
        });
        TipoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(TipoComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 160, -1));

        Contraseña.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jPanel1.add(Contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 80, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Tipo de persona: ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 140, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("REGISTRO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Descripción:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Tipo de documento:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));
        jPanel1.add(jLabelIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 110, 60));
        jPanel1.add(jLabelImagenRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, -2, 460, 780));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 766, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private persona nuevoPro;

    public persona getNuevoPro() {
        return nuevoPro;
    }

    public void setNuevoPro(persona nuevoPro) {
        this.nuevoPro = nuevoPro;
    }

    public void tabla() {
        String[] pro = new String[5];
        pro[0] = nuevoPro.getNombre();
        pro[1] = nuevoPro.getCelular();
        pro[2] = nuevoPro.getIdentificacion();
        pro[3] = nuevoPro.getTipoDocumento_nombre();
        pro[4] = nuevoPro.getDescripcion();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(pro);
    }


    private void BotonResitrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonResitrarActionPerformed
        switch (TipoComboBox.getSelectedItem().toString()) {
            case "Cliente":
                TipoDePersona = 2;
                break;
            case "Proveedor":
                TipoDePersona = 1;
                break;
            case "Usuario":
                TipoDePersona = 3;
                break;
            default:
                throw new AssertionError();
        }
        String nombre, Celular, identificacion, TipoDocumento;
        nombre = NombreTF.getText();
        Celular = CelularTF.getText();
        identificacion = IdentificacionTF.getText();
        TipoDocumento = (String) TipoIdentificacionTF.getSelectedItem();
        nuevoPro = new persona(nombre, Celular, identificacion, TipoDocumento, ListaDePresiosTA.getText(), DescripcionTA.getText());
        this.setNuevoPro(nuevoPro);
        CPersona Proveedor = new CPersona();
        Proveedor.registrarProvedor(nuevoPro, TipoDePersona, Contraseña.getText());
        this.dispose();
        tabla();

    }//GEN-LAST:event_BotonResitrarActionPerformed

    private void BotonResitrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonResitrar1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BotonResitrar1ActionPerformed

    private void BotonResitrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonResitrar2ActionPerformed
        // TODO add your handling code here:
        String nombreMaterial = MaterialTF.getText().trim();

        double nuevoValor = Double.parseDouble(ValorTF.getText().replaceAll(",", "").replaceAll("\\.", ""));

        // Buscamos el material en la tabla
        Material material = materiales.obtenerMaterialPorNombre(nombreMaterial);

        if (material != null) {
            // Si el material existe, comparamos su valor actual con el nuevo valor
            if (material.getValor() != nuevoValor) {
                // Si el valor es distinto, actualizamos el registro en la tabla
                material.setValor(nuevoValor);
                if (materiales.actualizarMaterial(material)) {
                    JOptionPane.showConfirmDialog(null, "Material actualizado correctamente");
                    listarMaterialesConValor();
                } else {
                    JOptionPane.showConfirmDialog(null, "Error al actualizar el material");
                }
            } else {
                // Si el valor es el mismo, no hacemos nada
                JOptionPane.showConfirmDialog(null, "El material ya tiene ese valor");
            }
        } else {
            // Si el material no existe, lo insertamos en la tabla
            Material nuevoMaterial = new Material();
            nuevoMaterial.setNombre(nombreMaterial);
            nuevoMaterial.setValor(nuevoValor);
            if (materiales.insertarMaterial(nuevoMaterial)) {
               // JOptionPane.showConfirmDialog(null, "Material insertado correctamente");
                listarMaterialesConValor();
            } else {
                JOptionPane.showConfirmDialog(null, "Error al insertar el material");
            }
        }
    }//GEN-LAST:event_BotonResitrar2ActionPerformed

    private void TipoComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipoComboBoxMouseClicked
        // TODO add your handling code her

    }//GEN-LAST:event_TipoComboBoxMouseClicked

    private void TipoComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoComboBoxActionPerformed
        // TODO add your handling code here:
        if (TipoComboBox.getSelectedItem().toString().equals("Usuario")) {
            Contraseña.setVisible(true);
        } else {
            Contraseña.setVisible(false);
        }
    }//GEN-LAST:event_TipoComboBoxActionPerformed

    private void ValorTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ValorTFKeyReleased
        try {
            // Obtener el texto ingresado en el campo de texto
                String input = ValorTF.getText();

                // Eliminar caracteres no numéricos
                String numericInput = input.replaceAll("[^0-9]", "");

                // Convertir el texto numérico a un valor entero
                int number = Integer.parseInt(numericInput);

                // Formatear el número en formato COP con separación de miles
                NumberFormat numberFormat = NumberFormat.getInstance();
                String formattedInput = numberFormat.format(number);

                // Establecer el texto formateado en el campo de texto
                ValorTF.setText(formattedInput);
        } catch (Exception e) {
        }
      
        

    }//GEN-LAST:event_ValorTFKeyReleased

    private void IdentificacionTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IdentificacionTFKeyTyped
      //esto para valdiar solo numeros
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
    }//GEN-LAST:event_IdentificacionTFKeyTyped

    private void CelularTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CelularTFKeyTyped
//esto para valdiar solo numeros
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
    }//GEN-LAST:event_CelularTFKeyTyped

    private void ValorTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ValorTFKeyTyped
       //esto para valdiar solo numeros
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
    }//GEN-LAST:event_ValorTFKeyTyped

    private void NombreTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreTFKeyTyped
        //todo esto es para validar solo letras
        char C = evt.getKeyChar();

        if (Character.isDigit(C)) {
            getToolkit().beep(); //esto lo que ahce es hacer un sonido de beep
            evt.consume();

        }
    }//GEN-LAST:event_NombreTFKeyTyped

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonResitrar;
    private javax.swing.JButton BotonResitrar1;
    private javax.swing.JButton BotonResitrar2;
    private javax.swing.JTextField CelularTF;
    private javax.swing.JTextField Contraseña;
    private javax.swing.JTextArea DescripcionTA;
    private javax.swing.JTextField IdentificacionTF;
    private javax.swing.JTextArea ListaDePresiosTA;
    private javax.swing.JTextField MaterialTF;
    private javax.swing.JTextField NombreTF;
    private javax.swing.JComboBox<String> TipoComboBox;
    private javax.swing.JComboBox<String> TipoIdentificacionTF;
    private javax.swing.JTextField ValorTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelIcono;
    private javax.swing.JLabel jLabelImagenRegistro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
