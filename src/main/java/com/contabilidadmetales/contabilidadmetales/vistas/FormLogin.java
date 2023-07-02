/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.vistas;

import Clases_FrontEnd.PlaceHolder;
import com.contabilidadmetales.contabilidadmetales.ContabilidadMetales;
import com.contabilidadmetales.contabilidadmetales.controlador.CLogin;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author blood
 */
public class FormLogin extends javax.swing.JFrame {

    public FormLogin() {
        
        
        initComponents();
     this.setResizable(false); //para que no modifiquen el ancho y no se agrande
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;//consigo el ancho y alto de la pantalla

        this.setBounds((ancho / 2) - (this.getWidth() / 2), (alto / 2) - (this.getHeight() / 2), 702, 500); //determino el tamaño de la pantalla del sw , ancho , alto
    
         this.setLocationRelativeTo(this);//para q se ponga en el centro
         
         this.setIconImage(new ImageIcon("Imagenes/FormLogin/favicon.png").getImage());
         this.setTitle("Login");
         
         AsignacionImagenes();
         AsignacionPlaceHolder_Jtext();
    }

    public void AsignacionImagenes(){
    ImageIcon icon = new ImageIcon("Imagenes/FormLogin/city.png");
jLabelImagenLogin.setIcon(icon);

icon = new ImageIcon("Imagenes/FormLogin/favicon.png");
jLabelIcono.setIcon(icon);

icon = new ImageIcon("Imagenes/FormLogin/logo.png");
jLabelLogo.setIcon(icon);

icon = new ImageIcon("Imagenes/FormLogin/Ingresar.png");
jButtonIngresar.setIcon(icon);

icon = new ImageIcon("Imagenes/FormLogin/Registro.png");
jButtonRegistro.setIcon(icon);

icon = new ImageIcon("Imagenes/FormLogin/Registro2.png");
jButtonRegistro.setRolloverIcon(icon);
jButtonRegistro.setFocusPainted(false);

icon = new ImageIcon("Imagenes/FormLogin/Ver.png");
jButtonMostrarClave.setIcon(icon);
 icon = new ImageIcon("Imagenes/FormLogin/Ver2.png");
jButtonMostrarClave.setRolloverIcon(icon);//le asigno el relovvericon


    }
    
    public void AsignacionPlaceHolder_Jtext(){
     PlaceHolder placeholder = new PlaceHolder("Ingrese su nombre de usuario", txtUsuario, PlaceHolder.Show.ALWAYS, txtUsuario.LEFT);//aqui le asigon l oq se vera en el text q se borra al escribir
   placeholder = new PlaceHolder("Ingrese su clave", txtContrasenia, PlaceHolder.Show.ALWAYS, txtContrasenia.LEFT);
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonMostrarClave = new javax.swing.JButton();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelContraseña = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtContrasenia = new javax.swing.JPasswordField();
        jButtonIngresar = new javax.swing.JButton();
        jButtonRegistro = new javax.swing.JButton();
        jLabelRecuperacion_de_hierro = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jLabelImagenLogin = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelIcono = new javax.swing.JLabel();
        jLabelINICIARSESION = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(702, 500));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonMostrarClave.setBorderPainted(false);
        jButtonMostrarClave.setContentAreaFilled(false);
        jButtonMostrarClave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonMostrarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarClaveActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonMostrarClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 290, 50, 30));

        jLabelUsuario.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabelUsuario.setText("USUARIO");
        jPanel1.add(jLabelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 146, -1, -1));

        jLabelContraseña.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabelContraseña.setText("CONTRASEÑA");
        jPanel1.add(jLabelContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 256, -1, -1));

        txtUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtUsuario.setBorder(null);
        txtUsuario.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtUsuario.setSelectionColor(new java.awt.Color(200, 244, 254));
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 186, 260, 20));

        txtContrasenia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtContrasenia.setBorder(null);
        txtContrasenia.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtContrasenia.setSelectionColor(new java.awt.Color(200, 244, 254));
        txtContrasenia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraseniaKeyPressed(evt);
            }
        });
        jPanel1.add(txtContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 296, 260, -1));

        jButtonIngresar.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButtonIngresar.setText("Ingresar");
        jButtonIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonIngresar.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButtonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIngresarActionPerformed(evt);
            }
        });
        jButtonIngresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonIngresarKeyPressed(evt);
            }
        });
        jPanel1.add(jButtonIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 336, 160, -1));

        jButtonRegistro.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButtonRegistro.setContentAreaFilled(false);
        jButtonRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRegistro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistroActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 6, 80, 80));

        jLabelRecuperacion_de_hierro.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jLabelRecuperacion_de_hierro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRecuperacion_de_hierro.setText("Recuperación de hierro");
        jPanel1.add(jLabelRecuperacion_de_hierro, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 236, 230, -1));
        jPanel1.add(jLabelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 96, 150, 150));
        jPanel1.add(jLabelImagenLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 6, 300, 500));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 315, 260, 10));
        jPanel1.add(jLabelIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 26, 80, 40));

        jLabelINICIARSESION.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelINICIARSESION.setText("INICIAR SESIÓN");
        jPanel1.add(jLabelINICIARSESION, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 76, -1, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 206, 260, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void jButtonRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistroActionPerformed
       RegistrarProveedor usuario=new RegistrarProveedor(3);
        usuario.setVisible(true);
    }//GEN-LAST:event_jButtonRegistroActionPerformed

    private void jButtonIngresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonIngresarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonIngresarKeyPressed

    private void jButtonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIngresarActionPerformed
        CLogin objetoLogin = new CLogin();
        objetoLogin.validaUsuario(txtUsuario.getText(), txtContrasenia.getText(), this);
    }//GEN-LAST:event_jButtonIngresarActionPerformed

    private void txtContraseniaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseniaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CLogin objetoLogin = new CLogin();
            objetoLogin.validaUsuario(txtUsuario.getText(), txtContrasenia.getText(), this);

        }
    }//GEN-LAST:event_txtContraseniaKeyPressed
private boolean mostrar = false;
    private void jButtonMostrarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarClaveActionPerformed
    // Invierte el valor de mostrar
    mostrar = !mostrar;

    if (mostrar) {
        txtContrasenia.setEchoChar((char) 0); // Mostrar caracteres
           ImageIcon icon = new ImageIcon("Imagenes/FormLogin/NoVer.png");
jButtonMostrarClave.setIcon(icon);//le asigno otro icono

  icon = new ImageIcon("Imagenes/FormLogin/NoVer2.png");
jButtonMostrarClave.setRolloverIcon(icon);//le asigno el relovvericon

    } else {
        txtContrasenia.setEchoChar('\u25CF'); // Ocultar caracteres
         ImageIcon icon = new ImageIcon("Imagenes/FormLogin/Ver.png");
jButtonMostrarClave.setIcon(icon);//le asigno otro icono

  icon = new ImageIcon("Imagenes/FormLogin/Ver2.png");
jButtonMostrarClave.setRolloverIcon(icon);//le asigno el relovvericon
      
    }

    // Forzar el repintado del campo de texto
    txtContrasenia.repaint();
       
    }//GEN-LAST:event_jButtonMostrarClaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIngresar;
    private javax.swing.JButton jButtonMostrarClave;
    private javax.swing.JButton jButtonRegistro;
    private javax.swing.JLabel jLabelContraseña;
    private javax.swing.JLabel jLabelINICIARSESION;
    public javax.swing.JLabel jLabelIcono;
    public javax.swing.JLabel jLabelImagenLogin;
    public javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelRecuperacion_de_hierro;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
