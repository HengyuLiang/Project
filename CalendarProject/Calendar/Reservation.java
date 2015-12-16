package Calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.google.gson.Gson;
public class Reservation 
{
	private String startTime;
	private String location;
	private String reservationName;
	private String reservationDiscription;
	private Date reservationDate;
	private final Long id;

	public Reservation(Date reservationDate,String reservationName, String reservationDiscription,String location,String startTime) {
		this(reservationDate,reservationName,reservationDiscription,location,startTime,System.currentTimeMillis());
	}
	public Reservation(Date reservationDate,String reservationName, String reservationDiscription,String location,String startTime,Long id) {
		this.reservationName = reservationName;
		this.reservationDiscription = reservationDiscription;
		this.startTime = startTime;
		this.location = location;
		this.reservationDate=reservationDate;
		this.id=id;
	}
	public String getReservationDiscription() {
		return reservationDiscription;
	}

	public Long getId() {
		return id;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDiscription(String reservationDiscription) {
		this.reservationDiscription = reservationDiscription;
	}
	public String getStartTime() {
		return startTime;
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
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
}
