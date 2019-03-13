package br.com.quaemo.support;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public final class Util {

	private Util(){
		super();
	}

	private static final int ZERO = 0;
	
	public static boolean isEmpty(String v1){  
		return (v1 == null || v1.trim().equals(""))? true : false;
	}
	 
	public static boolean isEmpty(Long v1){  
		return (v1 == null || v1.longValue() == ZERO)? true : false;
	}
	 
	public static boolean isEmpty(Integer v1){  
		return (v1 == null || v1.intValue() == ZERO)? true : false;
	}
	 
	public static boolean isEmpty(Double v1){  
		return (v1 == null || v1.doubleValue() == ZERO)? true : false;
	}
	 
	public static boolean isEmpty(BigDecimal v1){  
		return (v1 == null || v1.doubleValue() == ZERO)? true : false;
	}
	 
	public static boolean isNotEmpty(String v1){  
		return (v1 != null && !v1.trim().equals(""))? true : false;
	}
	 
	public static boolean isNotEmpty(Long v1){  
		return (v1 != null && v1.longValue() > ZERO)? true : false;
	}
	 
	public static boolean isNotEmpty(Integer v1){  
		return (v1 != null && v1.intValue() > ZERO)? true : false; 
	}
	 
	public static boolean isNotEmpty(BigDecimal v1){  
		return (v1 != null && v1.doubleValue() > ZERO)? true : false; 
	}
 
	public static boolean isEmpty(Date v1){  
		return v1 == null;
	}
  
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object v){  
		if(v == null){
			return true;
		}else{
			if(v instanceof String){
				return isEmpty((String) v);
				
			}else if(v instanceof Integer){
				return isEmpty((Integer) v);
				
			}else if(v instanceof Long){
				return isEmpty((Long) v);  
				
			}else if(v instanceof Double){
				return isEmpty((Double) v);  
				
			}else if(v instanceof BigDecimal){
				return isEmpty((BigDecimal) v);  
				
			}else if(v instanceof List){
				return ((List)v).isEmpty(); 
			}
			return false;
		}
	}
	 
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Object v){  
		if(v != null){
			if(v instanceof String){
				return isNotEmpty((String) v);
				
			}else if(v instanceof Integer){
				return isNotEmpty((Integer) v);
				
			}else if(v instanceof BigDecimal){
				return isNotEmpty((BigDecimal) v);
				
			}else if(v instanceof Boolean){
				return true;
				
			}else if(v instanceof Long){
				return isNotEmpty((Long) v); 
				
			}else if(v instanceof Date){
				return true;  
				
			}else if(v instanceof List){
				return !((List)v).isEmpty(); 
			}
		}
		return false;
	}
	 
	public static boolean isAnyoneNull(Object... valores){ 
		if(valores == null){
			return true; 
		}
		for(Object v: valores){
			if(v == null){
				return true;
			}
		}
		return false;
	}
 
	public static boolean isAnyoneEmpty(Object... valores){ 
		if(valores == null){
			return true; 
		}
		for(Object v: valores){
			if(isEmpty(v)){
				return true;
			}
		}
		return false;
	}
	 
	public static boolean isAnyoneNotEmpty(Object... valores){ 
		if(valores == null){
			return true; 
		}
		for(Object v: valores){
			if(isNotEmpty(v)){
				return true;
			}
		}
		return false;
	}
	 
	public static boolean isAllNotNull(Object... valores){ 
		if(valores == null){
			return true; 
		}
		for(Object v: valores){
			if(v == null){
				return false;
			}
		}
		return true;
	}

	public static String getStringSql(String s){
		return isEmpty(s) ? "%" : s;
	}
	
	public static Integer getIntegerSql(Integer s){
		return isEmpty(s) ? ZERO : s;
	}
	 
	
}
