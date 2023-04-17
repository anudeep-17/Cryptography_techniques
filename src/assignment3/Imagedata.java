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

public class Imagedata 
{
	static int[] all_pixels;
	static byte[] bytes;
	static byte[] header;
	static byte[] nonheader;
	
	Imagedata(String Path) throws IOException
	{
		File f = new File(Path);
		BufferedImage original = imagereader(f);
		
		all_pixels = getpixels(original);
		bytes = getbytes(new FileInputStream(f));
		header = getheader(getbytes(new FileInputStream(f)));
		nonheader = nonheaderbytes(getbytes(new FileInputStream(f)));
		
//		
//		
////	SSS.imagepreview(original);
		System.out.println("total pixels: "+ getpixels(original).length);
		System.out.println("total bytes: "+ getbytes(new FileInputStream(f)).length);
		System.out.println("total header bytes: "+ getheader(getbytes(new FileInputStream(f))).length);
		System.out.println("total non-header bytes: "+nonheaderbytes(getbytes(new FileInputStream(f))).length);
		
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
	
	public static byte[] getbytes(FileInputStream image) throws IOException
	{
		BufferedInputStream input = new BufferedInputStream(image);
		byte[] imageData = new byte[input.available()];
		input.read(imageData);
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
	
	
	public static void main(String[] args) throws IOException
	{
		File f = new File("C:\\Users\\Owner\\Downloads\\check.bmp");
		BufferedImage original = imagereader(f);
		
//		SSS.imagepreview(original);
		
//		System.out.println("total bytes: "+ getbytes(getpixels(original)).length);
//		System.out.println("total header bytes: "+ getheader(getbytes(getpixels(original))).length);
//		System.out.println("total non-header bytes: "+nonheaderbytes(getbytes(getpixels(original))).length);
//		
//		Imagedata imagedata = new Imagedata("C:\\Users\\Owner\\Downloads\\kite-4922348__340.bmp");
//		
//		
//		byte[] share = new byte[nonheader.length];
//		
//		System.arraycopy(Imagedata.header, 0, share, 0, 53);
//		System.arraycopy(nonheader, 0, share, 54, nonheader.length);
//		ByteArrayInputStream in1 = new ByteArrayInputStream(share);
//		BufferedImage share1 = ImageIO.read(in1);
//		System.out.println(share1);
//		SSS.imagepreview(share1);
	}

}
