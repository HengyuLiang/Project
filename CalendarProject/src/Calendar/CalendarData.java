package Calendar;

import java.util.Calendar;

public class CalendarData {

	private int year;
	private int month;
	
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
		int day = 0;
		if(month==1||month==3||month==5||month==7|| //set 31 days to the month 1,3,5,7,8,10,12
		   month==8||month==10||month==12)
			day=31;
		if(month==4||month==6||month==9||month==11)  //set 30 days to the month 4,6,9,11
			day=30;
		if(month==2)  // if it is a leap year, then set 29days to February
			if(((year%4==0)&&(year%100!=0))||(year%400==0))
					day=29;
			else      //if  it is not a leap year, then set 28 days to february
				day=28;
		for(int i=week,n=1;i<week+day;i++){
			temp[i]=String.valueOf(n);
			n++;
		}
		return temp;
	}
	
}
