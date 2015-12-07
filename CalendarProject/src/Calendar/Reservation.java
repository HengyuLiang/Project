package Calendar;

import java.util.Date;

public class Reservation 
{
	private String reservationName;
	private String reservationResourcetype;
	private String resourceLocation;
	private Date resourceDate;

	public Reservation(String reservationName, String reservationResourcetype, String resourceLocation, Date resourceDate) {
		this.reservationName = reservationName;
		this.reservationResourcetype = reservationResourcetype;
		this.resourceLocation = resourceLocation;
		this.resourceDate = resourceDate;
	}

	public String getReservationName() {
		return reservationName;
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
}
