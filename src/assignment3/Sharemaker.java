package assignment3;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

//class that makes the job for dividing into 5 shares.
public class Sharemaker 
{
	static Random random = new Random(); //random int genarator to make the share most distorted.
	 
	static int numofshares = 5; //number of shares to divide
	
	
	public static void main(String[] args) throws IOException
	{
		Imagedata imagedata = new Imagedata("C:\\Users\\Owner\\Downloads\\Army2016demo-007.bmp"); //loads the image
		
		
		byte[][] idata = headadder(shares(Imagedata.nonheader)); //reads the divided shares
		
//		System.out.println("each share bytes:" + idata[0].length);
	
		share_download(idata);// downloads the images 
		
	}
	
	//shares divided
	public static byte[][] shares(byte[] nonheaderdata)//takes non header data here to break number of shares
	{
		byte[][] shares = new byte[numofshares][];  // for the giving numof shares we divide the shares into 2d arrays
		int[] coefficient = randomarrayforcoefficient(Imagedata.nonheader.length); // we generate a random int array for the coefficient to make sure that its randomized
		
		for(int i = 0; i<shares.length; i++) // divides the shares using this equation
		{
			byte[] temp = new byte[Imagedata.nonheader.length];
			
			for(int j = 0; j < Imagedata.nonheader.length; j++)
			{	
				byte term = (byte) ((i+1) * coefficient[j]);
				temp[j] =  (byte) ((Imagedata.nonheader[j] + term) % 251);
			}
			
//			System.out.println(temp.length);
			shares[i] = temp;
//			System.out.println(i +" "+shares_5[i].length);
		}	
		
		return shares;
	}
 
	//adds the header to the shares 
	public static byte[][] headadder(byte[][] shares)
	{
		byte[][] share = new byte[numofshares][Imagedata.nonheader.length+54];
		
		for(int i = 0; i < shares.length; i++)
		{
			System.arraycopy(Imagedata.header, 0, share[i], 0, 53);
			System.arraycopy(shares[i], 0, share[i], 54, shares[i].length);
		}
		return share;
	}
	
	//downloads the shares
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
	
	//creates random arrays for the coefficients
	public static int[] randomarrayforcoefficient(int length)
	{
		int[] randomarray = new int[length];
		
		for(int i = 0; i<length; i++)
		{
			randomarray[i] = random.nextInt(251)+1;
		}
		return randomarray;
	}
	
}
