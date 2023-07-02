/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.vistas;

import Clases_FrontEnd.PlaceHolder;
import Clases_FrontEnd.RoundBorder;
import com.contabilidadmetales.contabilidadmetales.controlador.CPrestamos;
import com.contabilidadmetales.contabilidadmetales.modelo.Prestamos;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.accessibility.AccessibleContext;
import javax.swing.ImageIcon;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author narut
 */
public class RegistrarPrestamo extends javax.swing.JFrame {

    /**
     * Creates new form RegistrarPrestamo
     */
    private JTable j;
    public RegistrarPrestamo(JTable j) {
        initComponents();
        this.j=j;
         this.setLocationRelativeTo(this);//para q se ponga en el centro
          this.setResizable(false); //para que no modifiquen el ancho y no se agrande
          
         this.setTitle("Registrar Prestamo");
          this.setIconImage(new ImageIcon("Imagenes/FormLogin/favicon.png").getImage());
         
         Agregacion_imagenes();
        Ajustamiento_Objetos();
    }

    public void Agregacion_imagenes(){
     ImageIcon icon = new ImageIcon("Imagenes/RegistrarPrestamo/ImagenRegistro.png");
//esto es pa reducir la imagen
 Image imagenRedimensionada = icon.getImage().getScaledInstance(
        (int) (icon.getIconWidth() * 64 / 100),//el primer numero es el porcentage pa reducir la imagen
        (int) (icon.getIconHeight() * 64 / 100),//64
        Image.SCALE_SMOOTH
    );
  jLabelImagenRegistro.setIcon(new ImageIcon(imagenRedimensionada));
  
  
  icon = new ImageIcon("Imagenes/RegistrarPrestamo/Registrar.png");
BotonResitrar.setIcon(icon);

icon = new ImageIcon("Imagenes/RegistrarPrestamo/Cancelar.png");
BotonResitrar1.setIcon(icon);
    }
    
    public void Ajustamiento_Objetos(){
        //le digo q donde posicione lo q se escriba
      NombrePersona.setHorizontalAlignment(NombrePersona.CENTER);
       ValorPrestamo.setHorizontalAlignment(NombrePersona.CENTER);
      
       //aqui le asigon lo q se vera en el text q se borra al escribir
             PlaceHolder placeholder = new PlaceHolder("Nombre completo", NombrePersona, PlaceHolder.Show.ALWAYS, NombrePersona.CENTER);
                 placeholder = new PlaceHolder("Valor del préstamo", ValorPrestamo, PlaceHolder.Show.ALWAYS, ValorPrestamo.CENTER);
                       placeholder = new PlaceHolder("Por favor, ingrese la descripción", DescripcionPrestamo, PlaceHolder.Show.ALWAYS, ValorPrestamo.CENTER);
             
             // Establece el color del borde y el radio de los bordes redondos de los Jtext,todo esto se envia a la clase RoundBorder
          NombrePersona.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
          ValorPrestamo.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
          jScrollPane1.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ValorPrestamo = new javax.swing.JTextField();
        NombrePersona = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        DescripcionPrestamo = new javax.swing.JTextArea();
        BotonResitrar = new javax.swing.JButton();
        BotonResitrar1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelImagenRegistro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(465, 520));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(450, 480));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ValorPrestamo.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        ValorPrestamo.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        ValorPrestamo.setSelectionColor(new java.awt.Color(200, 244, 254));
        ValorPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValorPrestamoActionPerformed(evt);
            }
        });
        ValorPrestamo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ValorPrestamoKeyTyped(evt);
            }
        });
        jPanel1.add(ValorPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 300, 34));

        NombrePersona.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        NombrePersona.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        NombrePersona.setSelectionColor(new java.awt.Color(200, 244, 254));
        NombrePersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombrePersonaKeyTyped(evt);
            }
        });
        jPanel1.add(NombrePersona, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 300, 34));

        DescripcionPrestamo.setColumns(20);
        DescripcionPrestamo.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        DescripcionPrestamo.setLineWrap(true);
        DescripcionPrestamo.setRows(5);
        DescripcionPrestamo.setWrapStyleWord(true);
        DescripcionPrestamo.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        DescripcionPrestamo.setSelectionColor(new java.awt.Color(200, 244, 254));
        jScrollPane1.setViewportView(DescripcionPrestamo);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 300, 143));

        BotonResitrar.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        BotonResitrar.setText("Registrar");
        BotonResitrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonResitrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonResitrarActionPerformed(evt);
            }
        });
        jPanel1.add(BotonResitrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 150, -1));

        BotonResitrar1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        BotonResitrar1.setText("Cancelar");
        BotonResitrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonResitrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonResitrar1ActionPerformed(evt);
            }
        });
        jPanel1.add(BotonResitrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 150, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Descripción:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Registro Préstamo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        jLabelImagenRegistro.setFont(new java.awt.Font("Dialog", 1, 32)); // NOI18N
        jPanel1.add(jLabelImagenRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 0, 450, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void ValorPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValorPrestamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ValorPrestamoActionPerformed

    private void BotonResitrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonResitrarActionPerformed
     String nombre=NombrePersona.getText();
        Double Valor=Double.parseDouble(ValorPrestamo.getText());
        String Descripcion=DescripcionPrestamo.getText();
        CPrestamos c=new CPrestamos();
        c.RegistrarPrestamo(nombre, Valor, Descripcion);
        DefaultTableModel model=(DefaultTableModel)j.getModel();
        String[]s={nombre,Valor.toString(),Descripcion};
        model.addRow(s);
        this.dispose();
    }//GEN-LAST:event_BotonResitrarActionPerformed

    private void BotonResitrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonResitrar1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BotonResitrar1ActionPerformed

    private void ValorPrestamoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ValorPrestamoKeyTyped
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
    }//GEN-LAST:event_ValorPrestamoKeyTyped

    private void NombrePersonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombrePersonaKeyTyped
   //todo esto es para validar solo letras
        char C = evt.getKeyChar();

        if (Character.isDigit(C)) {
            getToolkit().beep(); //esto lo que ahce es hacer un sonido de beep
            evt.consume();

        }
    }//GEN-LAST:event_NombrePersonaKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonResitrar;
    private javax.swing.JButton BotonResitrar1;
    private javax.swing.JTextArea DescripcionPrestamo;
    private javax.swing.JTextField NombrePersona;
    private javax.swing.JTextField ValorPrestamo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelImagenRegistro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
