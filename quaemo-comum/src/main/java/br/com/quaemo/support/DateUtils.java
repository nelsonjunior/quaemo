package br.com.quaemo.support;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	static String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto",
			"Setembro", "Outubro", "Novembro", "Dezembro" };

	public static String formataDataTimestamp(Timestamp data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(new Date(data.getTime()));
	}

	public static String convertDateToString(Date data, String pattern) {
		if (data != null) {
			return new SimpleDateFormat(pattern).format(data);
		}
		return "";
	}

	public static String convertDateToString(Date data) {
		return convertDateToString(data, "dd/MM/yyyy");
	}

	public static Date convertStringDate(String data) {
		if ((data == null) || (data.equals(""))) {
			return null;
		}
		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			date = formatter.parse(data);
		} catch (ParseException e) {
			try {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				date = formatter.parse(data);
			} catch (ParseException e2) {
				try {
					DateFormat formatter = new SimpleDateFormat("MM/yyyy");
					date = formatter.parse(data);
				} catch (ParseException ex) {
					try {
						DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

						date = formatter.parse(data);
					} catch (ParseException exx) {
						System.out.println("Formato de data inválido.");
					}
				}
			}
		}
		return date;
	}

	public static Date convertStringDate(String data, String formato) {
		if ((data == null) || (data.equals("")) || (formato == null) ||

				(formato.equals(""))) {
			return null;
		}
		try {
			return new SimpleDateFormat(formato).parse(data);
		} catch (ParseException localParseException) {
		}
		return null;
	}

	public static Timestamp transfomarStringEmTimestamp(String data) {
		try {
			return new Timestamp(new SimpleDateFormat("dd/MM/yyyy").parse(data).getTime());
		} catch (ParseException e) {
		}
		return null;
	}

	public static String getAnoDateToString(Date data) {
		return convertDateToString(data, "yyyy");
	}

	public static Long getAnoDateToLong(Date data) {
		return Long.valueOf(Long.parseLong(convertDateToString(data, "yyyy")));
	}
}
