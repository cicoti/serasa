package br.com.teste.cartao;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class MainClass2{
  public static void main(String[] args) {
    JFrame jf = new JFrame("Demo");
    Container cp = jf.getContentPane();
    MyCanvas tl = new MyCanvas();
    cp.add(tl);
    jf.setSize(300, 200);
    jf.setVisible(true);
  }
}

class MyCanvas extends JComponent {
  Shape shape;

  public MyCanvas() {
    shape = create();
  }

  protected Shape create() {
    float cm = 72 / 2.54f;
    return new Rectangle2D.Float(cm, cm, 2 * cm, cm);
  }


  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    Stroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
        new float[] { 3, 1 }, 0);
    g2.setStroke(stroke);

    
    g2.setPaint(Color.black);
    g2.draw(shape);
    
  }
}