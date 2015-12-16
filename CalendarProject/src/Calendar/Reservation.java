package Calendar;

import java.util.Date;

public class Reservation 
{
	private String createdFor;
	private String startTime;
	private String endTime;
	private String reservationResourcetype;
	private String resourceLocation;
	private Date resourceDate;

	public Reservation(String createdFor, String reservationResourcetype, String resourceLocation, Date resourceDate, String startTime, String endTime) {
		this.createdFor = createdFor;
		this.reservationResourcetype = reservationResourcetype;
		this.resourceLocation = resourceLocation;
		this.resourceDate = resourceDate;
		this.startTime=startTime;
		this.endTime=endTime;
	}


	public String getReservationResourcetype() {
		return reservationResourcetype;
	}

	public String getResourceLocation() {
		return resourceLocation;
	}

	public Date getResourceDate() {
		return resourceDate;
	}

	public String getCreatedFor() {
		return createdFor;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
}
