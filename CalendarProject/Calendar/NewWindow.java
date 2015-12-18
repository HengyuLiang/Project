package Calendar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class NewWindow extends JFrame{
	int year,month,day;
	private final CalendarData calendarData;
	private final CalendarUi parent;
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT= new SimpleDateFormat("yyyy-MM-dd");
	public NewWindow(int year, int month, int day,CalendarData calendarData,CalendarUi parent){
		this.year = year;
		this.month = month;
		this.day = day;
		this.calendarData=calendarData;
		this.parent=parent;
		Container c = getContentPane();
		setSize(400,300);
		setLocation(800, 0);
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		
		//create the holiday JPanel
		JPanel js=new JPanel();
		js.setLayout(new BorderLayout());
		JButton loadS = new JButton("Load");

		JButton saveS=new JButton("Save");

		JButton cancelS=new JButton("Cancel");
		cancelS.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			
			}
		});
		JPanel jbs=new JPanel(new FlowLayout());
		jbs.add(loadS);
		jbs.add(saveS);

		//jbs.add(deleteS,BorderLayout.CENTER);
		jbs.add(cancelS);
		js.add(jbs,BorderLayout.SOUTH);
		JPanel details=new JPanel();
		details.setLayout(new GridLayout(2,2));

		JTextField hDate=new JTextField(""+year+"-"+month+"-"+day);
		JTextField hName=new JTextField("");
		details.add(new JLabel("Date :")); details.add(hDate);
		details.add(new JLabel("Name :")); details.add(hName);

		js.add(details,BorderLayout.CENTER);
		tab.addTab("Holiday", js);
		saveS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					String content = hName.getText();
					Date date=getDate(hDate.getText());
					Holiday s=new Holiday(content,date);
					calendarData.addHolidayAndSave(s);
					parent.updateCalendar();

				}
		      catch(Exception ee){}
				dispose();
			}
		});
		loadS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(NewWindow.this);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " +
							chooser.getSelectedFile().getName());
				}
				File f=chooser.getSelectedFile();
				calendarData.loadHolidaysFromFile(f);
				parent.updateCalendar();
				dispose();
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

		JPanel jbe=new JPanel(new FlowLayout());
		jbe.add(saveE);
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

		JTextArea eventDetails= new JTextArea();
		JScrollPane jeventDetails=new JScrollPane(eventDetails);
		eventDetails.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		eventDetails.setLineWrap(true); 
		eventDetails.setWrapStyleWord(true);
		eventDetails.setFont(new Font("Arial",1,20));

		JTextArea eventLocation= new JTextArea();
		JScrollPane jeventLocation=new JScrollPane(eventLocation);
		eventLocation.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		eventLocation.setLineWrap(true); 
		eventLocation.setWrapStyleWord(true);
		eventLocation.setFont(new Font("Arial",1,20));

		JTextArea eventStartTime= new JTextArea();
		JScrollPane jeventStartTime=new JScrollPane(eventStartTime);
		eventStartTime.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		eventStartTime.setLineWrap(true); 
		eventStartTime.setWrapStyleWord(true);
		eventStartTime.setFont(new Font("Arial",1,20));

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
					Event et=new Event(getDate(""+year+"-"+month+"-"+day),name,location,details,startTime);
					calendarData.addEventAndSave(et);
					parent.updateCalendar();

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
		JPanel jbr=new JPanel(new FlowLayout());
		jbr.add(saveR,BorderLayout.EAST);
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

        JTextArea rStartTime= new JTextArea();
        JScrollPane jrStartTime=new JScrollPane(rStartTime);
        rStartTime.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
        rStartTime.setLineWrap(true); 
        rStartTime.setWrapStyleWord(true);
        rStartTime.setFont(new Font("Arial",1,20));

        JTextArea rName= new JTextArea();
        JScrollPane jrName=new JScrollPane(rName);
        rName.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
        rName.setLineWrap(true); 
        rName.setWrapStyleWord(true);
        rName.setFont(new Font("Arial",1,20));

        JTextArea rDetails= new JTextArea();
        JScrollPane jrDetails=new JScrollPane(rDetails);
        rDetails.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
        rDetails.setLineWrap(true); 
        rDetails.setWrapStyleWord(true);
        rDetails.setFont(new Font("Arial",1,20));

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
                    Reservation rv=new Reservation(getDate(""+year+"-"+month+"-"+day),name,details,location,startTime);
                    calendarData.addReservationAndSave(rv);
					parent.updateCalendar();

				}
                catch(Exception ee){}
                dispose();
			}
		});

		Collection<CalendarEvent> calendarEvents=calendarData.getCalendarDetails().getAllCalendarEvents();
		String[][] dataRows=new String[calendarEvents.size()][2];
		int i=0;

		DefaultTableModel defaultTableModel=new DefaultTableModel();
		defaultTableModel.addColumn("Date");
		defaultTableModel.addColumn("Type");
		defaultTableModel.addColumn("Name");
		defaultTableModel.addColumn("id");
		JTable jTable=new JTable(defaultTableModel);
		for(CalendarEvent calendarEvent:calendarEvents){
			defaultTableModel.addRow(new String[]{""+new java.sql.Date(calendarEvent.getEventDate().getTime()).toString()
					, calendarEvent instanceof Holiday ? "Holiday" : (calendarEvent instanceof Event ? "Event" : "Reservation")
					, calendarEvent.getName(),calendarEvent.getId().toString()});
		}
		JScrollPane list=new JScrollPane(jTable);

		JPanel reviewPanel=new JPanel();
		reviewPanel.setLayout(new BorderLayout());
		JButton deleteRP=new JButton("Delete");
		JButton canceRP=new JButton("Close");
		JPanel rPbP=new JPanel(new FlowLayout());
		rPbP.add(deleteRP);
		rPbP.add(canceRP);
		reviewPanel.add(rPbP,BorderLayout.SOUTH);
		reviewPanel.add(list,BorderLayout.CENTER);
		tab.add(reviewPanel,"Review List");

		deleteRP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				int row=jTable.getSelectedRow();
				Long id=Long.valueOf((String)jTable.getValueAt(row,3));
				calendarData.deleteCalendarEvent(id);
				defaultTableModel.removeRow(row);
				AbstractTableModel contactTableModel = (AbstractTableModel) jTable.getModel();
				contactTableModel.fireTableRowsDeleted(row,row+1);
				jTable.repaint();
				repaint();
				parent.updateCalendar();

			}
		});
		canceRP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				dispose();
			}
		});

		setVisible(true);
	}

	private Date getDate(String dateValue){
		try{
			return SIMPLE_DATE_FORMAT.parse(dateValue);
		}catch(ParseException parse){
			return null;
		}
	}
}