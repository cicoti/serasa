package br.com.teste.cartao;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

/** A demonstration of anti-aliasing */
public class AntiAlias extends JPanel{
  static final int WIDTH = 650, HEIGHT = 350; // Size of our example

  public String getName() {
    return "AntiAliasing";
  }

  public int getWidth() {
    return WIDTH;
  }

  public int getHeight() {
    return HEIGHT;
  }

  /** Draw the example */
  public void paint(Graphics g1) {
    Graphics2D g = (Graphics2D) g1;
    BufferedImage image = // Create an off-screen image
    new BufferedImage(65, 35, BufferedImage.TYPE_INT_RGB);
    Graphics2D ig = image.createGraphics(); // Get its Graphics for drawing

    // Set the background to a gradient fill. The varying color of
    // the background helps to demonstrate the anti-aliasing effect
    ig.setPaint(new GradientPaint(0, 0, Color.black, 65, 35, Color.white));
    ig.fillRect(0, 0, 65, 35);

    // Set drawing attributes for the foreground.
    // Most importantly, turn on anti-aliasing.
    ig.setStroke(new BasicStroke(2.0f)); // 2-pixel lines
    ig.setFont(new Font("Serif", Font.BOLD, 18)); // 18-point font
    ig.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
        RenderingHints.VALUE_ANTIALIAS_ON);

    // Now draw pure blue text and a pure red oval
    ig.setColor(Color.blue);
    ig.drawString("Java", 9, 22);
    ig.setColor(Color.red);
    ig.drawOval(1, 1, 62, 32);

    // Finally, scale the image by a factor of 10 and display it
    // in the window. This will allow us to see the anti-aliased pixels
    g.drawImage(image, AffineTransform.getScaleInstance(10, 10), this);

    // Draw the image one more time at its original size, for comparison
    g.drawImage(image, 0, 0, this);
  }

  public static void main(String[] a) {
    JFrame f = new JFrame();
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    f.setContentPane(new AntiAlias());
    f.setSize(400,400);
    f.setVisible(true);
  }

}
