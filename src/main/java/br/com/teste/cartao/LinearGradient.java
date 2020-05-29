package br.com.teste.cartao;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class LinearGradient extends JPanel {

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color color1 = Color.WHITE;
        Color color2 = Color.BLUE;
        int steps = 30;
        int rectWidth = 10;
        int rectHeight = 10;

        for (int i = 0; i < steps; i++) {
            float ratio = (float) i / (float) steps;
            int red = (int) (color2.getRed() * ratio + color1.getRed() * (1 - ratio));
            int green = (int) (color2.getGreen() * ratio + color1.getGreen() * (1 - ratio));
            int blue = (int) (color2.getBlue() * ratio + color1.getBlue() * (1 - ratio));
            Color stepColor = new Color(red, green, blue);
            Rectangle2D rect2D = new Rectangle2D.Float(rectWidth * i, 0, rectWidth, rectHeight);
            g2.setPaint(stepColor);
            g2.fill(rect2D);
        }
    }
}