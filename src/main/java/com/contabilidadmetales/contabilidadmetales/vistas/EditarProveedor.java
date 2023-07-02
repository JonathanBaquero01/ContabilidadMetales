/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.vistas;


import Clases_FrontEnd.CustomComboBoxRenderer;
import Clases_FrontEnd.PlaceHolder;
import Clases_FrontEnd.RoundBorder;
import com.contabilidadmetales.contabilidadmetales.controlador.CPersona;
import com.contabilidadmetales.contabilidadmetales.modelo.persona;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 *
 * @author narut
 */
public class EditarProveedor extends javax.swing.JFrame {

    /**
     * Creates new form RegistrarProveedor
     */
    private CPersona Proveedor = new CPersona();

    public EditarProveedor(int id) {
        initComponents();
        persona nuevoP = Proveedor.Leerpersonas(id);
        ListaDePresiosTA.setText(nuevoP.getArchivoLista());
        NombreTF.setText(nuevoP.getNombre());
        CelularTF.setText(nuevoP.getCelular());
        IdentificacionTF.setText(nuevoP.getIdentificacion());
        DescripcionTA.setText(nuevoP.getDescripcion());
        
           this.setLocationRelativeTo(this);//para q se ponga en el centro
          this.setResizable(false); //para que no modifiquen el ancho y no se agrande
          
           this.setIconImage(new ImageIcon("Imagenes/FormLogin/favicon.png").getImage());
         this.setTitle("Editar persona");
          
         Agregacion_imagenes();
        Ajustamiento_Objetos();
    
    }

    public void Agregacion_imagenes(){
         ImageIcon icon = new ImageIcon("Imagenes/EditarProveedor/favicon.png");

//esto es pa reducir la imagen
Image imagenRedimensionada = icon.getImage().getScaledInstance(
        (int) (icon.getIconWidth() * 55 / 100),//el primer numero es el porcentage pa reducir la imagen
        (int) (icon.getIconHeight() * 55 / 100),
        Image.SCALE_SMOOTH
    );
   jLabelIcono.setIcon(new ImageIcon(imagenRedimensionada));
   
   
    icon = new ImageIcon("Imagenes/EditarProveedor/ImagenRegistro.png");
//esto es pa reducir la imagen
 imagenRedimensionada = icon.getImage().getScaledInstance(
        (int) (icon.getIconWidth() * 65 / 100),//el primer numero es el porcentage pa reducir la imagen
        (int) (icon.getIconHeight() * 65 / 100),
        Image.SCALE_SMOOTH
    );
  jLabelImagenRegistro.setIcon(new ImageIcon(imagenRedimensionada));
  
  
  //botones iconos
icon = new ImageIcon("Imagenes/EditarProveedor/RegistrarLista.png");
BotonResitrar2.setIcon(icon);
 icon = new ImageIcon("Imagenes/EditarProveedor/RegistrarLista2.png");
BotonResitrar2.setRolloverIcon(icon);//le asigno el relovvericon

icon = new ImageIcon("Imagenes/EditarProveedor/Cancelar.png");
BotonResitrar1.setIcon(icon);

icon = new ImageIcon("Imagenes/EditarProveedor/Registrar.png");
BotonResitrar.setIcon(icon);

    }
    
    public void Ajustamiento_Objetos(){
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
             jScrollPane2.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
              MaterialTF.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
           ValorTF.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
           jScrollPane1.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
          
          //aqui le q vaya a la clase que PERSONALIZA LOS COLORES DEL JCOMBOBX
    TipoIdentificacionTF.setRenderer(new CustomComboBoxRenderer());
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        NombreTF = new javax.swing.JTextField();
        TipoIdentificacionTF = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        IdentificacionTF = new javax.swing.JTextField();
        CelularTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        BotonResitrar = new javax.swing.JButton();
        BotonResitrar1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaDePresiosTA = new javax.swing.JTextArea();
        MaterialTF = new javax.swing.JTextField();
        ValorTF = new javax.swing.JTextField();
        BotonResitrar2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        DescripcionTA = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabelIcono = new javax.swing.JLabel();
        jLabelImagenRegistro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.white);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NombreTF.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
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
        TipoIdentificacionTF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cc", "PDP", "V" }));
        TipoIdentificacionTF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(TipoIdentificacionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 178, 140, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Tipo de documento:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Lista de precios");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, -1, -1));

        IdentificacionTF.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        IdentificacionTF.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        IdentificacionTF.setSelectionColor(new java.awt.Color(200, 244, 254));
        IdentificacionTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                IdentificacionTFKeyTyped(evt);
            }
        });
        jPanel1.add(IdentificacionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 300, 30));

        CelularTF.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        CelularTF.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        CelularTF.setSelectionColor(new java.awt.Color(200, 244, 254));
        CelularTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CelularTFKeyTyped(evt);
            }
        });
        jPanel1.add(CelularTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 300, 30));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Descripción:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));

        BotonResitrar.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        BotonResitrar.setText("Registrar");
        BotonResitrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonResitrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonResitrarActionPerformed(evt);
            }
        });
        jPanel1.add(BotonResitrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, 150, -1));

        BotonResitrar1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        BotonResitrar1.setText("Cancelar");
        BotonResitrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonResitrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonResitrar1ActionPerformed(evt);
            }
        });
        jPanel1.add(BotonResitrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 680, 150, -1));

        ListaDePresiosTA.setColumns(20);
        ListaDePresiosTA.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        ListaDePresiosTA.setLineWrap(true);
        ListaDePresiosTA.setRows(5);
        ListaDePresiosTA.setWrapStyleWord(true);
        ListaDePresiosTA.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        ListaDePresiosTA.setSelectionColor(new java.awt.Color(200, 244, 254));
        jScrollPane1.setViewportView(ListaDePresiosTA);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 300, 140));

        MaterialTF.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        MaterialTF.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        MaterialTF.setSelectionColor(new java.awt.Color(200, 244, 254));
        jPanel1.add(MaterialTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, 140, 30));

        ValorTF.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        ValorTF.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        ValorTF.setSelectionColor(new java.awt.Color(200, 244, 254));
        ValorTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValorTFActionPerformed(evt);
            }
        });
        ValorTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ValorTFKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ValorTFKeyTyped(evt);
            }
        });
        jPanel1.add(ValorTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 460, 140, 30));

        BotonResitrar2.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BotonResitrar2.setBorderPainted(false);
        BotonResitrar2.setContentAreaFilled(false);
        BotonResitrar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonResitrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonResitrar2ActionPerformed(evt);
            }
        });
        jPanel1.add(BotonResitrar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 456, 40, 40));

        DescripcionTA.setColumns(20);
        DescripcionTA.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        DescripcionTA.setRows(5);
        DescripcionTA.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        DescripcionTA.setSelectionColor(new java.awt.Color(200, 244, 254));
        jScrollPane2.setViewportView(DescripcionTA);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 300, 130));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("REGISTRO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));
        jPanel1.add(jLabelIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 110, 60));
        jPanel1.add(jLabelImagenRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 460, 740));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonResitrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonResitrarActionPerformed
        String nombre, Celular, identificacion, TipoDocumento;
        nombre = NombreTF.getText();
        Celular = CelularTF.getText();
        identificacion = IdentificacionTF.getText();
        TipoDocumento = (String) TipoIdentificacionTF.getSelectedItem();
       // byte[] archivo = manejo.escribir( ListaDePresiosTA.getText());
        persona nuevoPro = new  persona(nombre, Celular, identificacion, TipoDocumento, ListaDePresiosTA.getText(),DescripcionTA.getText());
       // Proveedor.registrarProvedor(nuevoPro,1);
        this.setVisible(false);
    }//GEN-LAST:event_BotonResitrarActionPerformed

    private void BotonResitrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonResitrar1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_BotonResitrar1ActionPerformed

    private void BotonResitrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonResitrar2ActionPerformed
        // TODO add your handling code here:
        ListaDePresiosTA.append(MaterialTF.getText() + "," + ValorTF.getText().replaceAll(",", "").replaceAll("\\.", "") + "\n");
    }//GEN-LAST:event_BotonResitrar2ActionPerformed

    private void ValorTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValorTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ValorTFActionPerformed

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
    private javax.swing.JTextArea DescripcionTA;
    private javax.swing.JTextField IdentificacionTF;
    private javax.swing.JTextArea ListaDePresiosTA;
    private javax.swing.JTextField MaterialTF;
    private javax.swing.JTextField NombreTF;
    private javax.swing.JComboBox<String> TipoIdentificacionTF;
    private javax.swing.JTextField ValorTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelIcono;
    private javax.swing.JLabel jLabelImagenRegistro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
