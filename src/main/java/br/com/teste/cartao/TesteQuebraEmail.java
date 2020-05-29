package br.com.teste.cartao;

public class TesteQuebraEmail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String email = "silvio.cicoti@comprealugueagora.com.br";
		String emailSegundaLinha = (email.substring(email.indexOf("@")));
		email = email.substring(0,email.indexOf("@"));
		System.out.println(email);
		System.out.println(emailSegundaLinha);
	}

}
