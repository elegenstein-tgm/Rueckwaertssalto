package ebraendlipkronowetter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 */

/**
 * @author Erik
 *
 */
public class Collecting {
	
	private MetadataCollector mdc = new MetadataCollector();
	private HtmlFileWriter hfw = new HtmlFileWriter();
	
	public String autogenRM(String host, String dbname, String user, String pwd){
		mdc.createConnection(host, user, user, dbname);
		ArrayList<String> tbn = mdc.getTabNames();
		if(tbn==null){
			System.err.println("Cant get tablenames");
			System.exit(1);
		}
		for(int i =0; i < tbn.size(); i++){
			mdc.genRMcolumns(tbn.get(i));
			hfw.addTableHTML(tbn.get(i), mdc.fieldPFK);
		}
		return hfw.genHTML();
	}
}
