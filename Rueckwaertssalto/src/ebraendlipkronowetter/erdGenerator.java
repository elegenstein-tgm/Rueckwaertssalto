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
	private String parser ="\\Propel\\Generator\\Reverse\\MysqlSchemaParser";
	
	public erdGenerator() {
		dbname = "timetool";
		user ="root";
		pwd ="root";
		host="127.00.00.1";
	}
	/**
	 * Not useable due to formating errors/issues
	 * @date 17-02-2015
	 */
	private void genYAML() {
		String s = "";
		s+="propel:\n\t"+"database:\n\t\t"+"connections:\n\t\t\t"+"bookstore:\n\t\t\t\t"+"adapter: "+dbType+"\n\t\t\t\t"+"classname: Propel\\Runtime\\Connection\\ConnectionWrapper\n\t\t\t\t"+"dsn: \"mysql:host="+host+";dbname="+dbname+"\"\n\t\t\t\t"+"user: "+user+"\n\t\t\t\t"+"password: "+pwd+"\n\t\t\t\t"+"attributes:\n";
		s+="\t\treverse:\n"+"\t\t\tconnection: bookstore\n";
		if(dbType.equals("mysql"))
			s+="\t\t\tparserClass: Propel\\Generator\\Reverse\\MysqlSchemaParser";
		else
			s+="\t\t\tparserClass: Propel\\Generator\\Reverse\\PgsqlSchemaParser";


		try {
			File aaa = new File("Propel.yaml");
			if(!aaa.exists())
				aaa.createNewFile();
			BufferedWriter bf = new BufferedWriter(new FileWriter(aaa));
			bf.write(s);
			bf.close();
//			Runtime.getRuntime().exec("propel reverse bookstore");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void genINI(){
		String s = "";
		s+="[propel]\n;\n; Database section;\ndatabase.connections.bookstore.adapter    = "+dbType+"\ndatabase.connections.bookstore.classname  = Propel\\Runtime\\Connection\\ConnectionWrapper\ndatabase.connections.bookstore.dsn        = mysql:host="+host+";dbname="+dbname+"\ndatabase.connections.bookstore.user       = "+user+"\ndatabase.connections.bookstore.password   = "+pwd+"\ndatabase.connections.bookstore.attributes =\n;\n; Runtime section\n;\n";
		s+="runtime.defaultConnection = bookstore\nruntime.connections[0]    = bookstore\n;\n; Reversetempl\n;\n";
		s+="reverse.connection = bookstore\nreverse.parserClass = "+parser+"\n;;; CONFIGURE END ;;;";
		
		try {
			File aaa = new File("generated-stuff/propel.ini");
			if(!new File("generated-suff").exists())
				if(!new File("generated-stuff").mkdir())
					System.err.println("have no permission to create dir");
			if(!aaa.exists())
				aaa.createNewFile();
			BufferedWriter bf = new BufferedWriter(new FileWriter(aaa));
			bf.write(s);
			bf.close();
			System.out.println("Please modify values if needed in "+aaa.getAbsolutePath()+"\n DO NOT MODIFY CONNECTION NAMES");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void genERD(){
		try {
			Runtime.getRuntime().exec("propel reverse --config-dir=\"generated-stuff\" --output-dir=\"generated-stuff\" bookstore");
			Runtime.getRuntime().exec("propel graphviz --config-dir=\"generated-stuff\" --output-dir=\"generated-stuff\" --schema-dir=\"generated-stuff\"");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
