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
		String[] tbn = mdc.getTabNames();
		for(int i = 0; i < tbn.length; i++){
			mdc.genRMcolumns(tbn[i]);
			hfw.addTableHTML(tbn[i], mdc.fieldPFK);
		}
		return hfw.genHTML();
	}
	
	
}
