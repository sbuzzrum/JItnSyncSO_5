import java.awt.*;
import xBaseJ.*;

public class pack 
{
	static DBF aDBF;
	
	public static void main(String arg[]) throws Exception
	{
		
		if (arg.length < 0 )
			throw new Exception("missing DBF file name as argument");
		
		aDBF = new DBF(arg[0]);
		for (int i = 1; i<= aDBF.getRecordCount(); i++)
		{
      aDBF.read();
      if (aDBF.deleted()) System.out.println("Will remove record " + i);
		}
		if (arg.length > 1)
			aDBF.useIndex(arg[1]);
		System.out.println("Total records before pack " + aDBF.getRecordCount());
		aDBF.pack();
		System.out.println("Total records after pack " + aDBF.getRecordCount());
	}
	
}
