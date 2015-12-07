package Calendar;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewWindow extends JFrame{
	File fileHoliday,fileEventName,fileEventLocation,fileEventContent,fileResName,fileResLocation,fileResContent;
	Hashtable tableHoliday,tableEventName,tableEventLocation,tableEventContent,tableResName,tableResLocation,tableResContent;
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
		saveS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
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
					out.close();
		        }
		      catch(Exception ee){
		        }
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
		JPanel jbe=new JPanel();
		jbe.add(saveE,BorderLayout.EAST);
		jbe.add(deleteE, BorderLayout.CENTER);
		jbe.add(cancelE, BorderLayout.WEST);
		je.add(jbe,BorderLayout.SOUTH);
		JPanel detaile=new JPanel();
		detaile.setLayout(new GridBagLayout());
		GridBagConstraints gbce=new GridBagConstraints();
		gbce.fill=GridBagConstraints.BOTH;
		gbce.gridx=0;
		gbce.gridy=0;
		gbce.gridwidth=1;
		gbce.gridheight=1;
		gbce.weightx=0;
		gbce.weighty=0;
		JLabel nameel=new JLabel("Name");
		detaile.add(nameel, gbce);
		gbce.gridx=1;
		gbce.gridy=0;
		gbce.gridwidth=10;
		gbce.gridheight=2;
		gbce.weightx=1;
		gbce.weighty=1;
		JTextArea nameea=new JTextArea();
		JScrollPane jnamee=new JScrollPane(nameea);
		nameea.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		nameea.setLineWrap(true); 
		nameea.setWrapStyleWord(true);
		nameea.setFont(new Font("Arial",1,20));
		detaile.add(jnamee,gbce);
//		gbce.gridx=0;
//		gbce.gridy=2;
//		gbce.gridwidth=1;
//		gbce.gridheight=1;
//		gbce.weightx=0;
//		gbce.weighty=0;
//		JLabel dateel=new JLabel("Date");
//		detaile.add(dateel, gbce);
//		gbce.gridx=1;
//		gbce.gridy=2;
//		gbce.gridwidth=10;
//		gbce.gridheight=2;
//		gbce.weightx=1;
//		gbce.weighty=1;
//		JTextArea dateea=new JTextArea();
//		JScrollPane jdatee=new JScrollPane(dateea);
//		dateea.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
//		dateea.setLineWrap(true); 
//		dateea.setWrapStyleWord(true);
//		dateea.setFont(new Font("Arial",1,20));
//		detaile.add(jdatee,gbce);
		gbce.gridx=0;
		gbce.gridy=4;
		gbce.gridwidth=1;
		gbce.gridheight=1;
		gbce.weightx=0;
		gbce.weighty=0;
		JLabel locationel=new JLabel("Location");
		detaile.add(locationel, gbce);
		gbce.gridx=1;
		gbce.gridy=4;
		gbce.gridwidth=10;
		gbce.gridheight=2;
		gbce.weightx=1;
		gbce.weighty=1;
		JTextArea locationea=new JTextArea();
		JScrollPane jlocatione=new JScrollPane(locationea);
		locationea.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		locationea.setLineWrap(true); 
		locationea.setWrapStyleWord(true);
		locationea.setFont(new Font("Arial",1,20));
		detaile.add(jlocatione,gbce);
		gbce.gridx=0;
		gbce.gridy=6;
		gbce.gridwidth=1;
		gbce.gridheight=1;
		gbce.weightx=0;
		gbce.weighty=0;
		JLabel detailel=new JLabel("Detail");
		detaile.add(detailel, gbce);
		gbce.gridx=1;
		gbce.gridy=6;
		gbce.gridwidth=10;
		gbce.gridheight=2;
		gbce.weightx=1;
		gbce.weighty=1;
		JTextArea detailea=new JTextArea();
		JScrollPane jdetaile=new JScrollPane(detailea);
		detailea.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		detailea.setLineWrap(true); 
		detailea.setWrapStyleWord(true);
		detailea.setFont(new Font("Arial",1,20));
		detaile.add(jdetaile,gbce);
		je.add(detaile,BorderLayout.CENTER);
		tab.addTab("Event", je);
		saveE.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					String contentName = nameea.getText();
					String contentLocation = locationea.getText();
					String contentDetail = detailea.getText();
					String key = ""+year+""+month+""+day;
					//save EventName
					FileInputStream   inOneName=new FileInputStream(fileEventName);
					ObjectInputStream inTwoName=new ObjectInputStream(inOneName);
					tableEventName=(Hashtable)inTwoName.readObject();
					inOneName.close();
					inTwoName.close();
					tableEventName.put(key,contentName); 
					FileOutputStream outName=new FileOutputStream(fileEventName);
					ObjectOutputStream objectOut=new ObjectOutputStream(outName);
					objectOut.writeObject(tableEventName);
					objectOut.close();
					outName.close();
					
					//Save eventLocation
					FileInputStream   inOneLocation=new FileInputStream(fileEventLocation);
					ObjectInputStream inTwoLocation=new ObjectInputStream(inOneLocation);
					tableEventLocation=(Hashtable)inTwoLocation.readObject();
					inOneLocation.close();
					inTwoLocation.close();
					tableEventLocation.put(key,contentLocation);                                  
					FileOutputStream outLocation=new FileOutputStream(fileEventLocation);
					ObjectOutputStream objectOutLocation=new ObjectOutputStream(outLocation);
					objectOutLocation.writeObject(tableEventLocation);
					objectOutLocation.close();
					outLocation.close();
					
					//Save eventDetail
					FileInputStream   inOneDetail=new FileInputStream(fileEventContent);
					ObjectInputStream inTwoDetail=new ObjectInputStream(inOneDetail);
					tableEventContent=(Hashtable)inTwoDetail.readObject();
					inOneDetail.close();
					inTwoDetail.close();
					tableEventContent.put(key,contentDetail);                                  
					FileOutputStream outDetail=new FileOutputStream(fileEventContent);
					ObjectOutputStream objectOutDetail=new ObjectOutputStream(outDetail);
					objectOutDetail.writeObject(tableEventContent);
					objectOutDetail.close();
					outDetail.close();
		        }
		      catch(Exception ee){
		        }
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
		JPanel jbr=new JPanel();
		jbr.add(saveR,BorderLayout.EAST);
		jbr.add(deleteR, BorderLayout.CENTER);
		jbr.add(cancelR, BorderLayout.WEST);
		jr.add(jbr,BorderLayout.SOUTH);
		JPanel detailr=new JPanel();
		detailr.setLayout(new GridBagLayout());
		GridBagConstraints gbcr=new GridBagConstraints();
		gbcr.fill=GridBagConstraints.BOTH;
		gbcr.gridx=0;
		gbcr.gridy=0;
		gbcr.gridwidth=1;
		gbcr.gridheight=1;
		gbcr.weightx=0;
		gbcr.weighty=0;
		JLabel namerl=new JLabel("Name");
		detailr.add(namerl, gbcr);
		gbcr.gridx=1;
		gbcr.gridy=0;
		gbcr.gridwidth=10;
		gbcr.gridheight=2;
		gbcr.weightx=1;
		gbcr.weighty=1;
		JTextArea namera=new JTextArea();
		JScrollPane jnamer=new JScrollPane(namera);
		namera.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		namera.setLineWrap(true); 
		namera.setWrapStyleWord(true);
		namera.setFont(new Font("Arial",1,20));
		detailr.add(jnamer,gbcr);
//		gbcr.gridx=0;
//		gbcr.gridy=2;
//		gbcr.gridwidth=1;
//		gbcr.gridheight=1;
//		gbcr.weightx=0;
//		gbcr.weighty=0;
//		JLabel daterl=new JLabel("Date");
//		detailr.add(daterl, gbcr);
//		gbcr.gridx=1;
//		gbcr.gridy=2;
//		gbcr.gridwidth=10;
//		gbcr.gridheight=2;
//		gbcr.weightx=1;
//		gbcr.weighty=1;
//		JTextArea datera=new JTextArea();
//		JScrollPane jdater=new JScrollPane(datera);
//		datera.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
//		datera.setLineWrap(true); 
//		datera.setWrapStyleWord(true);
//		datera.setFont(new Font("Arial",1,20));
//		detailr.add(jdater,gbcr);
		gbcr.gridx=0;
		gbcr.gridy=4;
		gbcr.gridwidth=1;
		gbcr.gridheight=1;
		gbcr.weightx=0;
		gbcr.weighty=0;
		JLabel locationrl=new JLabel("Location");
		detailr.add(locationrl, gbcr);
		gbcr.gridx=1;
		gbcr.gridy=4;
		gbcr.gridwidth=10;
		gbcr.gridheight=2;
		gbcr.weightx=1;
		gbcr.weighty=1;
		JTextArea locationra=new JTextArea();
		JScrollPane jlocationr=new JScrollPane(locationra);
		locationra.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		locationra.setLineWrap(true); 
		locationra.setWrapStyleWord(true);
		locationra.setFont(new Font("Arial",1,20));
		detailr.add(jlocationr,gbcr);
		gbcr.gridx=0;
		gbcr.gridy=6;
		gbcr.gridwidth=1;
		gbcr.gridheight=1;
		gbcr.weightx=0;
		gbcr.weighty=0;
		JLabel detailrl=new JLabel("Detail");
		detailr.add(detailrl, gbcr);
		gbcr.gridx=1;
		gbcr.gridy=6;
		gbcr.gridwidth=10;
		gbcr.gridheight=2;
		gbcr.weightx=1;
		gbcr.weighty=1;
		JTextArea detailra=new JTextArea();
		JScrollPane jdetailr=new JScrollPane(detailra);
		detailra.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		detailra.setLineWrap(true); 
		detailra.setWrapStyleWord(true);
		detailra.setFont(new Font("Arial",1,20));
		detailr.add(jdetailr,gbcr);
		jr.add(detailr,BorderLayout.CENTER);
		tab.addTab("Reservation", jr);
		c.add(tab,BorderLayout.CENTER);
		saveR.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					String contentName = namera.getText();
					String contentLocation = locationra.getText();
					String contentDetail = detailra.getText();
					String key = ""+year+""+month+""+day;
					//save EventName
					FileInputStream   inOneName=new FileInputStream(fileResName);
					ObjectInputStream inTwoName=new ObjectInputStream(inOneName);
					tableResName=(Hashtable)inTwoName.readObject();
					inOneName.close();
					inTwoName.close();
					tableResName.put(key,contentName); 
					FileOutputStream outName=new FileOutputStream(fileResName);
					ObjectOutputStream objectOut=new ObjectOutputStream(outName);
					objectOut.writeObject(tableResName);
					objectOut.close();
					outName.close();
					
					//Save eventLocation
					FileInputStream   inOneLocation=new FileInputStream(fileResLocation);
					ObjectInputStream inTwoLocation=new ObjectInputStream(inOneLocation);
					tableResLocation=(Hashtable)inTwoLocation.readObject();
					inOneLocation.close();
					inTwoLocation.close();
					tableResLocation.put(key,contentLocation);                                  
					FileOutputStream outLocation=new FileOutputStream(fileResLocation);
					ObjectOutputStream objectOutLocation=new ObjectOutputStream(outLocation);
					objectOutLocation.writeObject(tableResLocation);
					objectOutLocation.close();
					outLocation.close();
					
					//Save eventDetail
					FileInputStream   inOneDetail=new FileInputStream(fileResContent);
					ObjectInputStream inTwoDetail=new ObjectInputStream(inOneDetail);
					tableResContent=(Hashtable)inTwoDetail.readObject();
					inOneDetail.close();
					inTwoDetail.close();
					tableResContent.put(key,contentDetail);                                  
					FileOutputStream outDetail=new FileOutputStream(fileResContent);
					ObjectOutputStream objectOutDetail=new ObjectOutputStream(outDetail);
					objectOutDetail.writeObject(tableResContent);
					objectOutDetail.close();
					outDetail.close();
		        }
		      catch(Exception ee){
		        }
			}
		});
		setVisible(true);
	}
}