import java.util.HashMap;
import java.util.HashSet;
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
	
	public void addTable(String tablename, HashMap<String,String[]> columns){
		writeS +="<table border='1'><th>"+tablename+"</th>";
		Iterator<String> i = columns.keySet().iterator();
		while(i.hasNext()){
			String tmp = i.next();
			if(columns.get(tmp)[0].equals("PK")){
				writeS+="<td><u>"+tmp+"</u></td>";
			}else{
				writeS+="<td><i>"+columns.get(tmp)[1]+"."+columns.get(tmp)[2]+":"+tmp+"</i></td>";
			}
		}
		writeS+="</table><br />";
		
	}
}
