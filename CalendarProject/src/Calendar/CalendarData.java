package Calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalendarData {

	private int year;
	private int month;
	private String day[];
	
	public int getYear(){
		return year;
	}
	public void setYear(int year){
		this.year=year;
	}
	
	/*
	 * Month value starts from 1 to 12.
	 */
	public int getMonth(){
		return month;
	}

	public void setMonth(int month){
		this.month=month;
	}
	
	public int getToyear(){
		Calendar c=Calendar.getInstance();
		return c.get(Calendar.YEAR); 
	}
	
	public int getTomonth(){
		Calendar c=Calendar.getInstance();
		return c.get(Calendar.MONTH)+1; 
	}
	
	public int getToday(){
		Calendar c1=Calendar.getInstance();

		Calendar c2=Calendar.getInstance();
		c2.set(year, month-1,1);
		
		return c1.get(Calendar.DAY_OF_MONTH)+c2.get(Calendar.DAY_OF_WEEK)-2; 
				
	}
	
	public String[] getDate(){
		String temp[]=new String[42];
		Calendar c=Calendar.getInstance();
		c.set(year, month-1,1);
		int week=c.get(Calendar.DAY_OF_WEEK)-1;
		int noOfDaysInMonth=c.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		int n=1;
		for(int i=week;i<week+noOfDaysInMonth;i++){
			temp[i]=String.valueOf(n++);
		}
		return temp;
	
	}
	
}
