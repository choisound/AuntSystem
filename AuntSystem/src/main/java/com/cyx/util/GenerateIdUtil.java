package com.cyx.util;

import java.util.Calendar;
import java.util.UUID;

public class GenerateIdUtil {
	public static String generateId(String idtype){
		String rid=idtype+"_";
		Calendar Cld = Calendar.getInstance();
		int YY = Cld.get(Calendar.YEAR) ;
		rid+=YY;
		int MM = Cld.get(Calendar.MONTH)+1;
		rid+=MM;
		int DD = Cld.get(Calendar.DATE);
		rid+=DD;
		int HH = Cld.get(Calendar.HOUR_OF_DAY);
		rid+=HH;
		int mm = Cld.get(Calendar.MINUTE);
		rid+=mm;
		int SS = Cld.get(Calendar.SECOND);
		rid+=SS;
		int MI = Cld.get(Calendar.MILLISECOND);
		rid+=MI;
		rid+=String.valueOf((int)(Math.random()*10000));
		return rid;
	}
}
