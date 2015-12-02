package Calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.Calendar;

public class CalendarUi extends JFrame implements ActionListener{
	JLabel labelDay[] = new JLabel[42];
	JTextField showDate;
	JButton weekName[] = new JButton[7];
	String name[] = {"Sun","Mon","Tue","Wen","Thr","Fri","Sat"};
	JButton nextMonth, previousMonth,nextYear,previousYear,save, event;
	JTextArea message;
	JMenuBar jmb;
	JMenu File, Search;
	JMenuItem Open,Save, Year, Month;
	int year = 2015, month = 12;  //initial date;
	CalendarData calendar;
	
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
		showDate=new JTextField(calendar.getYear()+"."+calendar.getMonth());
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
		JPanel jpcenter=new JPanel();
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
//<<<<<<< HEAD
			if(i%7==0||i%7==6){
				labelDay[i].setForeground(Color.red);
			}
			if(calendar.getYear()==calendar.getToyear())
				if(calendar.getMonth()==calendar.getTomonth())
					if(i==calendar.getToday()){
						labelDay[i].setFont(new Font("Arial", 1, 20));
						labelDay[i].setForeground(Color.blue);
					}
		}
		
		//combine jsp and JNorth
//=======
		

		//in order to create a new panel in the center of the window
		
		JPanel JCenter = new JPanel();
		JCenter.setLayout(new BorderLayout());
		JCenter.add(JNorth,BorderLayout.NORTH);
		JCenter.add(jpcenter,BorderLayout.CENTER);
		c.add(JCenter, BorderLayout.CENTER);
		
		//create message label: show message and save the content of event.
		JPanel JSouth=new JPanel();
		JSouth.setLayout(new GridBagLayout());
		GridBagConstraints gbcs=new GridBagConstraints();
		gbcn.fill=GridBagConstraints.BOTH;
		gbcs.gridx=0;
		gbcs.gridy=0;
		gbcs.gridwidth=2;
		gbcs.gridheight=4;
		gbcs.weightx=0;
		gbcs.weighty=1;
		event=new JButton("EVENT:");
		JSouth.add(event, gbcs);
		gbcs.gridx=2;
		gbcs.gridy=0;
		gbcs.gridwidth=12;
		gbcs.gridheight=4;
		gbcs.weightx=1;
		gbcs.weighty=1;
		message=new JTextArea();
		message.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		message.setLineWrap(true); 
		message.setWrapStyleWord(true);
		JSouth.add(message,gbcs);
		gbcs.gridx=14;
		gbcs.gridy=0;
		gbcs.gridwidth=2;
		gbcs.gridheight=14;
		gbcs.weightx=0;
		gbcs.weighty=1;
		save=new JButton("SAVE");
		JSouth.add(save,gbcs);
		c.add(JSouth,BorderLayout.SOUTH);
		
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
				if(i%7==0||i%7==6){
					labelDay[i].setForeground(Color.red);
				}
				if(calendar.getYear()==calendar.getToyear())
					if(calendar.getMonth()==calendar.getTomonth())
						if(i==calendar.getToday()){
							labelDay[i].setFont(new Font("Arial", 1, 20));
							labelDay[i].setForeground(Color.blue);
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
				if(i%7==0||i%7==6){
					labelDay[i].setForeground(Color.red);
				}
				if(calendar.getYear()==calendar.getToyear())
					if(calendar.getMonth()==calendar.getTomonth())
						if(i==calendar.getToday()){
							labelDay[i].setFont(new Font("Arial", 1, 20));
							labelDay[i].setForeground(Color.blue);
						}
			}
		    showDate.setText(calendar.getYear()+"."+calendar.getMonth() );
		}else if(e.getSource()==nextYear){
			year = year+1;
			calendar.setYear(year);
			String day[] = calendar.getDate();
			for(int i=0;i<42;i++){
				labelDay[i].setText(day[i]);
				if(i%7==0||i%7==6){
					labelDay[i].setForeground(Color.red);
				}
				if(calendar.getYear()==calendar.getToyear())
					if(calendar.getMonth()==calendar.getTomonth())
						if(i==calendar.getToday()){
							labelDay[i].setFont(new Font("Arial", 1, 20));
							labelDay[i].setForeground(Color.blue);
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
				if(i%7==0||i%7==6){
					labelDay[i].setForeground(Color.red);
				}
				
				if(calendar.getYear()==calendar.getToyear())
					if(calendar.getMonth()==calendar.getTomonth())
						if(i==calendar.getToday()){
							labelDay[i].setFont(new Font("Arial", 1, 20));
							labelDay[i].setForeground(Color.blue);
						}
			}
			showDate.setText(calendar.getYear()+"."+calendar.getMonth() );
		}
	}
}	  
//need new actionListener