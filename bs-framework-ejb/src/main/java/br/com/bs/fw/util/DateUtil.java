package br.com.bs.fw.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Calendar getCalendarOfDate(Date date){
		if(date != null){
			Calendar calendar = Calendar.getInstance();	
			calendar.setTime(date);
			
			return calendar;
		}
		return null;
	}
	
	public static Integer getYear(Date date){
		if(date != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			
			return calendar.get(Calendar.YEAR);
		}
		else{
			return null;
		}
	}
	
	public static Integer getMonth(Date date){
		if(date != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			
			return calendar.get(Calendar.MONTH);
		}
		else{
			return null;
		}
	}
	public static Integer getDay(Date date){
		if(date != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			
			return calendar.get(Calendar.DAY_OF_MONTH);
		}
		else{
			return null;
		}
	}
	/*
	 * TODO avan�ar e voltar ano e mes
	 * */

	public static Date toLowerDate(Date date){
		Calendar calendar = getCalendarOfDate(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        
        return calendar.getTime();       
	}
	
	public static Date toBiggerDate(Date date){
		Calendar calendar = getCalendarOfDate(date);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        
        return calendar.getTime();    
	}
	

	public static String formatedDateString(Date date , String expressionString) {
		if(date != null && expressionString != null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(expressionString);
			return simpleDateFormat.format(date);
		}
		return null;
	}

	public static String dateToString(Date date) {
		return formatedDateString(date, "dd/MM/yyyy");
	}
	

	public static String dateTimeToString(Date date) {
		return formatedDateString(date, "dd/MM/yyyy HH:mm");
	}	

	public static Integer getDaysOfDiference(Date dateOne, Date dateTwo) {
		Calendar calendarOne = getCalendarOfDate(toLowerDate(dateOne));
		Calendar calendarTwo = getCalendarOfDate(toLowerDate(dateTwo));
		
		long diferenca = 	calendarTwo.getTimeInMillis() - calendarOne.getTimeInMillis();
		
		Integer dias = (int) (diferenca / (1000 * 60 * 60 * 24));
		return dias;
	}
	
	
	public static Date advanceDateInDays(Date dateToChange, Integer days) {
		return changeDateInDays(dateToChange, days, false);
	}
	

	public static Date backDateInDays(Date dateToChange, Integer days) {		
		return changeDateInDays(dateToChange, days, true);
	}
	

	private static Date changeDateInDays(Date dateToChange, Integer days, Boolean isBack) {
		if(dateToChange != null){

			Calendar calendarToChange = getCalendarOfDate(dateToChange);
		
			if(isBack){
				calendarToChange.setTimeInMillis((calendarToChange.getTimeInMillis() - (days*(1000 * 60 * 60 * 24))));
			}
			else{
				calendarToChange.setTimeInMillis((calendarToChange.getTimeInMillis() + (days*(1000 * 60 * 60 * 24))));				
			}
			
			return calendarToChange.getTime();

		}
		return dateToChange;
	}
		
	
}
