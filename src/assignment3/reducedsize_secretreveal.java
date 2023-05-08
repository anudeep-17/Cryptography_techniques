package assignment3;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class reducedsize_secretreveal 
{
	public static void main(String[] args) throws IOException
	{
		//loads the data
		Imagedata a  = new Imagedata("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\OG_downscaled.bmp");
		
		Imagedata b  = new Imagedata("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share0_downscaled.bmp");
		byte[] header = Imagedata.header;
		byte[] pixeldata = Imagedata.nonheader;
		
		Imagedata c  = new Imagedata("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share1_downscaled.bmp");
		byte[] header1 = Imagedata.header;
		byte[] pixeldata2 = Imagedata.nonheader;
		
		//temp to get the information from the data with header 
		byte[] allimagedata = new byte[pixeldata2.length+54];

		
		System.arraycopy(header1, 0, allimagedata, 0, 54);
		
		System.arraycopy(secret_retreival.lagrange(pixeldata, pixeldata2, 1,2), 0, allimagedata, 54, secret_retreival.lagrange(pixeldata, pixeldata2,1,2).length);
		
		ByteArrayInputStream in1 = new ByteArrayInputStream(allimagedata);
		BufferedImage share1 = ImageIO.read(in1);
		Imagedata.imagepreview(share1); //gives teh reconstructed image

		
		System.out.println("mean error is : "+  mean_error(a.bytes, allimagedata));
	}

	//calculates teh mean error by summing the abnsolute error between origin and reconstructed image.
	public static int mean_error(byte[] OG, byte[] reconstructed)
	{
		int sum = 0;
		for(int i = 0 ; i< OG.length; i++)
		{
			sum += Math.abs(OG[i] - reconstructed[i]);
		}
		return sum;
	}
}
