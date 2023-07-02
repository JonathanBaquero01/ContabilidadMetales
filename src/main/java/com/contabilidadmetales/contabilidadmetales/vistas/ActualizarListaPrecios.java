/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.vistas;

import Clases_FrontEnd.PlaceHolder;
import Clases_FrontEnd.RoundBorder;
import com.contabilidadmetales.contabilidadmetales.controlador.CPersona;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

/**
 *
 * @author narut
 */
public class ActualizarListaPrecios extends javax.swing.JFrame {

    /**
     * Creates new form ActualizarListaPrecios
     */
    int s;

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public ActualizarListaPrecios(String as, int lt) {
        initComponents();
        String[] lineas = as.split(" ");
        as = String.join("\n", lineas);
        Lista.append(as.toString());
        this.s = lt;
        
             this.setLocationRelativeTo(this);//para q se ponga en el centro
          this.setIconImage(new ImageIcon("Imagenes/FormLogin/favicon.png").getImage());
         this.setTitle("Actualizar lista");
        
        AsignacionPlaceHolder_Jtext();
        AsignacionImagenes();

    }

   public void  AsignacionPlaceHolder_Jtext(){
   PlaceHolder placeholder = new PlaceHolder("Actualice la lista", Lista);
        
              
             // Establece el color del borde y el radio de los bordes redondos de los Jtext,todo esto se envia a la clase RoundBorder
          Lista.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
   }
   
   public void AsignacionImagenes(){
   ImageIcon icon = new ImageIcon("Imagenes/FTipoMovimiento/Guardar.png");
jMenuGuardar.setIcon(icon);

     icon = new ImageIcon("Imagenes/FTipoMovimiento/Guardar2.png");
jMenuGuardar.setRolloverIcon(icon);

   icon = new ImageIcon("Imagenes/FTipoMovimiento/Cancelar.png");
jMenuCancelar.setIcon(icon);
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Lista = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuGuardar = new javax.swing.JMenu();
        jMenuCancelar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Lista.setColumns(20);
        Lista.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        Lista.setRows(5);
        Lista.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        Lista.setSelectionColor(new java.awt.Color(200, 244, 254));
        jScrollPane1.setViewportView(Lista);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
        );

        jMenuBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenuGuardar.setText("Guardar");
        jMenuGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuGuardarMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuGuardar);

        jMenuCancelar.setText("Cancelar");
        jMenuCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuCancelarMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuCancelar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
CPersona ca ;
    private void jMenuGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuGuardarMouseClicked
        ca= new CPersona();
        String as=Lista.getText();
        String[] lineas = as.split("\n");
        as = String.join(" ", lineas);
        ca.modificarArchivo(as, s);
        this.dispose();
    }//GEN-LAST:event_jMenuGuardarMouseClicked

    private void jMenuCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuCancelarMouseClicked
      this.dispose();
    }//GEN-LAST:event_jMenuCancelarMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Lista;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCancelar;
    private javax.swing.JMenu jMenuGuardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
