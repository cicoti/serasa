package br.com.teste.cartao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

public enum Turno {

	EMAIL_IMAGEM("iVBORw0KGgoAAAANSUhEUgAAACAAAAAZCAYAAABQDyyRAAAAAXNSR0IArs4c6QAABB9JREFUSA21Vl1oXFUQnrk/+3N3TdPQYrVFa4Ro/WsFQWsjjS9Ka2nFJgu+CP5bRaKCSAOCCCoIvvgLxfriS3M3u0EiatJCwRR9UKk+CEVD6UPFtKl/zd6z3b27Z/zONje9m4214u55uGd+zsw3Z87M7DLF1pUT4v1VDrpiovaSyYy8uJPmXmHWkWM2RLJ4rq9eq+0T4n4SsSNlR3bmEpPsX5/NvDSznSuOufUppT4D2LVE0hHMJqciWaAMn5gPDNjzzqlzaiuAAY7FvN9i+qZBd+KjiYXpCRG5FeiPYH/BsYiuiR4kTd7e0hDPdQI78un6yhGSd8B3dY1Tj0WIKlJW3HBtRHdu502Lvl1iJODC0tXw88RY5ZYLkvZSbl49q0U/HPfaFABqYU1dh186fhl10d7ljAava63fBkYTZhODIsTz0AohPWmPqd3tCGHgsDj2aPARHO9d8Hc27rcpAMu2n0IrhJgFSaqL7/rBnvjh/0qbFj8yp8Zx6/NpZ55htt+K+2kKIJVIFnHgPmSiZFKlRd538sGrcYNLpbt86TldVofQajuMDTN/iy67k0X/FvfRFIBR1HKpgxAOoDdOG160vGzng31D/qVPyLRfvkpJcATgm40PgE+tJO/uUq61xVsCMAZhLvOdQ84WWJ40PGl5fJxUYZ0v6QZ/kU+iWLmpSvorDJoNC8cObOz1dszlTFZb17IBmGOY1/VGLSzY4Da7ZklNrfhUVra6OS9x8uW76rVwGnaL8wS3T/cex7T5h7VsAJdNyKqa1L+AzWpjx5Z1zOwIoj9QwXS6oNYZPr7sseB+0XoKXdSNzGHi0k9GbwIvkjKTb9nVEoBJc1mpCaSwz1gw8RvXSXojiFHDi9CN1Zo+6uaDp5HuDe6Yuh018gHVqQBtChahLdZD3anMbbj998YGRnswB0Ya9JKPE+clJAtpPoCevaMhZ+vjWs4b+REMbvIgQGcRwDDYVVrLe6TDuDmw8VNryWB1MD15BhqvEGyv1PlrGF8Nn6+5+dLJpY/RlIFyqN4E0E7jlZkObupNPxoh4DZSy2Wfw/C+F8qZSB7t0BcTCb6hNpidjGRqd+ZX23G2IXu/G5nW/KFmuj7Sm53d0eAZTfJuk5D5aHfS23pmF8/H5RFtWvITp9pHYf1mzfpswuYfDFikX7o7BbVFanKo8URMFdRJ0pzxvMzqZQLgEymWzUEuO7vU0f/h7bx6AO2cRxCLWTcBLDILzv+wbWdbu8GN7/qQV7SYh/F81fhFHFQ53gc1jyU2j7g6/NP1S2vih9pFS1KKlQoPAAk/dBxebtM8p321tkryMyr1X6dcuwIxfkyRo6jvsco57xebrMcgUe0EuJgvgB9zyH6yEUh0ED17RUVzv0XSE8navmMYiG0fX59OTpu/5Mb/3wg8qeBGbWiNAAAAAElFTkSuQmCC");

    private BufferedImage descricao;

    Turno(String imagem) {
    	 try {

    		 byte[] imagemPNG =  org.apache.commons.codec.binary.Base64.decodeBase64(imagem.getBytes());

 	    	ByteArrayInputStream bis = new ByteArrayInputStream(imagemPNG);
 	    	this.descricao = (ImageIO.read(bis));
 	        bis.close();

 	    } catch (Exception e) {
 	        e.printStackTrace();
 	    }
    }

    public BufferedImage getDescricao() {
        return descricao;
    }
}