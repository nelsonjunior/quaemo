package br.com.quaemo.api.support;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ApiDateUtils {
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
	
	public static final long N_24 = 24l;
	public static final long N_60 = 60l;
	public static final long N_1000 = 1000l;

	public static long getDiferencaMinutos(String data, String hora1, String hora2) { 
		Calendar dataInicial = getCalendar(convertStringDate(data+" "+hora1, "dd/MM/yyyy HH:mm"));
		Calendar dataFinal = getCalendar(convertStringDate(data+" "+hora2, "dd/MM/yyyy HH:mm"));
		return minutos(dataFinal.getTimeInMillis() - dataInicial.getTimeInMillis());
	}
	
	public static long getDiferencaMinutos(Date data1, Date data2) { 
		return minutos(data1.getTime() - data2.getTime());
	}
	
	public static long getDiferencaHoras(String data, String hora1, String hora2) { 
		Calendar dataInicial = getCalendar(convertStringDate(data+" "+hora1, "dd/MM/yyyy HH:mm"));
		Calendar dataFinal = getCalendar(convertStringDate(data+" "+hora2, "dd/MM/yyyy HH:mm"));
		return horas(dataFinal.getTimeInMillis() - dataInicial.getTimeInMillis());
	}
	
	public static long getDiferencaHoras(Date data1, Date data2) { 
		return horas(data1.getTime() - data2.getTime());
	}
	
	public static long getDiferencaDias(String data, String hora1, String hora2) { 
		Calendar dataInicial = getCalendar(convertStringDate(data+" "+hora1, "dd/MM/yyyy HH:mm"));
		Calendar dataFinal = getCalendar(convertStringDate(data+" "+hora2, "dd/MM/yyyy HH:mm"));
		return dias(dataFinal.getTimeInMillis() - dataInicial.getTimeInMillis());
	}
	
	public static long getDiferencaDias(Date data1, Date data2) { 
		return dias(data1.getTime() - data2.getTime());
	}
	

	public static Calendar getCalendar(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar;
	}
	
	public static long segundos(long time) {
		return time / N_1000;
	}
	
	public static long minutos(long time) {
		return time / (N_60 * N_1000);
	}
	
	public static long horas(long time) {
		return time / (N_60 * N_60 * N_1000);
	} 
	
	public static long dias(long time) {
		return horas(time) / N_24;
	} 
	
	
}
