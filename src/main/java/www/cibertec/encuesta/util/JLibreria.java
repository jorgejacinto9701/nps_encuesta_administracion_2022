package www.cibertec.encuesta.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class JLibreria {

	private static DecimalFormatSymbols simbolos = new DecimalFormatSymbols();

	public static String toTextoConLinea(String valor) {
		if (valor == null || valor.isEmpty()) {
			return valor;
		} else {
			return valor.replace(' ', '_');
		}
	}
	
	public static String toDosDigitos(double num) {
		simbolos.setDecimalSeparator('.');
		DecimalFormat nf1 = new DecimalFormat("##.##", simbolos);
		return nf1.format(num);
	}
	

}
