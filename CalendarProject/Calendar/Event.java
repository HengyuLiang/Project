package Calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.google.gson.Gson;

public class Event implements CalendarEvent {
	private String eventName;
	private String location;
	private String eventDiscription;
	private String startTime;
	private Date eventDate;
	private final Long id;

	public Event(Date eventDate,String eventName, String location,String eventDiscription,String startTime) {
		this(eventDate,eventName,location,eventDiscription,startTime,System.currentTimeMillis());
	}
	public Event(Date eventDate,String eventName, String location,String eventDiscription,String startTime,Long id) {
		this.eventDate=eventDate;
		this.eventName = eventName;
		this.eventDiscription = eventDiscription;
		this.location=location;
		this.startTime = startTime;
		this.id=id;
	}

	public Long getId() {
		return id;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public String getEventName() {
		return eventName;
	}
	public String getEventDiscription() {
		return eventDiscription;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public void setEventDiscription(String eventDiscription) {
		this.eventDiscription = eventDiscription;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public String getName(){
		return getEventName();
	}
	@Override
	public String toString() {
		return "Event{" +
				"eventDate=" + eventDate +
				", eventName='" + eventName + '\'' +
				", location='" + location + '\'' +
				", eventDiscription='" + eventDiscription + '\'' +
				", startTime='" + startTime + '\'' +
				", id=" + id +
				'}';
	}
}
