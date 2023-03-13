package assignment1;

import java.util.Random;
import java.util.Scanner;

public class main 
{
	// main method for the whole program. 
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in); // scanner to get the input from user
		System.out.println("If you want to Encrypt type E, to Decrypt type D"); // a prompt to the user to make sure what he/she trying to do
		
		String input = sc.nextLine(); // reads the input from user 
		
		Random rand = new Random();
		
		if(input.equals("E")) //  if the input is E - encryption
		{
			System.out.println("please enter your plain text -- [make sure it is Minimum 7 letters to Max 20]");
			String plaintext = sc.nextLine(); // it prompts to input a plain text to convert to cipher. 
			plaintext = plaintext.toUpperCase(); // makes it to upper case to generalize the idea.
			String cipher = encryption.encryptedciphertext(plaintext); // calles the method to encrypt the plain text and returns the cipher text. 
			System.out.println("ciphertext of the given plain text : " + cipher); // gives the cipher text to user 
			System.out.println("");
		}
		else if(input.equals("D")) // if it is D - decryption. 
		{
			System.out.println("please enter your cipher text");
			String ciphertext = sc.nextLine(); // reads the cipher 
			String plaintext = decryption.decryptplaintext(ciphertext); // converts the cipher to plain text and prints the plain text
			System.out.println("plaintext of the given cipher text: " + plaintext);
		}
	}

}
