package assignment3;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Shares 
{
	// let, 
	// X = {1,2,3,4,5}
	// Secret being bytes of the image and since only two shares we need quadratic equation.
	// y = secret + bX mod 251.
	// let b = 11 and c = 7
	
	public static int b = 113;
	
	public static int shares = 5;
	public static int threshold = 2;
	
	public static byte[][] shares_5  = new byte[shares][];
	public static byte[][] share  = new byte[shares][];
	
	// y = (secret + 11x) mod 251;
	
	public static void sharer()
	{
		for(int i = 0; i<shares_5.length; i++)
		{
			byte term = (byte) ((byte) (i+1) * b);
			
			byte[] temp = new byte[SSS.pixelofimage.length];
	
			for(int j = 0; j < SSS.pixelofimage.length; j++)
			{
				temp[j] = (byte) ((byte)(SSS.pixelofimage[j] + term) % 251);
			}
			
//			System.out.println(temp.length);
			shares_5[i] = temp;
//			System.out.println(i +" "+shares_5[i].length);
		}	
	}
	
	public static void headadder()
	{
		for(int i = 0; i < shares_5.length; i++)
		{
			System.arraycopy(SSS.headofimage, 0, share[i], 0, 53);
			System.arraycopy(shares_5[i], 0, share[i], 54, shares_5[i].length);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		SSS test = new SSS("C:\\Users\\Owner\\Downloads\\kite-4922348__340.bmp");
		share  = new byte[shares][SSS.pixelofimage.length+54];
		sharer();
		headadder();
		
		ByteArrayInputStream in1 = new ByteArrayInputStream(share[0]);
		System.out.println(in1.available());
		BufferedImage share1 = ImageIO.read(in1);
		System.out.println(share1);
		SSS.imagepreview(share1);
		
//		File outputfile = new File("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\src\\share5.bmp");
//		ImageIO.write(share1, "bmp", outputfile);
		
		BufferedInputStream a = new BufferedInputStream(new FileInputStream("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\src\\share1.bmp"));
//		System.out.println(a.available());
//		BufferedImage share2 = ImageIO.read(a);
//		SSS.imagepreview(share2);
		
		BufferedInputStream b = new BufferedInputStream(new FileInputStream("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\src\\share2.bmp"));
//		System.out.println(a.available());
//		BufferedImage share3 = ImageIO.read(b);
//		SSS.imagepreview(share3);
		
		
		
		
	
	}
	
}
