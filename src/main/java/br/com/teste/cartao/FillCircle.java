package br.com.teste.cartao;
import java.lang.*;
import java.util.*;
import java.util.List;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class FillCircle extends Frame {

  public void paint(Graphics g) {

	  Graphics2D g2 = (Graphics2D) g;
      g2.setPaint(Color.RED);
      g2.setStroke(new BasicStroke(10.0f));
      g2.draw(new Ellipse2D.Double(50, 50, 250, 250));
  }

  public static void main(String args[])
  {
      FillCircle frame = new FillCircle();
      frame.addWindowListener(
      new WindowAdapter()
      {
         public void windowClosing(WindowEvent we)
         {
            System.exit(0);
         }
      }
      );

      frame.setSize(400, 400);
      frame.setVisible(true);
   }
}