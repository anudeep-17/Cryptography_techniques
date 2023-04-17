package assignment3;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Imagedata 
{
	int[] all_pixels;
	byte[] bytes;
	byte[] header;
	byte[] nonheader;
	
	Imagedata(String Path) throws IOException
	{
		File f = new File(Path);
		BufferedImage original = imagereader(f);
		
		all_pixels = getpixels(original);
		bytes = getbytes(getpixels(original));
		header = getheader(getbytes(getpixels(original)));
		nonheader = nonheaderbytes(getbytes(getpixels(original)));
		
//		SSS.imagepreview(original);
		System.out.println("total pixels: "+ getpixels(original).length);
		System.out.println("total bytes: "+ getbytes(getpixels(original)).length);
		System.out.println("total header bytes: "+ getheader(getbytes(getpixels(original))).length);
		System.out.println("total non-header bytes: "+nonheaderbytes(getbytes(getpixels(original))).length);
		
	}

	
	public static BufferedImage imagereader(File path) throws IOException
	{
		BufferedImage image = ImageIO.read(path);
		return image;
	}
	
	public static int[] getpixels(BufferedImage image) 
	{
//		System.out.println("width of image: "+image.getWidth() + " height of image:" + image.getHeight());
		return image.getRGB(0,0,image.getWidth(),image.getHeight(), null, 0, image.getWidth());
	}
	
	public static byte[] getbytes(int[] pixels)
	{
		byte[] imageData = new byte[pixels.length * 3];
		for (int i = 0; i < pixels.length; i++) {
		    int pixel = pixels[i];
		    imageData[i * 3] = (byte) ((pixel >> 16) & 0xff); // red value
		    imageData[i * 3 + 1] = (byte) ((pixel >> 8) & 0xff); // green value
		    imageData[i * 3 + 2] = (byte) (pixel & 0xff); // blue value
		}
		return imageData;
	}
	
	public static byte[] getheader(byte[] allbytes)
	{
		return Arrays.copyOfRange(allbytes, 0, 54);
	}
	
	public static byte[] nonheaderbytes(byte[] allbytes)
	{
		return Arrays.copyOfRange(allbytes, 54, allbytes.length);
	}
	
	
	public static void main(String[] args) throws IOException
	{
		File f = new File("C:\\Users\\Owner\\Downloads\\kite-4922348__340.bmp");
		BufferedImage original = imagereader(f);
		
//		SSS.imagepreview(original);
		System.out.println("total pixels: "+ getpixels(original).length);
		System.out.println("total bytes: "+ getbytes(getpixels(original)).length);
		System.out.println("total header bytes: "+ getheader(getbytes(getpixels(original))).length);
		System.out.println("total non-header bytes: "+nonheaderbytes(getbytes(getpixels(original))).length);
		
	}

}
