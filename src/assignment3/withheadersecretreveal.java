package assignment3;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class withheadersecretreveal 
{
	public static void main(String[] args) throws IOException
	{
		//loads image data.
		Imagedata a  = new Imagedata("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share0_withheader.bmp");
		byte[] header = Imagedata.header;
		byte[] pixeldata = Imagedata.nonheader;
		
		Imagedata b  = new Imagedata("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share1_withheader.bmp");
		byte[] header1 = Imagedata.header;
		byte[] pixeldata2 = Imagedata.nonheader;
		
		//loads the information with header.
		byte[] allimagedata = new byte[pixeldata2.length+54];
		
		byte[] head = secret_retreival.lagrange(header, header1, 1, 2);
		
		System.arraycopy(head, 0, allimagedata, 0, 54);
		System.arraycopy(secret_retreival.lagrange(pixeldata, pixeldata2, 1,2), 0, allimagedata, 54, secret_retreival.lagrange(pixeldata, pixeldata2,1,2).length);
		
		//retrievs the image.
		ByteArrayInputStream in1 = new ByteArrayInputStream(allimagedata);
		BufferedImage share1 = ImageIO.read(in1);
		Imagedata.imagepreview(share1);
 	}
	


}
