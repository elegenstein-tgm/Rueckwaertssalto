package ebraendlipkronowetter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class erdGenerator {
	private String dbname ="";
	private String user="";
	private String pwd="";
	private String dbType="mysql";
	private String host=""; 
	
	public void genYAML() {
		String s = "";
		s+="propel:\n"+"database:\n"+"connections:\n"+"bookstore:\n"+"adapter: "+dbType+"\n"+"classname: Propel\\Runtime\\Connection\\ConnectionWrapper\n"+"dsn: \"mysql:host="+host+";dbname="+dbname+"\"\n"+"user: "+user+"\n"+"password: "+pwd+"\n"+"attributes:\n";
		s+="reverse:\n"+"connection: bookstore\n";
		if(dbType.equals("mysql"))
		s+="parserClass: \\Propel\\Generator\\Reverse\\MysqlSchemaParser";
		else
			s+="parserClass: \\Propel\\Generator\\Reverse\\PgsqlSchemaParser";
		
		
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter(new File("Propel.yaml")));
			bf.write(s);
			Runtime.getRuntime().exec("propel reverse bookstore");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
