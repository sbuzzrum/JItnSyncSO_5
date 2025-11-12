import xBaseJ.*;
import java.io.*;


public class testread 
{
	static DBF aDBF;
	static Field f;
	
	static int i, j;
	public static void main(String arg[]) throws Exception
	{
		
		if (arg.length != 1)
			throw new Exception("missing DBF file name as argument");
		aDBF = new DBF(arg[0]);
		
		System.out.println("Name " + aDBF.getName());
		
		for (i = 1; i <= aDBF.getFieldCount(); i++)
		{
			f =  aDBF.getField(i);
			System.out.println(f.getName() + " " + f.getType() + " " + f.getLength() + " " + f.getDecimalPositionCount());
		}
		
		
		for (i = 1; i <= aDBF.getRecordCount(); i++)
		{
			System.out.println("Will read " + i + " of " +  aDBF.getRecordCount());
			aDBF.gotoRecord(i);
			for (j = 1; j <= aDBF.getFieldCount(); j++)
			{
				f =  aDBF.getField(j);
				System.out.println(f.getName() + "(" + f.get() + ")");
			}
		}
		
		
		return;
	}
	
	
}
