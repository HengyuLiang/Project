package Calendar;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CalendarUi extends JFrame{
	JLabel lableDay[] = new JLabel[42];
	JTextField showDate;
	JButton weekName[] = new JButton[7];
	String name[] = {"Sun","Mon","Tue","Wen","Thr","Fri","Sat"};
	JButton nextMonth, previousMonth,nextYear,previousYear,save, event;
	JTextArea message;
	JMenuBar jmb;
	JMenu File, Search;
	JMenuItem Open,Save, Year, Month;
	int year = 2015, month = 12;
	CalendarData cd;
	
	public CalendarUi(){
		Container c = getContentPane();
		setSize(800,600);
		setTitle("MyCalendar");
		cd = new CalendarData();
		cd.setYear(year);
		cd.setMonth(month);  //set initial data;

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
		showDate=new JTextField(cd.getYear()+"."+cd.getMonth());
		showDate.setHorizontalAlignment(JTextField.CENTER);
		JNorth.add(showDate,gbcn);
		gbcn.gridx=12;
		gbcn.gridy=0;
		gbcn.gridwidth=1;
		gbcn.gridheight=1;
		gbcn.weightx=0;
		gbcn.weighty=0;
	}
}
