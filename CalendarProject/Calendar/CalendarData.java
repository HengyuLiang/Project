package Calendar;

import com.google.gson.Gson;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class CalendarData {

	private int year;
	private int month;

	private String calendarEventsFileName="default-calendar-data.json";
 	public static final SimpleDateFormat DATE_FORMATER =  new SimpleDateFormat("MM/dd/yyyy");
	public static final SimpleDateFormat DEFAULT_FORMATTER =  new SimpleDateFormat("yyyy-MM-dd");

	public int getYear(){
		return year;
	}
	public void setYear(int year){
		this.year=year;
	}
	private CalendarDetails calendarDetails=new CalendarDetails();
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


		return c1.get(Calendar.DAY_OF_MONTH);
				
	}
	
	public String[] getDate(){
		String temp[]=new String[42];
		Calendar c=Calendar.getInstance();
		c.set(year, month-1,1);
		int week=c.get(Calendar.DAY_OF_WEEK)-1;

		int noOfDaysInMonth=c.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		List<Integer> eventDays=new ArrayList();
		List<Integer> reservatoinDays=new ArrayList();
		List<Integer> holidays=new ArrayList();
		if(!calendarDetails.events.isEmpty()) {
			for (Event event : calendarDetails.events.values()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(event.getEventDate());
				if (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) + 1 == month) {
					eventDays.add(cal.get(Calendar.DAY_OF_MONTH));
				}
			}
		}
		if(!calendarDetails.reservations.isEmpty()) {
			for (Reservation event : calendarDetails.reservations.values()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(event.getReservationDate());
				if (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) + 1 == month) {
					reservatoinDays.add(cal.get(Calendar.DAY_OF_MONTH));
				}
			}
		}
		if(!calendarDetails.holidays.isEmpty()) {
			for (Holiday event : calendarDetails.holidays.values()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(event.getHolidayDate());
				if (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) + 1 == month) {
					holidays.add(cal.get(Calendar.DAY_OF_MONTH));
				}
			}
		}
		int n=1;
		for(int i=week;i<week+noOfDaysInMonth;i++){
			temp[i]=String.valueOf(n);
			if(eventDays.contains(n)){
				temp[i]+="&";
			}
			if(reservatoinDays.contains(n)){
				temp[i]+="#";
			}
			if(holidays.contains(n)){
				temp[i]+="h";
			}
			n++;
		}
		
		return temp;
	
	}

	public void addEventAndSave(Event event){
		calendarDetails.addEvent(event);
		saveUpdates();
	}
	public void addReservationAndSave(Reservation
											  reservation){
		calendarDetails.addReservation(reservation);
		saveUpdates();
	}

	public void addHolidayAndSave(Holiday
											  holiday){
		calendarDetails.addHoliday(holiday);
		saveUpdates();
	}

	public void deleteCalendarEvent(Long id){
		calendarDetails.deleteById(id);
		saveUpdates();
	}

	public void loadHolidaysFromFile(File holidayFile){
		try {
			BufferedReader input=new BufferedReader(new InputStreamReader(new FileInputStream(holidayFile)));
			String s="";
			while((s= input.readLine())!=null){
				String items[]=s.split(",");
				Date date=DEFAULT_FORMATTER.parse(items[0]);
				String name=items[1];
				Holiday holiday=new Holiday(name,date);
				addHolidayAndSave(holiday);
				System.out.println("Loading Holidays:"+holiday);
				try{
					Thread.sleep(2);
				}catch(Exception e){
					
				}
			}
		}catch (Exception e){
			throw new RuntimeException("Unable to load file:"+e);
		}
	}

	public void openCalendarEvents() {
		File file = new File(calendarEventsFileName);
		try {
			FileReader reader = new FileReader(file);
			char[] bb = new char[1024];
			String str = "";
			int n;
			while ((n = reader.read(bb)) != -1) {
				str += new String(bb, 0, n);
			}
			reader.close();//
			if(str.trim().length()>0) {
				Gson g = new Gson();
				calendarDetails = g.fromJson(str, CalendarDetails.class);
				if(calendarDetails==null){
					throw new RuntimeException("Unable to parse the calendar data file");
				}
			}
		}catch (Exception e){
			throw new RuntimeException("Unable to load file:"+e);
		}
	}

	public CalendarDetails getCalendarDetails(){
		return calendarDetails;
	}

    public static void main(String args[]){
       new CalendarData().openCalendarEvents();
    }

	class CalendarDetails{
		private Map<Long,Event> events=new TreeMap();
		private Map<Long,Reservation> reservations = new TreeMap();
		private Map<Long,Holiday> holidays = new TreeMap();

		public void addHoliday(Holiday holiday){
			holidays.put(holiday.getId(),holiday);
		}
		public void addEvent(Event event){
			events.put(event.getId(),event);
		}
		public void addReservation(Reservation reservation){
			reservations.put(reservation.getId(),reservation);
		}

		public void deleteHoliday(Holiday holiday){
			holidays.remove(holiday.getId());
		}
		public void deleteEvent(Event event){
			events.remove(event.getId());
		}
		public void deleteReservation(Reservation reservation){
			reservations.remove(reservation.getId());
		}

		public Collection<Event> getEventFor(Date date){
			Collection<Event> items=new ArrayList<>();
			for(Event e: this.events.values()){
				if(e.getEventDate().compareTo(date)==0){
					items.add(e);
				}
			}
			return items;
		}
		public Collection<Reservation> getReservationFor(Date date){
			Collection<Reservation> items=new ArrayList<>();
			for(Reservation e: this.reservations.values()){
				if(e.getReservationDate().compareTo(date)==0){
					items.add(e);
				}
			}
			return items;
		}
		public Collection<Holiday> getHolidayFor(Date date){
			Collection<Holiday> items=new ArrayList<>();
			for(Holiday e: this.holidays.values()){
				if(e.getHolidayDate().compareTo(date)==0){
					items.add(e);
				}
			}
			return items;
		}

		public Collection<CalendarEvent> getAllCalendarEvents(){
			Collection<CalendarEvent> allEvents= new ArrayList<>();
			allEvents.addAll(events.values());
			allEvents.addAll(reservations.values());
			allEvents.addAll(holidays.values());
			return allEvents;
		}

		public void deleteById(Long id){
			if (holidays.containsKey(id)) {
				holidays.remove(id);
			}else if(events.containsKey(id)){
				events.remove(id);
			}else{
				reservations.remove(id);
			}
		}
	}

	private void saveUpdates(){
		Gson gson=new Gson();
		String Content = gson.toJson(calendarDetails);
		try {
			File file = new File(calendarEventsFileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file);
			writer.write(Content);
			writer.flush();
			writer.close();
		}catch(Exception e){
			throw new RuntimeException("Unable to write to file:"+e);
		}
	}
	
}

