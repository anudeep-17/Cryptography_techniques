package assignment2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class question1 extends AES
{

	public static byte[] ECB(String filename) throws IOException
	{
		
		File plaintextFile = new File(filename);
		byte[] in = Files.readAllBytes(plaintextFile.toPath());
		String key = "anudeep";
		byte[] ciphertext = encrypt(in, key.getBytes());
		
		System.out.println("Key value: "+ Arrays.toString(key.getBytes()));
		System.out.println(" ");
		
		for(int i = 0; i< ciphertext.length; i++)
		{
			
			System.out.print((ciphertext[i] & 0xff) + " ");
		}
		return ciphertext;
		
	}
	
	public static byte[] CBC(String filename) throws IOException
	{
		
		File plaintextFile = new File(filename);
		byte[] in = Files.readAllBytes(plaintextFile.toPath());
		String key = "anudeep";
		byte[] ciphertext = encrypt(in, key.getBytes(), 'C');
		
		System.out.println();
		
		System.out.println("Key value: "+ Arrays.toString(key.getBytes()));
		
		System.out.println(" ");
		
		for(int i = 0; i< ciphertext.length; i++)
		{
			
			System.out.print((ciphertext[i] & 0xff) + " ");
		}
		return ciphertext;
	}
	
	public static byte[] OFB(String filename) throws IOException
	{
		File plaintextFile = new File(filename);
		byte[] in = Files.readAllBytes(plaintextFile.toPath());
		String key = "anudeep";
		
		byte[] ciphertext = encrypt(in, key.getBytes(), 'O');
		
		System.out.println();
		
		System.out.println("Key value: "+ Arrays.toString(key.getBytes()));
		
		System.out.println(" ");
		
		for(int i = 0; i< ciphertext.length; i++)
		{
			
			System.out.print((ciphertext[i] & 0xff) + " ");
		}
		
		return ciphertext;
	}
	
	
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
		System.out.println();
		System.out.println(calculated_val.values());
		Double IC =(calculated_val.values().stream()
					.reduce(0.0,
							(partialcalc, currentcalc) -> (partialcalc + ((currentcalc*(currentcalc-1))/(length*(length-1) )))));
		return IC;
	}
	
	public static void main(String[]args) throws IOException
	{
		String file = "C:\\Users\\Owner\\OneDrive\\eclipse\\Cryptography_assignment1\\src\\assignment2\\CBCtext.txt";
		Double IC = index_of_coincidence(ECB(file));
		System.out.println();
		System.out.println();
		System.out.println("IC of the given file: " + IC);
		System.out.println();
		System.out.println();
//		CBC(file);
		IC = index_of_coincidence(CBC(file));
		System.out.println();
		System.out.println();
		System.out.println("IC of the given file: " + IC);
		System.out.println();
		System.out.println();
//		OFB(file);
		IC = index_of_coincidence(OFB(file));
		System.out.println();
		System.out.println();
		System.out.println("IC of the given file: " + IC);
		
	}

}
