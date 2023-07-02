/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases_FrontEnd;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class RightAlignedTableCellRenderer extends DefaultTableCellRenderer {

    public RightAlignedTableCellRenderer() {
        setHorizontalAlignment(SwingConstants.RIGHT);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (cell instanceof JLabel) {
            JLabel label = (JLabel) cell;
            String text = label.getText();

            // Recupera el ancho de la columna
            int columnWidth = table.getColumnModel().getColumn(column).getWidth();

            // Calcula la longitud máxima del texto en función del ancho de la columna
            int maxLength = columnWidth / 7; // Ajuste el divisor según la fuente de su celda

            if (text.length() > maxLength) {
                String ellipsisText = "..." + text.substring(text.length() - maxLength);
                label.setText(ellipsisText);
            }
        }

        return cell;
    }
}
