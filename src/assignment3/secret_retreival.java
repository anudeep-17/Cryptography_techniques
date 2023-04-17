package assignment3;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class secret_retreival
{
	
	public static void main(String[] args) throws IOException
	{
		Imagedata a  = new Imagedata("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share1.bmp");
		byte[] header = Imagedata.header;
		byte[] pixeldata = Imagedata.nonheader;
		
		Imagedata b  = new Imagedata("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share2.bmp");
		byte[] header1 = Imagedata.header;
		byte[] pixeldata2 = Imagedata.nonheader;
		
		byte[] allimagedata = new byte[pixeldata2.length+54];
		
		System.arraycopy(Imagedata.header, 0, allimagedata, 0, 54);
		System.arraycopy(lagrange(pixeldata, pixeldata2), 0, allimagedata, 54, lagrange(pixeldata, pixeldata2).length);
		
		ByteArrayInputStream in1 = new ByteArrayInputStream(allimagedata);
		BufferedImage share1 = ImageIO.read(in1);
		System.out.println(share1);
		Imagedata.imagepreview(share1);
 	}
	
	public static byte[] lagrange(byte[] a, byte[] b)
	{
		byte[] temp = new byte[a.length];
		
		for(int i = 0; i<a.length; i++)
		{
			temp[i] = (byte) ((a[i] * 3 + b[i] * -2) % 251);
		}
		return temp;
	}
}
