package assignment2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class question1 extends AES
{
	public static String key = "anudeep";
	
	//method that runs ECB
	public static byte[] ECB(String filename) throws IOException
	{
		
		File plaintextFile = new File(filename);
		byte[] in = Files.readAllBytes(plaintextFile.toPath());
		byte[] ciphertext = encrypt(in, key.getBytes());
		
		return ciphertext;
	}
	
	//method that runs CBC
	public static byte[] CBC(String filename) throws IOException
	{
		
		File plaintextFile = new File(filename);
		byte[] in = Files.readAllBytes(plaintextFile.toPath());
		byte[] ciphertext = encrypt(in, key.getBytes(), 'C');

		return ciphertext;
	}
	
	//method that runs OFB
	public static byte[] OFB(String filename) throws IOException
	{
		File plaintextFile = new File(filename);
		byte[] in = Files.readAllBytes(plaintextFile.toPath());
		byte[] ciphertext = encrypt(in, key.getBytes(), 'O');
			
		return ciphertext;
	}
	
	//method to find teh frequency and calculate the IC of the cipher text
	public static Double index_of_coincidence(byte[] ciphertext)
	{
		int length = ciphertext.length;
		HashMap<Byte, Double> calculated_val = new HashMap<Byte, Double>();
		
		for(int i = 0; i < ciphertext.length; i++)
		{
			if(calculated_val.get(ciphertext[i]) == null)
			{
				calculated_val.put(ciphertext[i], 1.0);
			}
			else
			{
				Double count = calculated_val.get(ciphertext[i]);
				calculated_val.put(ciphertext[i], count+1.0);
			}
		}
		
		Double IC =(calculated_val.values().stream()
					.reduce(0.0,
							(partialcalc, currentcalc) -> (partialcalc + ((currentcalc*(currentcalc-1))/(length*(length-1) )))));
		return IC;
	}
	
	//calls all of the methods
	public static void encryptionperformer(String file) throws IOException
	{
		file = new File(file).getAbsolutePath();
//		System.out.println(file);
		byte[] ciphertext_ECB = ECB(file);
		byte[] ciphertext_CBC = CBC(file);
		byte[] ciphertext_OFB = OFB(file);
		
		Double IC_ECB = index_of_coincidence(ciphertext_ECB);
		Double IC_CBC = index_of_coincidence(ciphertext_CBC);
		Double IC_OFB = index_of_coincidence(ciphertext_OFB);
		
		textwritter.resultwritter(ciphertext_ECB, IC_ECB, ciphertext_CBC, IC_CBC, ciphertext_OFB, IC_OFB, key);	
	
	}
	
	public static void main(String[]args) throws IOException
	{
		
		//UI for this
		JLabel prompt = new JLabel("please select the duplication percentage", JLabel.CENTER);
		
		JPanel panel = new JPanel();
		
		JButton percent0 = new JButton("0% duplication");
		percent0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String file = "src/assignment2/plaintext.txt";
				try {
					encryptionperformer(file);
					Desktop.getDesktop().open(new File("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\src\\assignment2\\Result.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		
		JButton percent25 = new JButton("25% duplication");
		percent25.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String file = "src/assignment2/plaintext25%.txt";
				try {
					encryptionperformer(file);
					Desktop.getDesktop().open(new File("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\src\\assignment2\\Result.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		
		JButton percent50 = new JButton("50% duplication");
		percent50.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String file = "src/assignment2/plaintext50%.txt";
				try {
					encryptionperformer(file);
					Desktop.getDesktop().open(new File("C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\src\\assignment2\\Result.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		
		JButton cancel = new JButton("EXIT");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
				
		panel.add(percent0);
		panel.add(percent25);
		panel.add(percent50);
		panel.add(cancel);
		
		panel.setLayout(new FlowLayout());
		
		 JFrame sectionFrame = new JFrame("AES Implementation");
		 sectionFrame.add(prompt);
		 sectionFrame.add(panel);
		 sectionFrame.setSize(500,300);
		 sectionFrame.setLayout(new GridLayout(3, 1));
		 sectionFrame.setVisible(true);
		 sectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
	}

}
