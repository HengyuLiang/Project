package Calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.google.gson.Gson;

public class Holiday {
	private String name;
	private Date holidayDate;
	private final Long id;

	public Holiday(String name, Date holidayDate) {
		this(name,holidayDate,System.currentTimeMillis());
	}

	public Holiday(String name, Date holidayDate, Long id){
		this.name=name;
		this.holidayDate=holidayDate;
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public Date getHolidayDate() {
		return holidayDate;
	}

	public Long getId() {
		return id;
	}


}
