package assignment3;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

public class Sharemaker 
{
	static Random random = new Random();
	
	public static void main(String[] args) throws IOException
	{
		Imagedata imagedata = new Imagedata("C:\\Users\\Owner\\Downloads\\kite-4922348__340.bmp");
		
		
		byte[][] idata = headadder(shares(Imagedata.nonheader));
		
		System.out.println("each share bytes:" + idata[0].length);
//		SSS.imagepreview(share1);
//		ByteArrayInputStream in1 = new ByteArrayInputStream(idata[4]);
//		BufferedImage share1 = ImageIO.read(in1);
//		System.out.println(share1);
//		SSS.imagepreview(share1);
		
		share_download(idata);		
		
	}
	
	public static byte[][] shares(byte[] nonheaderdata)
	{
		byte[][] shares = new byte[5][];
		
		for(int i = 0; i<shares.length; i++)
		{
			byte[] temp = new byte[Imagedata.nonheader.length];
			byte term = (byte) ((i+1) * 113);
			
			for(int j = 0; j < Imagedata.nonheader.length; j++)
			{	
				temp[j] =  (byte) ((Imagedata.nonheader[j] + term) % 251);
			}
			
//			System.out.println(temp.length);
			shares[i] = temp;
//			System.out.println(i +" "+shares_5[i].length);
		}	
		
		return shares;
	}

	public static byte[][] headadder(byte[][] shares)
	{
		byte[][] share = new byte[5][Imagedata.nonheader.length+54];
		
		for(int i = 0; i < shares.length; i++)
		{
			System.arraycopy(Imagedata.header, 0, share[i], 0, 53);
			System.arraycopy(shares[i], 0, share[i], 54, shares[i].length);
		}
		return share;
	}
	
	public static void share_download(byte[][] share) throws IOException
	{
		for(int i = 0; i<share.length; i++)
		{
			ByteArrayInputStream in1 = new ByteArrayInputStream(share[i]);
			BufferedImage sharedata = ImageIO.read(in1);
			
			File outputfile = new File("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share"+i+".bmp");
			ImageIO.write(sharedata, "bmp", outputfile);
		}
	}
	
}
