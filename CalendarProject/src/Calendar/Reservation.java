package Calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

<<<<<<< HEAD
import com.google.gson.Gson;
=======
public class Reservation 
{
	private String createdFor;
	private String startTime;
	private String endTime;
	private String reservationResourcetype;
	private String resourceLocation;
	private Date resourceDate;
>>>>>>> branch 'master' of https://github.com/HengyuLiang/Project.git

<<<<<<< HEAD
public class Reservation {
	private String reservationId;
	private String reservationName;
	private String reservationDiscription;
	private Date reservationDate;
	private String startTime, endTime;
	public Reservation(String reservationId, String reservationName, 
			String reservationDiscription, Date reservationDate,
			String startTime, String endTime) {
		this.reservationId = reservationId;
		this.reservationName = reservationName;
		this.reservationDiscription = reservationDiscription;
		this.reservationDate = reservationDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
=======
	public Reservation(String createdFor, String reservationResourcetype, String resourceLocation, Date resourceDate, String startTime, String endTime) {
		this.createdFor = createdFor;
		this.reservationResourcetype = reservationResourcetype;
		this.resourceLocation = resourceLocation;
		this.resourceDate = resourceDate;
		this.startTime=startTime;
		this.endTime=endTime;
>>>>>>> branch 'master' of https://github.com/HengyuLiang/Project.git
	}


	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationDiscription() {
		return reservationDiscription;
	}

	public void setReservationDiscription(String reservationDiscription) {
		this.reservationDiscription = reservationDiscription;
	}

<<<<<<< HEAD
	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void writeToFile(String s) throws IOException {
		Gson g=new Gson();
        String Content = g.toJson(this);// 要写入的文本
        File file = new File("/Users/hengyuliang/Desktop"+s);// 要写入的文本文件
        if (!file.exists()) {// 如果文件不存在，则创建该文件
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);// 获取该文件的输出流
        writer.write(Content);// 写内容
        writer.flush();// 清空缓冲区，立即将输出流里的内容写到文件里
        writer.close();// 关闭输出流，施放资源
    }
	public void readFromFile() throws FileNotFoundException, IOException {
        File file = new File("/Users/hengyuliang/Desktop");// 指定要读取的文件
        FileReader reader = new FileReader(file);// 获取该文件的输入流
        char[] bb = new char[1024];// 用来保存每次读取到的字符
        String str = "";// 用来将每次读取到的字符拼接，当然使用StringBuffer类更好
        int n;// 每次读取到的字符长度
        while ((n = reader.read(bb)) != -1) {
            str += new String(bb, 0, n);
        }
        reader.close();// 关闭输入流，释放连接
    }
=======
	public String getCreatedFor() {
		return createdFor;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}
>>>>>>> branch 'master' of https://github.com/HengyuLiang/Project.git
}
