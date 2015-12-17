package Calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.*;

public class CalendarUi extends JFrame implements ActionListener{
	JLabel labelDay[] = new JLabel[42];
	JLabel showDate;
	JButton weekName[] = new JButton[7];
	String name[] = {"Sun","Mon","Tue","Wen","Thr","Fri","Sat"};
	JButton nextMonth, previousMonth,nextYear,previousYear,event, delete;
	JTextArea message;
	JMenuBar jmb;
	JMenu File, Search;
	JMenuItem Open,Save, Year, Month;
	int year = 2015, month = 12;  //initial date;
	Hashtable hashtable;  
	File file;
	final CalendarData calendar;
	
	public CalendarUi(){
		Container c = getContentPane();
		setSize(800,600);
		setTitle("MyCalendar");
		calendar = new CalendarData();
		year = Calendar.getInstance().get(Calendar.YEAR);
		month = Calendar.getInstance().get(Calendar.MONTH)+1;
		calendar.setYear(year);
		calendar.setMonth(month);  //set initial data;
		calendar.openCalendarEvents();
		//create the operation panel;
		JPanel JNorth = new JPanel();
		JNorth.setLayout(new GridBagLayout());
		GridBagConstraints gbcn = new GridBagConstraints();
		gbcn.fill = GridBagConstraints.BOTH;
		gbcn.gridx = 0;
		gbcn.gridy = 0;
		gbcn.gridheight = 1;
		gbcn.gridwidth = 1;
		gbcn.weightx = 0;
		gbcn.weighty = 0;
		// add button ;
		previousYear = new JButton("<<");
		JNorth.add(previousYear,gbcn);
		gbcn.gridx=1;
		gbcn.gridy=0;
		gbcn.gridwidth=1;
		gbcn.gridheight=1;
		gbcn.weightx=0;
		gbcn.weighty=0;
		previousMonth=new JButton("<");
		JNorth.add(previousMonth,gbcn);
		gbcn.gridx=2;
		gbcn.gridy=0;
		gbcn.gridwidth=10;
		gbcn.gridheight=1;
		gbcn.weightx=1;
		gbcn.weighty=0;
		showDate=new JLabel(calendar.getYear()+"."+calendar.getMonth());
		showDate.setHorizontalAlignment(JTextField.CENTER);
		JNorth.add(showDate,gbcn);
		gbcn.gridx=12;
		gbcn.gridy=0;
		gbcn.gridwidth=1;
		gbcn.gridheight=1;
		gbcn.weightx=0;
		gbcn.weighty=0;
		nextMonth = new JButton(">");
		JNorth.add(nextMonth, gbcn);
		gbcn.gridx = 13;
		gbcn.gridy = 0;
		gbcn.gridwidth = 1;
		gbcn.gridheight = 1;
		gbcn.weightx = 0;
		gbcn.weighty = 0;
		nextYear = new JButton(">>");
		JNorth.add(nextYear, gbcn);
		
		//create date panel: show the dates
		final JPanel jpcenter=new JPanel();
		jpcenter.setLayout(new GridLayout(7,7));
		for(int i=0;i<7;i++){
			weekName[i]=new JButton(name[i]);
			jpcenter.add(weekName[i]);
		}
		for(int i=0;i<42;i++){
			labelDay[i]=new JLabel("",JLabel.CENTER);
			jpcenter.add(labelDay[i]);
		}
		String day[]=calendar.getDate();
		for(int i=0;i<42;i++){
			labelDay[i].setText(day[i]);
			String text=day[i]==null?"-":day[i];
			if(calendar.getYear()==calendar.getToyear()&&calendar.getMonth()==calendar.getTomonth() && text.contains(""+calendar.getToday())){
				
				labelDay[i].setForeground(new Color(0,255,0));
				String s= ""+calendar.getToyear()+""+calendar.getTomonth()+""+calendar.getToday();
				//File f = new File("/Users/hengyuliang/Desktop/"+s+"Reseravtion.txt");
				//if(f.exists()){
				Reminder r=new Reminder("Today, ",calendar.getToyear(),calendar.getTomonth(),calendar.getToday(),calendar);

				//}
			}else{
				labelDay[i].setForeground(Color.BLACK);
			}

			if(text.contains("&") || text.contains("#")){
				boolean hasEvent=false;
				boolean hasReservation=false;
				if(text.contains("&")){
					hasEvent=true;
				}
				if(text.contains("#")){
					hasReservation=true;
				}
				text=text.replaceAll("&","").replaceAll("#","");
				text+=":";
				labelDay[i].setText(text);

				labelDay[i].setForeground(new Color(255,hasEvent?255:0, hasReservation?255:0));
			}

			if(i%7==0||i%7==6){
				labelDay[i].setForeground(Color.red);
			}else{
				if(text.contains("h")){
					labelDay[i].setForeground(Color.red);
					labelDay[i].setText(text.replaceAll("h",""));
				}
			}

		}

		//in order to create a new panel in the center of the window
		
		final JPanel JCenter = new JPanel();
		JCenter.setLayout(new BorderLayout());
		JCenter.add(JNorth,BorderLayout.NORTH);
		JCenter.add(jpcenter,BorderLayout.CENTER);
		c.add(JCenter, BorderLayout.CENTER);

		//add ActionListener 
		nextMonth.addActionListener(this);
		previousMonth.addActionListener(this);
		nextYear.addActionListener(this);
		previousYear.addActionListener(this);
//		hashtable=new Hashtable();
//	    file=new File("event.txt");
//	    if(!file.exists())
//	      {
//	       try{
//	           FileOutputStream out=new FileOutputStream(file);
//	           ObjectOutputStream objectOut=new ObjectOutputStream(out);
//	           objectOut.writeObject(hashtable);
//	           objectOut.close();
//	           out.close();
//	          }
//	       catch(IOException e){
//	          }
//	      } 

		for(int i=0;i<42;i++){
			int a=i;
			labelDay[a].addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
							if(labelDay[a].getText().equals(""));
							else{
								if(e.getButton() == MouseEvent.BUTTON3){
									//left button
									NewWindow nw=new NewWindow(year,month,Integer.parseInt(labelDay[a].getText().replaceAll(":","")),calendar);
								}else if(e.getButton() == MouseEvent.BUTTON1){
									//right button 
								}
							}
						}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		
		setVisible(true);
		
		addWindowListener
		(
		 new WindowAdapter() {
			 public void windowClosing(WindowEvent e) {
				 System.exit(0);
			 }
		 } );
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==nextMonth){
			month = month + 1;
			if(month>12){
				month = 1;
				year = year+1;
			}
			calendar.setMonth(month);
			calendar.setYear(year);
			String day[] = calendar.getDate();
			for(int i=0;i<42;i++){
				labelDay[i].setText(day[i]);
				if(calendar.getYear()==calendar.getToyear()&&calendar.getMonth()==calendar.getTomonth()&&i==calendar.getToday()){
					
					labelDay[i].setForeground(new Color(0,255,0));
				}else{
					labelDay[i].setForeground(Color.BLACK);
				}
				if(i%7==0||i%7==6){
					labelDay[i].setForeground(Color.red);
				}
			}
			showDate.setText(calendar.getYear()+"."+calendar.getMonth() );
		}else if(e.getSource()==previousMonth){
			month = month-1;
			if(month<1){
				month=12;
				year = year-1;
			}
			calendar.setMonth(month);
			calendar.setYear(year);
		    String day[]=calendar.getDate();
		    for(int i=0;i<42;i++){
				labelDay[i].setText(day[i]);
				if(calendar.getYear()==calendar.getToyear()&&calendar.getMonth()==calendar.getTomonth()&&i==calendar.getToday()){
					
					labelDay[i].setForeground(new Color(0,255,0));
				}else{
					labelDay[i].setForeground(Color.BLACK);
				}
				if(i%7==0||i%7==6){
					labelDay[i].setForeground(Color.red);
				}
			}
		    showDate.setText(calendar.getYear()+"."+calendar.getMonth() );
		}else if(e.getSource()==nextYear){
			year = year+1;
			calendar.setYear(year);
			String day[] = calendar.getDate();
			for(int i=0;i<42;i++){
				labelDay[i].setText(day[i]);
				if(calendar.getYear()==calendar.getToyear()&&calendar.getMonth()==calendar.getTomonth()&&i==calendar.getToday()){
					labelDay[i].setForeground(new Color(0,255,0));
				}else{
					labelDay[i].setForeground(Color.BLACK);
				}
				if(i%7==0||i%7==6){
					labelDay[i].setForeground(Color.red);
				}
			}
			showDate.setText(calendar.getYear()+"."+calendar.getMonth() );
		}else if(e.getSource()==previousYear){
			year = year-1;
			if(year<1){
				year = 1;
			}
			calendar.setYear(year);
			String day[] = calendar.getDate();
			for(int i=0;i<42;i++){
				labelDay[i].setText(day[i]);
				if(calendar.getYear()==calendar.getToyear()&&calendar.getMonth()==calendar.getTomonth()&&i==calendar.getToday()){
					labelDay[i].setForeground(new Color(0,255,0));
				}else{
					labelDay[i].setForeground(Color.BLACK);
				}
				if(i%7==0||i%7==6){
					labelDay[i].setForeground(Color.red);
				}
			}
			showDate.setText(calendar.getYear()+"."+calendar.getMonth() );
		}
	}
	
}	  
//need new actionListener