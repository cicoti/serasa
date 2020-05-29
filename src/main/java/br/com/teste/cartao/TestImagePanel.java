package br.com.teste.cartao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class TestImagePanel{
    public static void main(String[] args){

    BufferedImage image = null;
    ImagePanel imagePanel = null;

    try{
        image = ImageIO.read(new File("d:\\silvio\\fotoAgente.png"));
        imagePanel = new ImagePanel(image);
    }catch(IOException  e){
         System.err.println("Trying to read in image "+e);
    }

    JFrame frame = new JFrame("Example");
    frame.add(imagePanel);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    }
}