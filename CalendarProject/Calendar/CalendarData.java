package Calendar;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class CalendarData {

	private int year;
	private int month;
	private Collection<Event> events=new ArrayList<Event>();
	private Collection<Reservation> reservations = new ArrayList<Reservation>();
    private Collection<Date> holidays = new ArrayList<Date>();
	private String calendarEventsFileName="default-calendar-data.json";
 	public static final SimpleDateFormat DATE_FORMATER =  new SimpleDateFormat("MM/dd/yyyy");
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
		List<Integer> eventDays=new ArrayList();
		List<Integer> reservatoinDays=new ArrayList();
		for(Event event: events){
			Calendar cal=Calendar.getInstance();
			cal.setTime(event.getEventDate());
			if(cal.get(Calendar.YEAR)==year && cal.get(Calendar.MONTH)+1 == month){
				eventDays.add(cal.get(Calendar.DAY_OF_MONTH));
			}
		}
		for(Reservation event: reservations){
			Calendar cal=Calendar.getInstance();
//			cal.setTime(event.getResourceDate());
			if(cal.get(Calendar.YEAR)==year && cal.get(Calendar.MONTH)+1 == month){
				reservatoinDays.add(cal.get(Calendar.DAY_OF_MONTH));
			}
		}
		int n=1;
		for(int i=week;i<week+noOfDaysInMonth;i++){
			temp[i]=String.valueOf(n)+(eventDays.contains(n)?"*":"")+(reservatoinDays.contains(n)?"#":"");
			n++;
		}
		return temp;
	
	}

//	public void addEventAndSave(Event event){
//		this.events.add(event);
//		saveCalendarEvents(calendarEventsFileName);
//	}
//	public void addReservationAndSave(Reservation
//											  reservation){
//		this.reservations.add(reservation);
//		saveCalendarEvents(calendarEventsFileName);
//	}
//	public void openCalendarEvents(){
//		openCalendarEvents(calendarEventsFileName);
//	}
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

//    public static void main(String args[]){
//        new CalendarData().openCalendarEvents();
//    }
	
}
