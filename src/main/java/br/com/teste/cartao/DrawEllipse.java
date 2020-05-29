package br.com.teste.cartao;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class DrawEllipse extends JComponent {
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.RED);
        g2.setStroke(new BasicStroke(10.0f));
        g2.draw(new Ellipse2D.Double(50, 50, 200, 200));

        Graphics2D g3 = (Graphics2D) g;
        g3.setPaint(Color.RED);
        g3.setStroke(new BasicStroke(10.0f));
        g3.draw(new Ellipse2D.Double(50, 50, 250, 250));

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Draw Ellipse Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new DrawEllipse());
        frame.pack();
        frame.setSize(new Dimension(420, 400));
        frame.setVisible(true);
    }
}