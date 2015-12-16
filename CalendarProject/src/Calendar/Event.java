package Calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.google.gson.Gson;

public class Event {
	private String eventName;
	private String location;
	private String eventDiscription;
	private Date eventDate;
	private String startTime, endTime;
	public Event(String eventName, String location,
				 String eventDiscription, Date eventDate,
				 String startTime, String endTime) {
		this.eventName = eventName;
		this.eventDiscription = eventDiscription;
		this.location=location;
		this.eventDate = eventDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getEventName() {
		return eventName;
	}
	public String getEventDiscription() {
		return eventDiscription;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
<<<<<<< HEAD
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public void setEventDiscription(String eventDiscription) {
		this.eventDiscription = eventDiscription;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
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

	public String getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "Event{" +
				" eventName='" + eventName + '\'' +
				", eventDiscription='" + eventDiscription + '\'' +
				", eventDate=" + eventDate +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				'}';
	}
>>>>>>> branch 'master' of https://github.com/HengyuLiang/Project.git
}
