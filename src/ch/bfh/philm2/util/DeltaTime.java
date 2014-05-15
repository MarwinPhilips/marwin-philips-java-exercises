package ch.bfh.philm2.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import ch.bfh.philm2.helpers.H;

public class DeltaTime {
	private GregorianCalendar date1, date2;
	private long delta;
	private LocalDate largerDate, smallerDate;
	/**
	 * Constructs a DeltaTime object with date1=date and date2=CurrentTime (Year, Month, Day)
	 * @param date
	 */
	public DeltaTime(int year1,int month1,int day1){
		this.date1=new GregorianCalendar(year1,month1,day1);
		GregorianCalendar now = (GregorianCalendar) Calendar.getInstance();
		date2=new GregorianCalendar(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));
		setDelta();
	}
	/**
	 * Constructs a DeltaTime object with date1=date and date2=date2
	 * @param date
	 */
	public DeltaTime(int year1,int month1,int day1,int year2,int month2,int day2){
		this.date1=new GregorianCalendar(year1,month1,day1);
		this.date2=new GregorianCalendar(year2,month2,day2);
		setDelta();
	}
	public DeltaTime(LocalDate firstDate, LocalDate secondDate){
		date1 = new GregorianCalendar(secondDate.getYear(),secondDate.getMonthValue(),secondDate.getDayOfMonth());
		date2 = new GregorianCalendar(firstDate.getYear(),firstDate.getMonthValue(),firstDate.getDayOfMonth());
		setDelta();
	}
	private void setDelta(){
		long date1Mili = date1.getTimeInMillis();
		long date2Mili = date2.getTimeInMillis();
		if(date1Mili>date2Mili){
			delta= date1Mili-date2Mili;
			smallerDate = date2.toZonedDateTime().toLocalDate();
			largerDate = date1.toZonedDateTime().toLocalDate();
		}else{
			delta = date2Mili-date1Mili;
			smallerDate = date1.toZonedDateTime().toLocalDate();
			largerDate = date2.toZonedDateTime().toLocalDate();
		}
	}
	public long getDeltaTimeInDays(){
		H.pL("DeltaTimeInDays: "+String.valueOf(TimeUnit.MILLISECONDS.toDays(delta)));
		return TimeUnit.MILLISECONDS.toDays(delta);
	}
	
	public String getDeltaTimeInYearsMonthsDays()
	{		
		Period p = smallerDate.until(largerDate);	
		String returner = "Years: "+String.valueOf(p.getYears())+ " Months: " + String.valueOf(p.getMonths()) + " Days: "+ String.valueOf(p.getDays());
		return returner;
	}
}
