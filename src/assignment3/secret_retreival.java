package assignment3;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

//gets secret with any two shares given
public class secret_retreival
{
	public static void main(String[] args) throws IOException
	{
		//loads image 
		Imagedata a  = new Imagedata("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share1.bmp");
		byte[] header = Imagedata.header;
		byte[] pixeldata = Imagedata.nonheader;
		
		Imagedata b  = new Imagedata("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share2.bmp");
		byte[] header1 = Imagedata.header;
		byte[] pixeldata2 = Imagedata.nonheader;
		
		//temp to combine all the data header+pixeldata
		byte[] allimagedata = new byte[pixeldata2.length+54];
		
		//copies header first
		System.arraycopy(Imagedata.header, 0, allimagedata, 0, 54);
		System.arraycopy(lagrange(pixeldata, pixeldata2, 2,3), 0, allimagedata, 54, lagrange(pixeldata, pixeldata2,2,3).length); // copies the lagrange returned byte for the pixeldata
		
		ByteArrayInputStream in1 = new ByteArrayInputStream(allimagedata);
		BufferedImage share1 = ImageIO.read(in1); //displaysthe image
		Imagedata.imagepreview(share1);
 	}
	
	//claculates the lagrange.
	//takes share numbers to calculate the X coeficient value
	public static byte[] lagrange(byte[] a, byte[] b, int sharenum1, int sharenum2)
	{
		byte[] temp = new byte[a.length];
		
		for(int i = 0; i<a.length; i++)
		{
			temp[i] = (byte) ((a[i] * (-sharenum2/(sharenum1-sharenum2)) + b[i] * (-sharenum1/(sharenum2-sharenum1))) % 251);
		}
		return temp;
	}
}
