package br.com.teste.cartao.serasa.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {

	public String situacaoCPF(String codigo) {

		String situacao = "não encontrada";

		switch (codigo) {
	        case "0": {
	            situacao = "inapta";
	            break;
	        }
	        case "2": {
	        	situacao = "ativa";
	            break;
	        }
	        case "4": {
	        	situacao = "nula";
	            break;
	        }
	        case "6": {
	        	situacao = "suspensa";
	            break;
	        }
	        case "7": {
	            situacao = "baixada";
	            break;
	        }
	        case "9": {
	            situacao = "cancelada";
	            break;
	        }
		}

		return situacao;

	}

	public String situacaoCNPJ(String codigo) {

		String situacao = "não encontrada";

		switch (codigo) {
	        case "2": {
	        	situacao = "regular";
	            break;
	        }
	        case "3": {
	        	situacao = "pendente de regularização";
	            break;
	        }
	        case "4": {
	        	situacao = "nula";
	            break;
	        }
	        case "6": {
	            situacao = "suspensa";
	            break;
	        }
	        case "7": {
	            situacao = "titular falecido";
	            break;
	        }
	        case "8": {
	            situacao = "cancelada";
	            break;
	        }
		}

		return situacao;

	}

	public String formataCPF(String cpf) {

		cpf = cpf.substring(4);

		return cpf.replaceAll("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})","$1\\.$2\\.$3-$4");

	}

	public String formataCNPJ(String cnpj) {

		cnpj = cnpj.substring(1);

		 return cnpj.replaceAll("([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})","$1\\.$2\\.$3/$4-$5");
	}

	public String formataTexto(String texto) {

		try {
			texto = texto.toUpperCase().trim();
		}catch(Exception e) {
			texto = "-";
		}

		return texto;
	}

	public String removeZerosEsquerda(String valor) {

		try {

			valor = Integer.valueOf(valor).toString();

		}catch(Exception e) {
			valor = "NADA CONSTA";
		}

		return valor;

	}

	public String formataDataPadraoSerasa(String data) throws ParseException {

		try {
			DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat df2 = new SimpleDateFormat("ddMMyyyy");
			Date d = df2.parse(data);
			data = df1.format(d);
		}catch(Exception e) {
			data = "-";
		}

		return data;

	}

	public String formataDataPadrao(String data) throws ParseException {

		try {
			DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat df2 = new SimpleDateFormat("yyyyMMdd");
			Date d = df2.parse(data);
			data = df1.format(d);
		}catch(Exception e) {
			data = "-";
		}

		return data;

	}

	public String formataDataResumo(String data) throws ParseException {

		try {
			DateFormat df1 = new SimpleDateFormat("MMM/yyyy");
			DateFormat df2 = new SimpleDateFormat("yyyyMMdd");
			Date d = df2.parse(data);
	        data = df1.format(d);
		}catch(Exception e) {
			data = "-";
		}

		return data;

	}

	public String retornaTraco(String valor) {

		if(valor!=null) {
			valor = valor.trim();
		}

		if(valor==null || valor.isEmpty() || valor.length()<=0) {
			return "-";
		}

		return valor;
	}

	public String retornaDataHoraCabecalho() {

		Date data =  new Date();
		Locale local = new Locale("pt","BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'às' HH:mm:ss" ,local);

		return formato.format(data);

	}

	public String formatarValorMonetario(String valor) {

		try {
			Double d = Double.parseDouble(valor)/100;
			valor = NumberFormat.getCurrencyInstance().format(d).replace("R$ ","");
		}catch(Exception e) {
			valor = "-";
		}

		return valor;
	}

	public static void main(String args[]) throws ParseException {

		System.out.println(new Util().retornaDataHoraCabecalho());
		System.out.println(new Util().situacaoCPF("2"));
		String cnpf = "000002220000154";
		System.out.println(new Util().formataCNPJ(cnpf));
		String cpf = "000000000000272";
		System.out.println(new Util().formataCPF(cpf));
		System.out.println(new Util().formataDataPadraoSerasa("31031966"));
		System.out.println(new Util().formataDataPadrao("20181203"));
		System.out.println(new Util().formataDataResumo("20181203"));
		System.out.println(new Util().formatarValorMonetario("000000000155601"));
		System.out.println(new Util().formatarValorMonetario("100000000155601"));


	}
}
