package br.com.teste.cartao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GirarFlecha {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		  BufferedImage bufferImagemSerasa = ImageIO.read(new File("d:\\silvio\\serasa_score_imagem_final.png"));
		  BufferedImage bufferImagemFlecha = ImageIO.read(new File("d:\\silvio\\flecha_peq.gif"));

		  Graphics2D graphics = bufferImagemSerasa.createGraphics();
	      graphics.drawImage(bufferImagemFlecha, 20, 100, null); // H x V
	      graphics.dispose();
	      ImageIO.write(bufferImagemSerasa, "PNG", new File("d:\\silvio\\imagemSobreposta.png"));

	}

}
