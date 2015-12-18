package Calendar;

import java.util.Date;

/**
 * Created by amk on 12/16/2015.
 */
public interface CalendarEvent {
    public Long getId();
    public Date getEventDate();
    public String getName();
}
