package Calendar;

import java.util.Date;

public class Event {
	private String eventName;
	private String location;
	private String eventDiscription;
	private Date eventDate;
	private String startTime, endTime;
	public Event(String eventName, String location,
				 String eventDiscription, Date eventDate,
				 String startTime, String endTime) {
		this.eventName = eventName;
		this.eventDiscription = eventDiscription;
		this.location=location;
		this.eventDate = eventDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getEventName() {
		return eventName;
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

	public String getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "Event{" +
				" eventName='" + eventName + '\'' +
				", eventDiscription='" + eventDiscription + '\'' +
				", eventDate=" + eventDate +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				'}';
	}
}
