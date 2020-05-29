package br.com.teste.cartao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

BufferedImage bufferedImage;
Dimension size;

public ImagePanel(BufferedImage bufferedImage) {
    this.bufferedImage = bufferedImage;
    this.size = new Dimension();
    size.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
    this.setBackground(Color.WHITE);
    this.setBorder(BorderFactory.createLineBorder(Color.RED, 10));
}

@Override
protected void paintComponent(Graphics g) {
    // Center image in this component.
    int x = (getWidth() - size.width)/2;
    int y = (getHeight() - size.height)/2;
    g.drawImage(bufferedImage, x, y, this);
}

@Override
public Dimension getPreferredSize() { return size; }

}