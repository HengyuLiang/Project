package Calendar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
=======
import java.text.SimpleDateFormat;
import java.util.Date;
>>>>>>> branch 'master' of https://github.com/HengyuLiang/Project.git

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
		details.add(jnames,gbcs);
		js.add(details,BorderLayout.CENTER);
<<<<<<< HEAD
		tab.addTab("SpecialDay", js);
		saveS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					String content = namesa.getText();
					String key = ""+year+""+month+""+day;
					FileInputStream   inOne=new FileInputStream(fileHoliday);
					ObjectInputStream inTwo=new ObjectInputStream(inOne);
					tableHoliday=(Hashtable)inTwo.readObject();
					inOne.close();
					inTwo.close();
					tableHoliday.put(key,content);                                  
					FileOutputStream out=new FileOutputStream(fileHoliday);
					ObjectOutputStream objectOut=new ObjectOutputStream(out);
					objectOut.writeObject(tableHoliday);
					objectOut.close();
					SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
					SpecialDay s=new SpecialDay("No.1", namesa.getText(), 
							"Hello!", sdf.parse(""+year+""+month+""+day),"8:00", "10:00");
					s.writeToFile("No.1");
					out.close();
		        }
		      catch(Exception ee){
		        }
			}
		});
		
=======

>>>>>>> branch 'master' of https://github.com/HengyuLiang/Project.git
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
		detaile.setLayout(new GridLayout(6,2));
		final JTextField eventDate=new JTextField(""+year+"-"+month+"-"+day);
		final JTextField eventName= new JTextField("enter name");
		final JTextField eventDetails= new JTextField("enter Details");

		final JTextField eventLocation= new JTextField("enter location");
		final JTextField eventStartTime= new JTextField("enter startTime");
		final JTextField eventEndTime= new JTextField("enter endTime");
		detaile.add(new JLabel("Event Date:")); detaile.add(eventDate);
		detaile.add(new JLabel("Event Name:")); detaile.add(eventName);
		detaile.add(new JLabel("Event Description:")); detaile.add(eventDetails);
		detaile.add(new JLabel("Event Location:")); detaile.add(eventLocation);
		detaile.add(new JLabel("Event Start Time:")); detaile.add(eventStartTime);
		detaile.add(new JLabel("Event End Time:")); detaile.add(eventEndTime);

		je.add(detaile,BorderLayout.CENTER);
		tab.addTab("Event", je);
		saveE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					String name = eventName.getText();
					String location = eventLocation.getText();
					String details = eventDetails.getText();
					String startTime=eventStartTime.getText();
					String endTime=eventEndTime.getText();
					Date eventDateValue = new SimpleDateFormat("yyyy-MM-dd").parse(eventDate.getText());
					Event event=new Event(name,location,details,eventDateValue,startTime,endTime);
					calendarData.addEventAndSave(event);
		        }
		      catch(Exception ee){
                  ee.printStackTrace();
                  throw new RuntimeException("Error capturing event");
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
        detailr.setLayout(new GridLayout(6,2));
        final JTextField reservationDate=new JTextField(""+year+"-"+month+"-"+day);
        final JTextField resourceType= new JTextField("enter Type:");
        final JTextField resourceLocation= new JTextField("enter Location:");

        final JTextField rCreatedBy= new JTextField("createdFor:");

        final JTextField rStartTime= new JTextField("enter startTime:");
        final JTextField rEndTime= new JTextField("enter endTime:");

        detailr.add(new JLabel("Reservation Date:")); detailr.add(reservationDate);
        detailr.add(new JLabel("Resource Type:")); detailr.add(resourceType);
        detailr.add(new JLabel("Created For:")); detailr.add(rCreatedBy);
        detailr.add(new JLabel("Resource Location:")); detailr.add(resourceLocation);
        detailr.add(new JLabel("Event Start Time:")); detailr.add(rStartTime);
        detailr.add(new JLabel("Event End Time:")); detailr.add(rEndTime);


		jr.add(detailr,BorderLayout.CENTER);
		tab.addTab("Reservation", jr);

		c.add(tab,BorderLayout.CENTER);
		saveR.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
                try{
                    String name = rCreatedBy.getText();
                    String location = resourceLocation.getText();
                    String details = resourceType.getText();
                    String startTime=eventStartTime.getText();
                    String endTime=eventEndTime.getText();
                    Date eventDateValue = new SimpleDateFormat("yyyy-MM-dd").parse(reservationDate.getText());
                    Reservation event=new Reservation(name,details,location,eventDateValue,startTime,endTime);
                    calendarData.addReservationAndSave(event);
                }
                catch(Exception ee){
                    ee.printStackTrace();
                    throw new RuntimeException("Error capturing event");
                }
                dispose();
			}
		});
		setVisible(true);
	}
}