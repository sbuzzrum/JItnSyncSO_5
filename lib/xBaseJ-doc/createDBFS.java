import java.io.*;
import xBaseJ.*;

public class createDBFS 
{
	
	static DBF aDBF;
	static CharField afld , bfld, cfld;
	
	public static void main(String arg[]) throws xBaseJException, IOException
	{
		aDBF = new DBF("DBFs.DBF", true);
		afld = new CharField("id", 16);
		bfld = new CharField("name", 254);
		cfld = new CharField("relation", 16);
		
		
		aDBF.addField(afld);
		aDBF.addField(bfld);
		aDBF.addField(cfld);
		
		System.out.println("# of records is " + aDBF.getRecordCount());
		
		System.exit(0);
	}
	
}
