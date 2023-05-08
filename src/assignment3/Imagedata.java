package assignment3;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

// the class that extracts the image data like pixels, bytes of the image, header fo the image , non header bytes of the image. 

public class Imagedata 
{
	//global variables for the usage of info retreival of image. 
	static int[] all_pixels; 
	static byte[] bytes;
	static byte[] header;
	static byte[] nonheader;
	static int width = 0;
	static int heigth = 0;
	
	//constructor to load the above information
	Imagedata(String Path) throws IOException
	{
		File f = new File(Path);
		BufferedImage original = imagereader(f);
		width = original.getWidth();
		heigth = original.getHeight();
		
		all_pixels = getpixels(original);
		bytes = getbytes(new FileInputStream(f));
		header = getheader(getbytes(new FileInputStream(f)));
		nonheader = nonheaderbytes(getbytes(new FileInputStream(f)));
		
//		System.out.println("total pixels: "+ getpixels(original).length);
//		System.out.println("total bytes: "+ getbytes(new FileInputStream(f)).length);
//		System.out.println("total header bytes: "+ getheader(getbytes(new FileInputStream(f))).length);
//		System.out.println("total non-header bytes: "+nonheaderbytes(getbytes(new FileInputStream(f))).length);
		
	}

	//reads a path and returns a buffered image.
	public static BufferedImage imagereader(File path) throws IOException
	{
		BufferedImage image = ImageIO.read(path);
		return image;
	}
	
	//reads buffered image and gives pixels of the image.
	public static int[] getpixels(BufferedImage image) 
	{
//		System.out.println("width of image: "+image.getWidth() + " height of image:" + image.getHeight());
		return image.getRGB(0,0,image.getWidth(),image.getHeight(), null, 0, image.getWidth());
	}
	
	//reads bytes of the image by the file path
	public static byte[] getbytes(FileInputStream image) throws IOException
	{
		BufferedInputStream input = new BufferedInputStream(image);
		byte[] imageData = new byte[input.available()];
		input.read(imageData);
		return imageData;
	}
	
	//reads header of the image
	public static byte[] getheader(byte[] allbytes)
	{
		return Arrays.copyOfRange(allbytes, 0, 54);
	}
	
	//reads nonheader bytes or bytes from 54th position.
	public static byte[] nonheaderbytes(byte[] allbytes)
	{
		return Arrays.copyOfRange(allbytes, 54, allbytes.length);
	}
	

	//used to see the image prview.
	public static void imagepreview(BufferedImage image)
	{
		ImageIcon icon=new ImageIcon(image);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(200,300);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
