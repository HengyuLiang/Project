package Calendar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewWindow extends JFrame{
	int year,month,day;
	private final CalendarData calendarData;
	public NewWindow(int year, int month, int day,CalendarData calendarData){
		this.year = year;
		this.month = month;
		this.day = day;
		this.calendarData=calendarData;
		Container c = getContentPane();
		setSize(400,300);
		setLocation(800, 0);
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		
		//create the holiday JPanel
		JPanel js=new JPanel();
		js.setLayout(new BorderLayout());
		JButton saveS=new JButton("Save");
		JButton deleteS=new JButton("Delete");
		deleteS.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			
			}
		});
		JButton cancelS=new JButton("Cancel");
		cancelS.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			
			}
		});
		JPanel jbs=new JPanel();
		jbs.add(saveS,BorderLayout.EAST);
		jbs.add(deleteS,BorderLayout.CENTER);
		jbs.add(cancelS, BorderLayout.WEST);
		js.add(jbs,BorderLayout.SOUTH);
		JPanel details=new JPanel();
		details.setLayout(new GridBagLayout());
		GridBagConstraints gbcs=new GridBagConstraints();
		gbcs.fill=GridBagConstraints.BOTH;
		gbcs.gridx=0;
		gbcs.gridy=0;
		gbcs.gridwidth=1;
		gbcs.gridheight=1;
		gbcs.weightx=0;
		gbcs.weighty=0;
		JLabel namesl=new JLabel("Name");
		details.add(namesl, gbcs);
		gbcs.gridx=1;
		gbcs.gridy=0;
		gbcs.gridwidth=10;
		gbcs.gridheight=2;
		gbcs.weightx=1;
		gbcs.weighty=1;
		JTextArea namesa=new JTextArea();
		JScrollPane jnames=new JScrollPane(namesa);
		namesa.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		namesa.setLineWrap(true); 
		namesa.setWrapStyleWord(true);
		namesa.setFont(new Font("Arial",1,20));
		try {
			SpecialDay sd;
			sd=SpecialDay.readFromFile(""+year+""+month+""+day);
			namesa.setText(sd.getSpecialdayName());
		} catch (Exception e1) {} 
		details.add(jnames,gbcs);
		js.add(details,BorderLayout.CENTER);
		tab.addTab("SpecialDay", js);
		saveS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					String content = namesa.getText();
					SpecialDay s=new SpecialDay(namesa.getText());
					s.writeToFile(""+year+""+month+""+day);
		        }
		      catch(Exception ee){}
			}
		});
		

		//create the event JPanel
		JPanel je=new JPanel();
		je.setLayout(new BorderLayout());
		JButton saveE=new JButton("Save");
		JButton cancelE=new JButton("Cancel");
		cancelE.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			
			}
		});
		JButton deleteE=new JButton("Delete");
		JPanel jbe=new JPanel(new FlowLayout());
		jbe.add(saveE);
		jbe.add(deleteE);
		jbe.add(cancelE);
		je.add(jbe,BorderLayout.SOUTH);
		JPanel detaile=new JPanel();
		detaile.setLayout(new GridLayout(4,2));
		JTextArea eventName= new JTextArea();	
		JScrollPane jeventName=new JScrollPane(eventName);
		eventName.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		eventName.setLineWrap(true); 
		eventName.setWrapStyleWord(true);
		eventName.setFont(new Font("Arial",1,20));
		try {
			Event e;
			e=Event.readFromFile(""+year+""+month+""+day);
			eventName.setText(e.getEventName());
		} catch (Exception e1) {} 
		JTextArea eventDetails= new JTextArea();
		JScrollPane jeventDetails=new JScrollPane(eventDetails);
		eventDetails.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		eventDetails.setLineWrap(true); 
		eventDetails.setWrapStyleWord(true);
		eventDetails.setFont(new Font("Arial",1,20));
		try {
			Event e;
			e=Event.readFromFile(""+year+""+month+""+day);
			eventDetails.setText(e.getEventDiscription());
		} catch (Exception e1) {} 
		JTextArea eventLocation= new JTextArea();
		JScrollPane jeventLocation=new JScrollPane(eventLocation);
		eventLocation.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		eventLocation.setLineWrap(true); 
		eventLocation.setWrapStyleWord(true);
		eventLocation.setFont(new Font("Arial",1,20));
		try {
			Event e;
			e=Event.readFromFile(""+year+""+month+""+day);
			eventLocation.setText(e.getLocation());
		} catch (Exception e1) {} 
		JTextArea eventStartTime= new JTextArea();
		JScrollPane jeventStartTime=new JScrollPane(eventStartTime);
		eventStartTime.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		eventStartTime.setLineWrap(true); 
		eventStartTime.setWrapStyleWord(true);
		eventStartTime.setFont(new Font("Arial",1,20));
		try {
			Event e;
			e=Event.readFromFile(""+year+""+month+""+day);
			eventStartTime.setText(e.getStartTime());
		} catch (Exception e1) {} 
		detaile.add(new JLabel("Event Name:")); 
		detaile.add(jeventName);
		detaile.add(new JLabel("Event Description:")); 
		detaile.add(jeventDetails);
		detaile.add(new JLabel("Event Location:")); 
		detaile.add(jeventLocation);
		detaile.add(new JLabel("Event Start Time:")); 
		detaile.add(jeventStartTime);
		je.add(detaile,BorderLayout.CENTER);
		tab.addTab("Event", je);
		saveE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					String name = eventName.getText();
					String location = eventLocation.getText();
					String details = eventDetails.getText();
					String startTime=eventStartTime.getText();
					Event et=new Event(name,location,details,startTime);
					et.writeToFile(""+year+""+month+""+day);
		        }
		      catch(Exception ee){
		        }
                dispose();
            }
		});
		
		
		//create the reservation JPanel
		JPanel jr=new JPanel();
		jr.setLayout(new BorderLayout());
		JButton saveR=new JButton("Save");
		JButton cancelR=new JButton("Cancel");
		cancelR.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			
			}
		});
		JButton deleteR=new JButton("Delete");
		JPanel jbr=new JPanel(new FlowLayout());
		jbr.add(saveR,BorderLayout.EAST);
		jbr.add(deleteR, BorderLayout.CENTER);
		jbr.add(cancelR, BorderLayout.WEST);
		jr.add(jbr,BorderLayout.SOUTH);
		JPanel detailr=new JPanel();
        detailr.setLayout(new GridLayout(4,2));
        JTextArea resourceLocation= new JTextArea();
        JScrollPane jresourceLocation=new JScrollPane(resourceLocation);
        resourceLocation.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
        resourceLocation.setLineWrap(true); 
        resourceLocation.setWrapStyleWord(true);
        resourceLocation.setFont(new Font("Arial",1,20));
		try {
			Reservation rv;
			rv=Reservation.readFromFile(""+year+""+month+""+day);
			resourceLocation.setText(rv.getReservationName());
		} catch (Exception e1) {} 
        JTextArea rStartTime= new JTextArea();
        JScrollPane jrStartTime=new JScrollPane(rStartTime);
        rStartTime.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
        rStartTime.setLineWrap(true); 
        rStartTime.setWrapStyleWord(true);
        rStartTime.setFont(new Font("Arial",1,20));
		try {
			Reservation rv;
			rv=Reservation.readFromFile(""+year+""+month+""+day);
			rStartTime.setText(rv.getStartTime());
		} catch (Exception e1) {} 
        JTextArea rName= new JTextArea();
        JScrollPane jrName=new JScrollPane(rName);
        rName.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
        rName.setLineWrap(true); 
        rName.setWrapStyleWord(true);
        rName.setFont(new Font("Arial",1,20));
		try {
			Reservation rv;
			rv=Reservation.readFromFile(""+year+""+month+""+day);
			rName.setText(rv.getReservationName());
		} catch (Exception e1) {} 
        JTextArea rDetails= new JTextArea();
        JScrollPane jrDetails=new JScrollPane(rDetails);
        rDetails.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
        rDetails.setLineWrap(true); 
        rDetails.setWrapStyleWord(true);
        rDetails.setFont(new Font("Arial",1,20));
		try {
			Reservation rv;
			rv=Reservation.readFromFile(""+year+""+month+""+day);
			rDetails.setText(rv.getReservationDiscription());
		} catch (Exception e1) {} 
        detailr.add(new JLabel("Name:")); 
        detailr.add(jrName);
        detailr.add(new JLabel("Resource Location:")); 
        detailr.add(jresourceLocation);
        detailr.add(new JLabel("Details:")); 
        detailr.add(jrDetails);
        detailr.add(new JLabel("Event Start Time:")); 
        detailr.add(jrStartTime);
		jr.add(detailr,BorderLayout.CENTER);
		tab.addTab("Reservation", jr);
		c.add(tab,BorderLayout.CENTER);
		saveR.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
                try{
                    String name = rName.getText();
                    String location = resourceLocation.getText();
                    String details = rDetails.getText();
                    String startTime=eventStartTime.getText();
                    Reservation rv=new Reservation(name,details,location,startTime);
                    rv.writeToFile(""+year+""+month+""+day);
                }
                catch(Exception ee){}
                dispose();
			}
		});
		setVisible(true);
	}
}