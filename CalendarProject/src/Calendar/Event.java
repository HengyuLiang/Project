package Calendar;

import java.util.Date;

public class Event {
	private String eventId;
	private String eventName;
	private String eventCreator;
	private String eventDiscription;
	private Date eventDate;
	private String startTime, endTime;
	public Event(String eventId, String eventName, String eventCreator, String eventDiscription, Date eventDate,
			String startTime, String endTime) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventCreator = eventCreator;
		this.eventDiscription = eventDiscription;
		this.eventDate = eventDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public String getEventId() {
		return eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public String getEventCreator() {
		return eventCreator;
	}
	public String getEventDiscription() {
		return eventDiscription;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	
 
 
}
