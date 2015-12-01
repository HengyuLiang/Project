package Calendar;

import java.util.Calendar;

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
