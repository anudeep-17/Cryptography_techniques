package assignment3;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.imageio.ImageIO;

public class SSS 
{
	public static byte[] headofimage = new byte[54];
	public static byte[] pixelofimage;

	public SSS(String Path) throws IOException
	{
		System.out.println("SSS 2 shares broken into 5 shares");
		File f = new File(Path);
		BufferedImage i = imagereader(f);	
		imagepreview(i);
		System.out.println();
		
		headofimage = imageheader_reader(new FileInputStream(Path));
		pixelofimage= imagepixel_reader(new FileInputStream(Path));
	
		System.out.println( headofimage.length);
		System.out.println( pixelofimage.length);
	}
	
	
	public static BufferedImage imagereader(File Path) throws IOException
	{
		BufferedImage image = ImageIO.read(Path);
		return image;
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
	
	public static byte[] imageheader_reader(FileInputStream image) throws IOException
	{
		BufferedInputStream input = new BufferedInputStream(image);
		byte[] temp = new byte[53];
		input.read(temp);
//		System.out.println(Arrays.toString(temp));
		return temp;
	}
	
	public static byte[] imagepixel_reader(FileInputStream image) throws IOException
	{
		BufferedInputStream input = new BufferedInputStream(image);
		System.out.println(input.available());
		input.skip(54);
		byte[] temp = new byte[input.available()];
		input.read(temp);
//		System.out.println(temp.length);
		return temp;
	}

//	public static void main(String[] args) throws IOException
//	{
//		System.out.println("SSS 2 shares broken into 5 shares");
//		File f = new File("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\src\\assignment3\\Army2016demo-007.bmp");
//		BufferedImage i = imagereader(f);	
//		System.out.println(i.getWidth());
//		System.out.println(i.getHeight());
//		
//		System.out.println();
//		headofimage = imageheader_reader(new FileInputStream("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\src\\assignment3\\Army2016demo-007.bmp"));
//		pixelofimage= imagepixel_reader(new FileInputStream("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\src\\assignment3\\Army2016demo-007.bmp"));
//		System.out.println( headofimage.length);
//		System.out.println( pixelofimage.length);
//		
//	}
	
	
	
}
