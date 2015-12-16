package Calendar;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

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
		for(Event event: calendarDetails.events.values()){
			Calendar cal=Calendar.getInstance();
			cal.setTime(event.getEventDate());
			if(cal.get(Calendar.YEAR)==year && cal.get(Calendar.MONTH)+1 == month){
				eventDays.add(cal.get(Calendar.DAY_OF_MONTH));
			}
		}
		for(Reservation event: calendarDetails.reservations.values()){
			Calendar cal=Calendar.getInstance();
			cal.setTime(event.getReservationDate());
			if(cal.get(Calendar.YEAR)==year && cal.get(Calendar.MONTH)+1 == month){
				reservatoinDays.add(cal.get(Calendar.DAY_OF_MONTH));
			}
		}

		for(Holiday event: calendarDetails.holidays.values()){
			Calendar cal=Calendar.getInstance();
			cal.setTime(event.getHolidayDate());
			if(cal.get(Calendar.YEAR)==year && cal.get(Calendar.MONTH)+1 == month){
				holidays.add(cal.get(Calendar.DAY_OF_MONTH));
			}
		}

		int n=1;
		for(int i=week;i<week+noOfDaysInMonth;i++){
			temp[i]=String.valueOf(n);
			if(eventDays.contains(n)){
				temp[i]+="*";
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
			reader.close();// 关闭输入流，释放连接
			Gson g = new Gson();
			calendarDetails= g.fromJson(str, CalendarDetails.class);
		}catch (Exception e){
			throw new RuntimeException("Unable to load file:"+e);
		}
	}

	public CalendarDetails getCalendarDetails(){
		return calendarDetails;
	}
//
//	public void openCalendarEvents(String fileName){
//		calendarEventsFileName=fileName;
//
//		JSONParser parser = new JSONParser();
//
//		try {
			//
//			JSONParser parser=new JSONParser(Source.sourceFor("calendarData", new File(fileName)),new ErrorManager());
//			Node node=parser.parse();
			//read node for events and reservations
			//node.e
//		}catch (Exception e){
//			throw new RuntimeException("Unable to load file:",e);
//			Object obj = parser.parse(new FileReader(fileName));
//
//			JSONObject jsonObject = (JSONObject) obj;
//
//			JSONArray events = (JSONArray) jsonObject.get("Events");
//
//            for(Object eventObject : events){
//				JSONObject eventJsonObject = (JSONObject) eventObject;
//
//				//Events
//				Event event= new Event(getJsonString(eventJsonObject,"name"),getJsonString(eventJsonObject,"location"),
//						getJsonString(eventJsonObject,"description"), DATE_FORMATER.parse(getJsonString(eventJsonObject,"date")),
//						getJsonString(eventJsonObject,"startTime"), getJsonString(eventJsonObject,"endTime"));
//				System.out.println(event);
//
//				this.events.add(event);
//			}
//
//            JSONArray reservations = (JSONArray) jsonObject.get("Reservations");
//            for(Object eventObject : reservations){
//                JSONObject eventJsonObject = (JSONObject) eventObject;
//
//                //Events
//                Reservation event= new Reservation(getJsonString(eventJsonObject,"name"),
//                        getJsonString(eventJsonObject,"description"),getJsonString(eventJsonObject,"location"),
//                         DATE_FORMATER.parse(getJsonString(eventJsonObject,"date")),
//                        getJsonString(eventJsonObject,"startTime"), getJsonString(eventJsonObject,"endTime"));
//                System.out.println(event);
//
//                this.reservations.add(event);
//            }
//
//
//
//        } catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	private String getJsonString(JSONObject jsonObject, String columnName){
//		return (String) jsonObject.get(columnName);
//	}
//
//	private Date getJsonDate(JSONObject jsonObject, String columnName){
//		return (Date) jsonObject.get(columnName);
//	}
//
//	public void saveCalendarEvents(String fileName){
//		try {
//			PrintWriter printWriter = new PrintWriter(new FileOutputStream(fileName));
//			printWriter.println("{");
//			printWriter.print("\"Events\":[");
//			StringBuilder builder=new StringBuilder();
//			if(!events.isEmpty()) {
//				for (Event e : events) {
//					builder.append("\n");
//					builder.append("{");
//					builder.append(getPair("name", e.getEventName())).append(",");
//                    builder.append(getPair("location", e.getLocation())).append(",");
//					builder.append(getPair("description", e.getEventDiscription())).append(",");
//					builder.append(getPair("date", DATE_FORMATER.format(e.getEventDate()))).append(",");
//					builder.append(getPair("startTime", e.getStartTime())).append(",");
//					builder.append(getPair("endTime", e.getEndTime()));
//					builder.append("},");
//				}
//				printWriter.println(builder.toString().substring(0, builder.toString().length() - 1));
//			}
//			System.out.println(builder.toString().substring(0,builder.toString().length()-1));
//			printWriter.println("]");
//			printWriter.print("\"Reservations\":[");
//			StringBuilder builder2=new StringBuilder();
//			for(Reservation e: reservations){
//				builder2.append("\n");
//				builder2.append("{");
//				builder2.append(getPair("name",e.getReservationName())).append(",");
////				builder2.append(getPair("resourceType",e.getReservationResourcetype())).append(",");
////				builder2.append(getPair("location",e.getResourceLocation())).append(",");
////				builder2.append(getPair("date",DATE_FORMATER.format(e.getResourceDate()))).append(",");
//				builder2.append("},");
//			if(!reservations.isEmpty()) {
//				for (Reservation e : reservations) {
//					builder2.append("\n");
//					builder2.append("{");
//					builder2.append(getPair("name", e.getCreatedFor())).append(",");
//					builder2.append(getPair("resourceType", e.getReservationResourcetype())).append(",");
//					builder2.append(getPair("location", e.getResourceLocation())).append(",");
//					builder2.append(getPair("date", DATE_FORMATER.format(e.getResourceDate()))).append(",");
//                    builder.append(getPair("startTime", e.getStartTime())).append(",");
//                    builder.append(getPair("endTime", e.getEndTime()));
//					builder2.append("},");
//				}
//				printWriter.println(builder2.toString().substring(0, builder2.toString().length() - 1));
//				System.out.println(builder2.toString().substring(0, builder2.toString().length() - 1));
//			}
//
//
//			printWriter.println("]");
//            printWriter.println("}");
//			printWriter.flush();
//			printWriter.close();
//		}catch(FileNotFoundException fnfe){
//			throw new RuntimeException("Unable to save the events/reservations into file");
//		}
//
//	}
//
//	private String getPair(String name, String value){
//		return ""+"\""+name+"\":\""+value+"\"";
//	}

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

