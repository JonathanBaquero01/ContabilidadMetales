/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases_FrontEnd;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.AbstractBorder;


//ESTA CLASE REDONDEA LOS BORDES DE UN JTEXT
public class RoundBorder extends AbstractBorder {
   private Color borderColor;
    private int arcRadius;
    private int borderThickness;

    public RoundBorder(Color borderColor, int arcRadius, int borderThickness) {
        this.borderColor = borderColor;
        this.arcRadius = arcRadius;
        this.borderThickness = borderThickness;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Set rendering hints to get smoother edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Set the border color and thickness
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));

        // Calculate the border offsets
        int xOffset = borderThickness / 2;
        int yOffset = borderThickness / 2;
        int adjustedWidth = width - borderThickness;
        int adjustedHeight = height - borderThickness;

        g2.drawRoundRect(x + xOffset, y + yOffset, adjustedWidth, adjustedHeight, arcRadius, arcRadius);
        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        int totalThickness = borderThickness * 2; // Account for both sides of the border
        return new Insets(totalThickness, totalThickness, totalThickness, totalThickness);
    }
}