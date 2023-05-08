package assignment3;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

//creates the shares with header
public class withheadersecretshare 
{
	static Random random = new Random();
	public static void main(String[] args) throws IOException
	{
		Imagedata imagedata = new Imagedata("C:\\Users\\Owner\\Downloads\\Army2016demo-007.bmp");
		
		
		byte[][] idata = Sharemaker.headadder(Sharemaker.shares(Imagedata.bytes));
		
//		System.out.println("each share bytes:" + idata[0].length);
	
		share_download(idata);		
		
	}
	
	
	
	public static void share_download(byte[][] share) throws IOException
	{
		for(int i = 0; i<share.length; i++)
		{
			ByteArrayInputStream in1 = new ByteArrayInputStream(share[i]);
			BufferedImage sharedata = ImageIO.read(in1);
			
			File outputfile = new File("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share"+i+"_withheader.bmp");
			ImageIO.write(sharedata, "bmp", outputfile);
		}
	}
	

}
