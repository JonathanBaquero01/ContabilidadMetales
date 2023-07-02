/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases_FrontEnd;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author Admin
 */
public class CustomComboBoxRenderer extends BasicComboBoxRenderer {
  //  ESTA CLASE PERSONALIZA LOS COLORES DEL JCOMBOBX
   private static final Color SELECTED_BACKGROUND_COLOR = new Color(182, 238, 252); // Color  personalizado DE FONFO
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (isSelected) {
                setBackground(SELECTED_BACKGROUND_COLOR);
                setForeground(new Color(51, 51, 51)); // Color de texto cuando el mouse pasa sobre el
            } else {
                setBackground(new Color(255,255,255));
                setForeground(new Color(51, 51, 51)); // Color de texto DE LOS ELEMENTOS
            }
//234 250 254
            return this;
        }
    }
