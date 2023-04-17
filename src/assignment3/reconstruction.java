package assignment3;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class reconstruction 
{
	
	public static void main(String[] args) throws IOException
	{
		BufferedInputStream a = new BufferedInputStream(new FileInputStream("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\src\\share1.bmp"));
		BufferedInputStream b = new BufferedInputStream(new FileInputStream("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\src\\share2.bmp"));
	
		
//		BufferedImage share1 = ImageIO.read(a);
//		BufferedImage share2 = ImageIO.read(b);
//		SSS.imagepreview(share1);
//		SSS.imagepreview(share2);
//		
		byte[] a_byte = new byte[a.available()];
		byte[] b_byte = new byte[b.available()];
		
		a.read(a_byte);
		b.read(b_byte);
		System.out.println(a_byte.length);
		System.out.println(b_byte.length);
//		
//		ByteArrayInputStream in1 = new ByteArrayInputStream(b_byte);
//		System.out.println(in1.available());
//		BufferedImage share1 = ImageIO.read(in1);
//		SSS.imagepreview(share1);
		byte[] temp = new byte[a_byte.length];
		
		for(int i = 0; i<a_byte.length; i++)
		{
			temp[i] = (byte) ((a_byte[i] * -2 + b_byte[i] * 2) % 251);
		}
		
		System.out.println(temp[150]);
		
	}
}
