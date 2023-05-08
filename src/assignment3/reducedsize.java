package assignment3;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.awt.image.DataBufferByte;

import javax.imageio.ImageIO;


// class for downscaled version.
public class reducedsize 
{
	
	public static void main(String[] args) throws IOException
	{
		//loads image and downscales the image and downloads them
		
		Imagedata a = new Imagedata("C:\\Users\\Owner\\Downloads\\Army2016demo-007.bmp");
		BufferedImage OG_downscale = downscaled(a.all_pixels, a.width, a.heigth);
		
		File output = new File("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\OG_downscaled.bmp");
		ImageIO.write(OG_downscale, "bmp", output);
		
		Imagedata b = new Imagedata("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share0.bmp");
		BufferedImage share1 = downscaled(b.all_pixels, b.width, b.heigth);
		
		File outputfile = new File("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share"+0+"_downscaled.bmp");
		ImageIO.write(share1, "bmp", outputfile);
		
		
		Imagedata c = new Imagedata("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share1.bmp");
		BufferedImage share2 = downscaled(c.all_pixels, c.width, c.heigth);
		
		File outputfile2 = new File("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\Shares\\share"+1+"_downscaled.bmp");
		ImageIO.write(share2, "bmp", outputfile2);
	}
	
	// downscale method that returns the downscaled buffered image.
	public static BufferedImage downscaled(int[] all_pixels, int width, int height)
	{	
		int[] newpixels = new int[(width / 2) * (height / 2)];
	    int pixelIndex = 0;
	   //reads through each pixel and adds the red blue and green values and once done it will return the pixel value that can be used to construct a buffered image.
	    for (int y = 0; y < height; y += 2) 
	    {
	        for (int x = 0; x < width; x += 2) 
	        {
	            int red = 0;
	            int green = 0;
	            int blue = 0;
	            
	            for (int j = 0; j < 2; j++) 
	            {
	                for (int i = 0; i < 2; i++) 
	                {
	                    int pixel = all_pixels[(y + j) * width + (x + i)];
	                    red += (pixel >> 16) & 0xff;
	                    green += (pixel >> 8) & 0xff;
	                    blue += pixel & 0xff;
	                }
	                
	            }
	            
	            red = red/ 4; 
	            green= green/4; 
	            blue = blue/ 4;
	     
	            newpixels[pixelIndex++] = (red << 16) | (green << 8) | blue;
	        }
	    }
	    
		BufferedImage image = new BufferedImage(width/2, height/2, BufferedImage.TYPE_INT_RGB); //creates a buffer image 
		image.setRGB(0, 0, width/2, height/2, newpixels, 0, width/2);
		 
		Imagedata.imagepreview(image);
		return image;
	}
}
