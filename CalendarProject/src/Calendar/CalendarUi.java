package Calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		calendar.setYear(year);
		calendar.setMonth(month);  //set initial data;

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
			if(calendar.getYear()==calendar.getToyear()&&calendar.getMonth()==calendar.getTomonth()&&i==calendar.getToday()){
				
				labelDay[i].setForeground(Color.blue);
				Reminder r=new Reminder("Today, ");
			}else{
				labelDay[i].setForeground(Color.BLACK);
			}
			if(i%7==0||i%7==6){
				labelDay[i].setForeground(Color.red);
			}

		}

		//in order to create a new panel in the center of the window
		
		final JPanel JCenter = new JPanel();
		JCenter.setLayout(new BorderLayout());
		JCenter.add(JNorth,BorderLayout.NORTH);
		JCenter.add(jpcenter,BorderLayout.CENTER);
		c.add(JCenter, BorderLayout.CENTER);
		
		//create message label: show message and save the content of event.
//		JPanel JSouth=new JPanel();
//		JSouth.setLayout(new GridBagLayout());
//		GridBagConstraints gbcs=new GridBagConstraints();
//		gbcs.fill=GridBagConstraints.BOTH;
//		gbcs.gridx=0;
//		gbcs.gridy=0;
//		gbcs.gridwidth=12;
//		gbcs.gridheight=10;
//		gbcs.weightx=1;
//		gbcs.weighty=1;
//		message=new JTextArea();
//		JScrollPane jnote=new JScrollPane(message);
//		message.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
//		message.setLineWrap(true); 
//		message.setWrapStyleWord(true);
//		message.setFont(new Font("Arial",1,20));
//		message.setEnabled(false);
//		JSouth.add(jnote,gbcs);
//		gbcs.gridx=12;
//		gbcs.gridy=0;
//		gbcs.gridwidth=2;
//		gbcs.gridheight=5;
//		gbcs.weightx=0;
//		gbcs.weighty=0; 
//		event=new JButton(" EVENT ");
//		event.addActionListener(new ActionListener(){
//		public void actionPerformed(ActionEvent ae){
//			setUpCalendarEvent(CalendarUi.this, calendar);
//		}
//	});
//		JSouth.add(event,gbcs);
//		gbcs.gridx=12;
//		gbcs.gridy=5;
//		gbcs.gridwidth=2;
//		gbcs.gridheight=5;
//		gbcs.weightx=0;
//		gbcs.weighty=0;
//		delete=new JButton("DELETE");
//		JSouth.add(delete,gbcs);
//		c.add(JSouth,BorderLayout.SOUTH);
		
		//create the menu
		jmb = new JMenuBar();
		File = new JMenu("File");
		Open = new JMenuItem("Open");
		Save = new JMenuItem("Save");
		File.add(Open);
		File.add(Save);
		Search = new JMenu("Search");
		Year = new JMenuItem("Year");
		Month = new JMenuItem("Month");
		Search.add(Year);
		Search.add(Month);
		jmb.add(File);
		jmb.add(Search);
		c.add(jmb,BorderLayout.NORTH);
		
		//add ActionListener 
		nextMonth.addActionListener(this);
		previousMonth.addActionListener(this);
		nextYear.addActionListener(this);
		previousYear.addActionListener(this);
		hashtable=new Hashtable();
	    file=new File("event.txt");
	    if(!file.exists())
	      {
	       try{
	           FileOutputStream out=new FileOutputStream(file);
	           ObjectOutputStream objectOut=new ObjectOutputStream(out);
	           objectOut.writeObject(hashtable);
	           objectOut.close();
	           out.close();
	          }
	       catch(IOException e){
	          }
	      } 

		for(int i=0;i<42;i++){
			int a=i;
			labelDay[a].addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
							if(labelDay[a].getText().equals(""));
							else{
								if(e.getButton() == MouseEvent.BUTTON3){
									//left button
									NewWindow nw=new NewWindow(year,month,Integer.parseInt(labelDay[a].getText()),calendar);
								}else if(e.getButton() == MouseEvent.BUTTON1){
									//right button 
//									message.setText("Hello");	
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
//				if(i%7==0||i%7==6){
//					labelDay[i].setForeground(Color.red);
//				}
				if(calendar.getYear()==calendar.getToyear()&&calendar.getMonth()==calendar.getTomonth()&&i==calendar.getToday()){
					
					labelDay[i].setForeground(Color.blue);
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
//				if(i%7==0||i%7==6){
//					labelDay[i].setForeground(Color.red);
//				}
				if(calendar.getYear()==calendar.getToyear()&&calendar.getMonth()==calendar.getTomonth()&&i==calendar.getToday()){
					
					labelDay[i].setForeground(Color.blue);
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
//				if(i%7==0||i%7==6){
//					labelDay[i].setForeground(Color.red);
//				}
				if(calendar.getYear()==calendar.getToyear()&&calendar.getMonth()==calendar.getTomonth()&&i==calendar.getToday()){
					labelDay[i].setForeground(Color.blue);
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
//				if(i%7==0||i%7==6){
//					labelDay[i].setForeground(Color.red);
//				}
//				
				if(calendar.getYear()==calendar.getToyear()&&calendar.getMonth()==calendar.getTomonth()&&i==calendar.getToday()){
					labelDay[i].setForeground(Color.blue);
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
	
	private void setUpCalendarEvent(final JFrame jFrame, final CalendarData calData){
		JDialog dialog=new JDialog(jFrame,"Create Event",true);
		dialog.setSize(300, 200);
		dialog.setBounds(jFrame.getWidth() - 300, jFrame.getHeight() - 200, 300, 200);
		dialog.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton add = new JButton("Add");
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.setVisible(false);
				dialog.dispose();
			}
		});
		buttonPanel.add(add);
		buttonPanel.add(cancel);
		dialog.add(buttonPanel,BorderLayout.SOUTH);
		JPanel detailspanel = new JPanel(new GridLayout(6,2));
		JTextField eventname = new JTextField(20);
		JTextField eventlocation = new JTextField(20);
		JTextField eventdiscription = new JTextField(20);
		JTextField eventdate = new JTextField(20);
		JTextField starttime = new JTextField(20);
		JTextField endtime = new JTextField(20);
		detailspanel.add(new JLabel("Event Name:"));
		detailspanel.add (eventname);
		detailspanel.add(new JLabel("Event Location:"));
		detailspanel.add (eventlocation);

		detailspanel.add(new JLabel("Event Discription"));
		detailspanel.add (eventdiscription);

		detailspanel.add(new JLabel("Event Date:"));
		detailspanel.add (eventdate);

		detailspanel.add(new JLabel("Start Time:"));
		detailspanel.add (starttime);
		
		detailspanel.add(new JLabel("End Time:"));
		detailspanel.add (endtime);
		dialog.add(detailspanel,BorderLayout.CENTER);
		
		dialog.setVisible(true);
		

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{

				Event event = new Event(eventname.getText(),eventlocation.getText(),
						eventdiscription.getText(),
						format.parse(eventdate.getText()),
						starttime.getText(),endtime.getText());

				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
				dialog.setVisible(false);
				dialog.dispose();
			}
		});
		
		dialog.setVisible(true);
	}
	
}	  
//need new actionListener