package ch.bfh.philm2.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ch.bfh.philm2.helpers.H;

public class DueDateCalculator {
	private int year,month,day;
	private Calendar calculatedDate;
	public DueDateCalculator(int year, int month, int day){
		this.year=year;
		this.month=month;
		this.day=day;
		H.pL("Menstrual Date: "+day+"."+month+"."+year);
		CalculateDueDate();
		CalculateDueDateNaegele();
		H.pL("\n");
	}
	private void CalculateDueDate(){
		calculatedDate = new GregorianCalendar(year,month,day);
		calculatedDate.add(Calendar.DATE, 7*40);
		
		System.out.println("Standard Rule: "+printMe());
	}
	private String printMe(){
		int day=calculatedDate.get(Calendar.DAY_OF_MONTH);
		int month = calculatedDate.get(Calendar.MONTH);
		int year = calculatedDate.get(Calendar.YEAR);
		return day+"." + month+"." + year;
	}
	private void CalculateDueDateNaegele(){
		calculatedDate = new GregorianCalendar(year,month,day);
		calculatedDate.add(Calendar.DATE, 7);
		calculatedDate.add(Calendar.MONTH, -3);
		calculatedDate.add(Calendar.YEAR, 1);
		System.out.println("Naegele: "+printMe());
	}
}
