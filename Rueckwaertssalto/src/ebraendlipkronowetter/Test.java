package ebraendlipkronowetter;

public class Test {

	public static void main(String[] args) {
//		decide(args);
		testERD();
	}
	public static void printHelp(){
		System.out.println("usage -h Host -u username -p password -d database -rm -erd");
		System.out.println("erd not implemented");
	}
	public static void testERD(){
		new erdGenerator().genINI();
	}
	public static void decide(String[] args){
		Collecting a = new Collecting();
		String host=null, db=null, uname=null, pwd=null;
		boolean rm = false, erd=false;
		
		if(args.length < 9){
			printHelp();
		}else{
			for(int i = 0; i<args.length;i++){
				switch(args[i].charAt(1)){
				case 'h':
					host = args[i+1];
					break;
				case 'u':
					uname = args[i+1];
					break;
				case 'p':
					pwd = args[i+1];
					break;
				case 'd':
					db = args[i+1];
					break;
				case 'r':
					rm = true;
					break;
				case 'e':
					erd = true;
					break;
				}
			}
		}
		if(rm){
			System.out.println("File saved to "+a.autogenRM(host,db,uname,pwd));
		}
		if(erd){
			System.err.println("not implemented yet");
		}
	}
}
