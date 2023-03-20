package assignment2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class textwritter 
{
	public static void resultwritter(byte[] ECB, Double IC_ECB,  byte[] CBC, Double IC_CBC, byte[] OFB, Double IC_OFB, String key) throws IOException
	{
		FileWriter fw = new FileWriter("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\src\\assignment2\\Result.txt",  false);
		PrintWriter out = new PrintWriter(fw);
		
		
		out.println("====================================");
		out.println(" ");
		out.println(" ");
		out.println("used key for all of the data: " + Arrays.toString(key.getBytes()));
		out.println(" ");
		out.println(" ");
		
		out.println("====================================");
		out.println("Selected Mode : ECB");
		out.println(" ");
		out.println("Cipher text in ASCII: ");
		out.println(Arrays.toString(ECB));
		out.println(" ");
		out.println(" ");
		out.println("IC value for the above cipher text: " + IC_ECB);
		
		out.println(" ");
		out.println(" ");
		out.println(" ");
		out.println(" ");
		
		out.println("====================================");
		out.println("Selected Mode : CBC");
		out.println(" ");
		out.println("Cipher text in ASCII: ");
		out.println(Arrays.toString(CBC));
		out.println(" ");
		out.println(" ");
		out.println("IC value for the above cipher text: " + IC_CBC);
		
		out.println(" ");
		out.println(" ");
		out.println(" ");
		out.println(" ");
		
		out.println("====================================");
		out.println("Selected Mode : OFB");
		out.println(" ");
		out.println("Cipher text in ASCII: ");
		out.println(Arrays.toString(OFB));
		out.println(" ");
		out.println(" ");
		out.println("IC value for the above cipher text: " + IC_OFB);
		
		out.close();
		fw.close();
	}
}
