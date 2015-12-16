package Calendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

public class SpecialDay {
	private String specialdayName;
	public SpecialDay(String specialdayName) {
		this.specialdayName = specialdayName;
	}
	public String getSpecialdayName() {
		return specialdayName;
	}

	public void setSpecialdayName(String specialdayName) {
		this.specialdayName = specialdayName;
	}
	public void writeToFile(String s) throws IOException {
		Gson g=new Gson();
        String Content = g.toJson(this);// 要写入的文本
        File file = new File("/Users/hengyuliang/Desktop/"+s+"SpecialDay.txt");// 要写入的文本文件
        try{
	        if (!file.exists()) {// 如果文件不存在，则创建该文件
	            file.createNewFile();
	        }
	        FileWriter writer = new FileWriter(file);// 获取该文件的输出流
	        writer.write(Content);// 写内容
	        writer.flush();// 清空缓冲区，立即将输出流里的内容写到文件里
	        writer.close();// 关闭输出流，施放资源
	        }catch(Exception e){}
    }
	public static SpecialDay readFromFile(String s) throws FileNotFoundException, IOException {
        File file = new File("/Users/hengyuliang/Desktop/"+s+"SpecialDay.txt");// 指定要读取的文件
        FileReader reader = new FileReader(file);// 获取该文件的输入流
        char[] bb = new char[1024];// 用来保存每次读取到的字符
        String str = "";// 用来将每次读取到的字符拼接，当然使用StringBuffer类更好
        int n;// 每次读取到的字符长度
        while ((n = reader.read(bb)) != -1) {
            str += new String(bb, 0, n);
        }
        reader.close();// 关闭输入流，释放连接
        Gson g=new Gson();
        return g.fromJson(str,SpecialDay.class);
    }
}
