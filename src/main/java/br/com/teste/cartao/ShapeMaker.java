package br.com.teste.cartao;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class ShapeMaker extends JFrame {
  public static void main(String[] args) {
    new ShapeMaker();
  }

  public ShapeMaker() {
    this.setSize(400, 400);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(new PaintSurface(), BorderLayout.CENTER);
    this.setVisible(true);
  }

  private class PaintSurface extends JComponent {
    ArrayList<Shape> shapes = new ArrayList<Shape>();

    Point startDrag, endDrag;

    Shape found = null;

    public PaintSurface() {

    	 Shape s = null;

			/*
			 * Shape s = new Rectangle2D.Float(10, 10, 60, 80); shapes.add(s);
			 */

			/*
			 * s = new RoundRectangle2D.Float(110, 10, 80, 80, 10, 10); shapes.add(s);
			 */

			/*
			 * s = new Ellipse2D.Float(10, 110, 80, 80); shapes.add(s);
			 */

      s = new Arc2D.Float(10, 210, 80, 80, 90, 90, Arc2D.OPEN);
      shapes.add(s);

      s = new Arc2D.Float(110, 210, 80, 80, 0, 180, Arc2D.CHORD);

      shapes.add(s);

      int[] polygonXs = { -20, 0, +20, 0};
		int[] polygonYs = { 20, 10, 20, -20};
		s = new Polygon(polygonXs, polygonYs, polygonXs.length);
		shapes.add(s);

			/*
			 * s = new Arc2D.Float(210, 210, 80, 80, 45, 90, Arc2D.PIE); shapes.add(s);
			 */
    }

    public void paint(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,

      RenderingHints.VALUE_ANTIALIAS_ON);

      g2.setPaint(Color.LIGHT_GRAY);
      for (int i = 0; i < getSize().width; i += 10)
        g2.draw(new Line2D.Float(i, 0, i, getSize().height));
      for (int i = 0; i < getSize().height; i += 10)
        g2.draw(new Line2D.Float(0, i, getSize().width, i));

      g2.setColor(Color.BLACK);
      g2.setStroke(new BasicStroke(2));
      for (Shape s : shapes) {
    	g2.translate(50, 50);
    	g2.setColor(Color.BLACK);
    	g2.fill(s);
        g2.draw(s);
      }
    }
  }
}
