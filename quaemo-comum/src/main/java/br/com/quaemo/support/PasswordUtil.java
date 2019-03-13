package br.com.quaemo.support;

import java.security.InvalidParameterException;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import br.com.quaemo.exceptions.DadoInconsistenteException;
 


public class PasswordUtil {
	
	/**
	 * Verifica se a senha é complexa seguindo requisitos do Active Directory
	 * - Is at least eight characters long
	 * - Passwords must not contain the user's entire samAccountName (Account Name) value or entire displayName (Full Name) value. Both checks are not case sensitive.
	 * - Passwords must contain characters from three of the following five categories:
	 * . Uppercase characters of European languages (A through Z, with diacritic marks, Greek and Cyrillic characters)
	 * . Lowercase characters of European languages (a through z, sharp-s, with diacritic marks, Greek and Cyrillic characters)
	 * . Base 10 digits (0 through 9)
	 * . Nonalphanumeric characters: ~!@#$%^&*_-+=`|\(){}[]:;"'<>,.?/
	 * @param password Senha do usu�rio
	 * @param username Login do usu�rio
	 * @param fullname Nome completo do usu�rio
	 * @return true se a senha for complexa e false caso n�o atenda qualquer um dos requisitos do Active Directory
	 */
	public static boolean checkPasswordComplexity(String password,String username, String fullname){
		if (StringUtils.isEmpty(username)||StringUtils.isEmpty(fullname)){
			throw new DadoInconsistenteException("Todos os parâmetros precisam ser preenchidos.");
		}
		return (checkPasswordComplexity(password)&&!checkForConsecutiveCharacters(password, username, fullname));
	}

	/**
	 * Verifica se a senha � complexa seguindo o requisito:
	 * - Is at least eight characters long
	 * - Passwords must contain characters from three of the following five categories:
	 * . Uppercase characters of European languages (A through Z, with diacritic marks, Greek and Cyrillic characters)
	 * . Lowercase characters of European languages (a through z, sharp-s, with diacritic marks, Greek and Cyrillic characters)
	 * . Base 10 digits (0 through 9)
	 * . Nonalphanumeric characters: ~!@#$%^&*_-+=`|\(){}[]:;"'<>,.?/
	 * @param password Senha do usu�rio
	 * @return true se a senha for complexa e false caso n�o atenda o requisito acima
	 */
	public static boolean checkPasswordComplexity(String password){
		if (StringUtils.isEmpty(password)){
			throw new InvalidParameterException("Todos os parâmetros precisam ser preenchidos.");
		}
		String pattern = "(((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]))|"+
				          "((?=.*\\d)(?=.*\\W)(?=.*[A-Z]))|"+
				          "((?=.*\\W)(?=.*[a-z])(?=.*[A-Z]))|"+
				          "((?=.*\\d)(?=.*\\W)(?=.*[a-z])))"+
				         ".{8,}";		
		return password.matches(pattern);	   
	}
	
	private static boolean checkForConsecutiveCharacters(String password, String username, String fullname) {
		String delimiters[] = {".",",","-","_"," ","#","/","�"};
		String splitToken[] = null;
		String singleDelimeter = null;
		String singleToken = null;
		String pwd = password.toUpperCase();	
		
		if (username.length()>2){
			String sAMAccountName = username.toUpperCase();
			if (sAMAccountName.length() > 20 ) {
				sAMAccountName = sAMAccountName.substring(0,20);
				if ( (sAMAccountName.charAt(19) == '.') || (sAMAccountName.charAt(19) == '@') ) {
					sAMAccountName = sAMAccountName.substring(0,19) + "_";
				}
			}
			if (pwd.indexOf(sAMAccountName)>=0){		
				return true;
			}		
		}
		
		String displayName = fullname.toUpperCase();
		for (int i = 0;i < delimiters.length ; i++) {
			singleDelimeter = delimiters[i];
			splitToken = displayName.split(singleDelimeter);
			if (splitToken.length==0) splitToken = new String[]{displayName};
			for (int j = 0; j < splitToken.length; j++) {
				singleToken = splitToken[j];
				if (singleToken.length() > 2){
					if (pwd.indexOf(singleToken)>=0){
						return true;
					}
				}
			}				
		}
		return false;
	}
	
	/**
	 * Cria uma senha complexa randomica seguindo o seguinte requisito:
	 * - Is at least eight characters long
	 * - Passwords must contain characters from three of the following five categories:
	 * . Uppercase characters of European languages (A through Z, with diacritic marks, Greek and Cyrillic characters)
	 * . Lowercase characters of European languages (a through z, sharp-s, with diacritic marks, Greek and Cyrillic characters)
	 * . Base 10 digits (0 through 9)
	 * . Nonalphanumeric characters: ~!@#$%^&*_-+=`|\(){}[]:;"'<>,.?/	 
	 * @param tamanho numero de caracteres que a senha dever� possuir
	 * @return senha complexa randomica
	 */
	public static String getRandomPassword(int tamanho) {
		String string = null;
		int count = 0;
		do {
			string = RandomStringUtils.randomAlphanumeric(tamanho);
			count++;
		} while (!checkPasswordComplexity(string) && count < 100);
		if (!checkPasswordComplexity(string)){
			String number = Long.toString(GregorianCalendar.getInstance().getTimeInMillis());
			return String.format("K%s%sw", string.substring(0,4),number.substring(number.length()-2, number.length()));
		}  
		return string;
    }
	
	public static void main(String[] args) {		
		String passwd[] = {"maria",
				"M@r1a",
				"mariazinha",
				"m@riazinha",
				"mar1azinha",
				"Mariazinha",
				"mar1aziNha",
				"m@r1azinha",
				"M@riazinha",
				"M@r1azinha",
				"mariaz1nH@",
				"mario@z1nha",
				"m@rio@z1nha",
				"#aRiazinha",
				"#ar1azinha"};
		for (int i = 0; i < passwd.length; i++) {
			String pw = passwd[i];
			System.out.println(pw+"="+(checkPasswordComplexity(pw,"mbueno@testando.com.br", "Marvel Mari Bueno")));	
		}
		String senhaRadom = getRandomPassword(8);
		System.out.println(senhaRadom+"="+(checkPasswordComplexity(senhaRadom)));
	}
}
