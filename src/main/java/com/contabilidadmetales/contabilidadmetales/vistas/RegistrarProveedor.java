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
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author narut
 */
public class RegistrarProveedor extends javax.swing.JFrame {

    /**
     * Creates new form RegistrarProveedor
     */
    private JTable table;
    int TipoDePersona;
    Materiales materiales;

    public RegistrarProveedor(JTable table) {
        initComponents2();
        this.table = table;
        Contraseña.setVisible(false);
        materiales = new Materiales();
         this.setLocationRelativeTo(this);//para q se ponga en el centro
          this.setTitle("Registrar Persona");
        listarMaterialesConValor();
       
    }

    //CAMBIOS ARGUELLO
    public RegistrarProveedor(int TipoDePersona) {
        initComponents();
        this.TipoDePersona = TipoDePersona;
        TipoComboBox.setSelectedItem("Usuario");
        TipoComboBox.setEnabled(false);
         this.setLocationRelativeTo(this);//para q se ponga en el centro
          this.setResizable(false); //para que no modifiquen el ancho y no se agrande
          
           this.setIconImage(new ImageIcon("Imagenes/FormLogin/favicon.png").getImage());
         this.setTitle("Registrar usuario");
          
         Agregacion_imagenes();
        Ajustamiento_Objetos();

    }

    public void listarMaterialesConValor() {
        ListaDePresiosTA.setText("");
        for (Material material : materiales.listarMateriales()) {
            ListaDePresiosTA.append(material.getNombre() + "," + material.getValor() + "\n");
        }

    }

    public void Agregacion_imagenes(){
   ImageIcon icon = new ImageIcon("Imagenes/RegistrarProveedor/favicon.png");

//esto es pa reducir la imagen
Image imagenRedimensionada = icon.getImage().getScaledInstance(
        (int) (icon.getIconWidth() * 55 / 100),//el primer numero es el porcentage pa reducir la imagen
        (int) (icon.getIconHeight() * 55 / 100),
        Image.SCALE_SMOOTH
    );
   jLabelIcono.setIcon(new ImageIcon(imagenRedimensionada));
   
   
    icon = new ImageIcon("Imagenes/RegistrarProveedor/ImagenRegistro.png");
//esto es pa reducir la imagen
 imagenRedimensionada = icon.getImage().getScaledInstance(
        (int) (icon.getIconWidth() * 65 / 100),//el primer numero es el porcentage pa reducir la imagen
        (int) (icon.getIconHeight() * 65 / 100),
        Image.SCALE_SMOOTH
    );
  jLabelImagenRegistro.setIcon(new ImageIcon(imagenRedimensionada));
  
  //botones iconos
  icon = new ImageIcon("Imagenes/RegistrarProveedor/Ver.png");
jButtonMostrarClave.setIcon(icon);
 icon = new ImageIcon("Imagenes/RegistrarProveedor/Ver2.png");
jButtonMostrarClave.setRolloverIcon(icon);//le asigno el relovvericon

icon = new ImageIcon("Imagenes/RegistrarProveedor/Registrar.png");
BotonResitrar.setIcon(icon);

icon = new ImageIcon("Imagenes/RegistrarProveedor/Cancelar.png");
BotonResitrar1.setIcon(icon);
    }
    
    public void Ajustamiento_Objetos(){
         //le digo q donde posicione lo q se escriba
          NombreTF.setHorizontalAlignment(NombreTF.CENTER);
            Contraseña.setHorizontalAlignment(Contraseña.CENTER);
               IdentificacionTF.setHorizontalAlignment(IdentificacionTF.CENTER);
             CelularTF.setHorizontalAlignment(CelularTF.CENTER);
        //aqui le asigon lo q se vera en el text q se borra al escribir
             PlaceHolder placeholder = new PlaceHolder("Nombre completo", NombreTF, PlaceHolder.Show.ALWAYS, NombreTF.CENTER);
          placeholder = new PlaceHolder("Contraseña", Contraseña, PlaceHolder.Show.ALWAYS, Contraseña.CENTER);
                 placeholder = new PlaceHolder("Número de identificación", IdentificacionTF, PlaceHolder.Show.ALWAYS, IdentificacionTF.CENTER);
    placeholder = new PlaceHolder("Número de celular", CelularTF, PlaceHolder.Show.ALWAYS, Contraseña.CENTER);
    placeholder = new PlaceHolder("Por favor, ingrese la descripción", DescripcionTA, PlaceHolder.Show.ALWAYS, Contraseña.CENTER);
          
// Establece el color del borde y el radio de los bordes redondos de los Jtext,todo esto se envia a la clase RoundBorder
          NombreTF.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
           Contraseña.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
           IdentificacionTF.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
           CelularTF.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
           DescripcionTA.setBorder(new RoundBorder(new Color(104, 161, 189), 15, 2));
           
            //aqui le q vaya a la clase que PERSONALIZA LOS COLORES DEL JCOMBOBX
    TipoIdentificacionTF.setRenderer(new CustomComboBoxRenderer());
         TipoComboBox.setRenderer(new CustomComboBoxRenderer());
    }
 
    
    
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField MaterialTF;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField ValorTF;
    private javax.swing.JButton BotonResitrar2;
  private void BotonResitrar2ActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        String nombreMaterial = MaterialTF.getText().trim();

        double nuevoValor = Double.parseDouble(ValorTF.getText());

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
    } 
    @SuppressWarnings("unchecked")
      private void initComponents2() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NombreTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TipoIdentificacionTF = new javax.swing.JComboBox<>();
        IdentificacionTF = new javax.swing.JTextField();
        CelularTF = new javax.swing.JTextField();
        BotonResitrar = new javax.swing.JButton();
        BotonResitrar1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaDePresiosTA = new javax.swing.JTextArea();
        MaterialTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ValorTF = new javax.swing.JTextField();
        BotonResitrar2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DescripcionTA = new javax.swing.JTextArea();
        TipoComboBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        Contraseña = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        setForeground(java.awt.Color.white);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jPanel1.setFocusCycleRoot(true);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel1.setText("Registro");

        NombreTF.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel5.setText("Tipo Documento");

        TipoIdentificacionTF.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        TipoIdentificacionTF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cc" }));

        IdentificacionTF.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N

        CelularTF.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N

        BotonResitrar.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BotonResitrar.setText("Registrar");
        BotonResitrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonResitrarActionPerformed(evt);
            }
        });

        BotonResitrar1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BotonResitrar1.setText("Cancelar");
        BotonResitrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonResitrar1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel6.setText("Lista de presios");

        ListaDePresiosTA.setEditable(false);
        ListaDePresiosTA.setColumns(20);
        ListaDePresiosTA.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListaDePresiosTA.setRows(5);
        jScrollPane1.setViewportView(ListaDePresiosTA);

        MaterialTF.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel7.setText("Material");

        jLabel8.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel8.setText("Valor");

        ValorTF.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N

        BotonResitrar2.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BotonResitrar2.setText("Registrar a la lista");
        BotonResitrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonResitrar2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel9.setText("Descripcion");

        DescripcionTA.setColumns(20);
        DescripcionTA.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        DescripcionTA.setRows(5);
        jScrollPane2.setViewportView(DescripcionTA);

        TipoComboBox.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        TipoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Proveedor", "Cliente", "Usuario" }));
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

        jLabel10.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel10.setText("Tipo de persona ");


        Contraseña.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(TipoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(MaterialTF, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ValorTF, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotonResitrar2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(231, 231, 231)
                                .addComponent(BotonResitrar)
                                .addGap(38, 38, 38)
                                .addComponent(BotonResitrar1))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel9))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(TipoIdentificacionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2)
                                        .addComponent(jScrollPane1)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(210, 210, 210)
                                                .addComponent(CelularTF, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                             
                                                .addGap(324, 324, 324)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))))
                                        .addGap(62, 62, 62)
                                        .addComponent(IdentificacionTF, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(404, 404, 404)
                                      
                                        .addGap(18, 18, 18)
                                        .addComponent(NombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 23, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(TipoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
      
                    .addComponent(CelularTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
  
                    .addComponent(IdentificacionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TipoIdentificacionTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                  
                    .addComponent(Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(278, 278, 278))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotonResitrar2)
                            .addComponent(ValorTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MaterialTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotonResitrar)
                            .addComponent(BotonResitrar1))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NombreTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TipoIdentificacionTF = new javax.swing.JComboBox<>();
        Contraseña = new javax.swing.JPasswordField();
        IdentificacionTF = new javax.swing.JTextField();
        CelularTF = new javax.swing.JTextField();
        BotonResitrar = new javax.swing.JButton();
        BotonResitrar1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DescripcionTA = new javax.swing.JTextArea();
        TipoComboBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jButtonMostrarClave = new javax.swing.JButton();
        jLabelIcono = new javax.swing.JLabel();
        jLabelImagenRegistro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.white);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setAutoscrolls(true);
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setFocusTraversalPolicyProvider(true);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("REGISTRO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));

        NombreTF.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        NombreTF.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        NombreTF.setSelectionColor(new java.awt.Color(200, 244, 254));
        NombreTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombreTFKeyTyped(evt);
            }
        });
        jPanel1.add(NombreTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 300, 30));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Tipo de documento:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 242, -1, -1));

        TipoIdentificacionTF.setBackground(new java.awt.Color(218, 247, 254));
        TipoIdentificacionTF.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        TipoIdentificacionTF.setForeground(new java.awt.Color(51, 51, 51));
        TipoIdentificacionTF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cc" }));
        TipoIdentificacionTF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(TipoIdentificacionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 140, -1));

        Contraseña.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Contraseña.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        Contraseña.setSelectionColor(new java.awt.Color(200, 244, 254));
        jPanel1.add(Contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 300, 30));

        IdentificacionTF.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        IdentificacionTF.setForeground(new java.awt.Color(51, 51, 51));
        IdentificacionTF.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        IdentificacionTF.setSelectionColor(new java.awt.Color(200, 244, 254));
        IdentificacionTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                IdentificacionTFKeyTyped(evt);
            }
        });
        jPanel1.add(IdentificacionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 300, 30));

        CelularTF.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        CelularTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CelularTF.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        CelularTF.setSelectionColor(new java.awt.Color(200, 244, 254));
        CelularTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CelularTFKeyTyped(evt);
            }
        });
        jPanel1.add(CelularTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 300, 30));

        BotonResitrar.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        BotonResitrar.setText("Registrar");
        BotonResitrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonResitrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonResitrarActionPerformed(evt);
            }
        });
        jPanel1.add(BotonResitrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, 150, -1));

        BotonResitrar1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        BotonResitrar1.setText("Cancelar");
        BotonResitrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonResitrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonResitrar1ActionPerformed(evt);
            }
        });
        jPanel1.add(BotonResitrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 560, 150, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Descripción:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, -1, -1));

        DescripcionTA.setColumns(20);
        DescripcionTA.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        DescripcionTA.setLineWrap(true);
        DescripcionTA.setRows(5);
        DescripcionTA.setWrapStyleWord(true);
        DescripcionTA.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        DescripcionTA.setSelectionColor(new java.awt.Color(200, 244, 254));
        jScrollPane2.setViewportView(DescripcionTA);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, 300, 130));

        TipoComboBox.setBackground(new java.awt.Color(218, 247, 254));
        TipoComboBox.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        TipoComboBox.setForeground(new java.awt.Color(51, 51, 51));
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
        jPanel1.add(TipoComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 160, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Tipo de persona: ");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 362, 140, -1));

        jButtonMostrarClave.setBorderPainted(false);
        jButtonMostrarClave.setContentAreaFilled(false);
        jButtonMostrarClave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonMostrarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarClaveActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonMostrarClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 50, 30));
        jPanel1.add(jLabelIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 110, 60));
        jPanel1.add(jLabelImagenRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private boolean mostrar = false;
  
    private void jButtonMostrarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarClaveActionPerformed
  // Invierte el valor de mostrar
    mostrar = !mostrar;

    if (mostrar) {
        Contraseña.setEchoChar((char) 0); // Mostrar caracteres
           ImageIcon icon = new ImageIcon("Imagenes/FormLogin/NoVer.png");
jButtonMostrarClave.setIcon(icon);//le asigno otro icono

  icon = new ImageIcon("Imagenes/FormLogin/NoVer2.png");
jButtonMostrarClave.setRolloverIcon(icon);//le asigno el relovvericon

    } else {
        Contraseña.setEchoChar('\u25CF'); // Ocultar caracteres
         ImageIcon icon = new ImageIcon("Imagenes/FormLogin/Ver.png");
jButtonMostrarClave.setIcon(icon);//le asigno otro icono

  icon = new ImageIcon("Imagenes/FormLogin/Ver2.png");
jButtonMostrarClave.setRolloverIcon(icon);//le asigno el relovvericon
      
    }

    // Forzar el repintado del campo de texto
    Contraseña.repaint();
       
    }//GEN-LAST:event_jButtonMostrarClaveActionPerformed

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
    private javax.swing.JTextArea ListaDePresiosTA;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonResitrar;
    private javax.swing.JButton BotonResitrar1;
    private javax.swing.JTextField CelularTF;
    private javax.swing.JPasswordField Contraseña;
    private javax.swing.JTextArea DescripcionTA;
    private javax.swing.JTextField IdentificacionTF;
    private javax.swing.JTextField NombreTF;
    private javax.swing.JComboBox<String> TipoComboBox;
    private javax.swing.JComboBox<String> TipoIdentificacionTF;
    private javax.swing.JButton jButtonMostrarClave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelIcono;
    private javax.swing.JLabel jLabelImagenRegistro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
