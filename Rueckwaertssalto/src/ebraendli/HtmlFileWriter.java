package ebraendli;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 */

/**
 * @author Erik
 *
 */
public class HtmlFileWriter {
	private String writeS="";

	public void addTableHTML(String tablename, HashMap<String,String[]> columns){
		writeS +="<table border='1'><tr><th>"+tablename+"</th>";
		Iterator<String> i = columns.keySet().iterator();
		while(i.hasNext()){
			String tmp = i.next();
			if(columns.get(tmp)[0].contains("PK")){
				writeS+="<td><u>"+tmp+"</u></td>";
			}else{
//				System.out.println(columns.get(tmp)[0].contains("FK"));
				if(columns.get(tmp)[0].equals("FK")){
					writeS+="<td><i>"+tmp+": "+columns.get(tmp)[1]+"."+columns.get(tmp)[2]+"</i></td>";
//					System.out.println(columns.get(tmp)[0]+columns.get(tmp)[1]+columns.get(tmp)[2]);
//					System.err.println(writeS);
				}else{
					writeS+="<td>"+tmp+"</td>";
				}
			}
		}
		writeS+="</tr></table><br />";

	}
	public String genHTML(){

		File file = new File("rm.html");
			try {
				if(file.exists()==false)
					file.createNewFile();
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				//Modify the String to HTML syntax
				writeS="<html>"+writeS+"</html>";
				bw.write(writeS);
				bw.close();				
			} catch (IOException e) {
				System.err.println("Error on writing to file see to following exception");
				e.printStackTrace();
			}
		return file.getAbsolutePath();
	}


}
