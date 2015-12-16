package Calendar;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Reminder extends JFrame{
	private String s;
	private int day,month,year;
	private CalendarData calendarData;
	public Reminder(String s,int year,int month,int day, CalendarData calendarData){
		this.day = day;
		this.month = month;
		this.year = year;
		this.s=s;
		this.calendarData=calendarData;
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		setSize(400,300);
		setLocation(800, 300);
		
		//add a label
		JLabel reminder=new JLabel("Today, you have a reminder!");
		c.add(reminder, BorderLayout.NORTH);
		
		//add a text area
		JTextArea detail=new JTextArea();
		JScrollPane jdetail=new JScrollPane(detail);
		detail.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		boolean display=false;
		try{
			Date d=new SimpleDateFormat("yyyyMMdd").parse(""+year+""+month+""+day);
			Collection<Event> events=calendarData.getCalendarDetails().getEventFor(d);
			Collection<Reservation> reservations=calendarData.getCalendarDetails().getReservationFor(d);
			Collection<Holiday> holidays=calendarData.getCalendarDetails().getHolidayFor(d);
			if(events.size()>0 || reservations.size() >0 || holidays.size() >0){
				for(Event event: events){
					detail.append("Event :"+ event.getEventName()+":"+event.getLocation()+"\n");
				}
				for(Reservation reservation: reservations){
					detail.append("Reservation:"+reservation.getReservationName()+":"+reservation.getReservationDiscription()+"\n");
				}
				for(Holiday holiday: holidays){
					detail.append("Holiday:"+holiday.getName()+"\n");
				}
				display=true;
			}
		}catch(Exception e1){
			
		}
		//detail.setText(s);//set the containing of the reminder
		detail.setLineWrap(true); 
		detail.setWrapStyleWord(true);
		detail.setFont(new Font("Arial",1,20));
		detail.setEnabled(false);
		c.add(jdetail, BorderLayout.CENTER);
		
		//add a cancel button
		JButton cancel=new JButton("CANCEL");
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				dispose();
			}
		});
		c.add(cancel, BorderLayout.SOUTH);
		
		if(display) {
			setVisible(true);
		}
		
	}
	
}
