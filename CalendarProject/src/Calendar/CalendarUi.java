package Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
		
	}
}
