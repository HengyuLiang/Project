package Calendar;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Reminder extends JFrame{
	private String s;
	private int day,month,year;
	public Reminder(String s,int year,int month,int day){
		this.day = day;
		this.month = month;
		this.year = year;
		this.s=s;
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
		try{
			Reservation rv;
			rv = Reservation.readFromFile(""+year+""+month+""+day);
			detail.setText("Reservation Name is:"+rv.getReservationName()+" in "+rv.getLocation()+" at "+rv.getStartTime());
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
		
		
		setVisible(true);
		
		
		
	}
	
}
