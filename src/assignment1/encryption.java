package assignment1;

import java.util.Arrays;

public class encryption 
{
	static String ciphertext = ""; 
	static String manipulationStr = ""; // string to hold the string value during manipulation.
	static int[] ascii_value_store = new int[21]; // a array to hold ascii values 

	// ---------------substituion ---------------------------------------------
	
	public static void Ascii_converter(String plaintext) // method to convert he string to ascii
	{
		for(int i = 0; i < plaintext.length(); i++) // runs until the end of the string and converts every letter to its ascii values and stores in array
		{
			char c = plaintext.charAt(i);
			ascii_value_store[i] = (int) c;
		}
	}
	
	// bit converter: used to convert the ascii values in to bits
	public static void bit_converter(int[] ascii_num)
	{
		for(int i = 0; i< ascii_num.length; i++) // loops through the ascii array 
		{
			if(ascii_num[i] == 0)
			{
				break;
			}
			else
			{
				String temp = Integer.toBinaryString(ascii_num[i]); // convetrs the num to binary bits
				manipulationStr += String.format("%8s", temp).replaceAll(" ", "0"); // increases length to 8 no matter what to maintain continuity.
			}
		}
	}
	
	// -------------------------- transpose -------------------------------------------- 
	public static String bit_manipulation(String ciphertex) // 
	{
			StringBuilder temp = new StringBuilder(); // string builder for temporary store of the cipher text
			temp.append(ciphertex);
			ciphertext = temp.reverse().toString(); // transposes the letters of the string and rearranges them in reverse order.
			return ciphertext;
	}
	
	//-----------------------product--------------------------------------------
	
	public static void ascii_maskingby_product(int[] ascii_value_store)
	{
		
		for(int i = 0; i< ascii_value_store.length ; i++) // loops through the whole acii array and multiplies them with 2 to have a product based manipulation.
		{
			ascii_value_store[i] = ascii_value_store[i]*2;
		}
	}
	
	
    // --------------------------encrypter-------------------
    public static String encryptedciphertext(String plaintext)
    {
    	Ascii_converter(bit_manipulation(plaintext)); // first reverses the order of the plain text, substitutes the reverse plain text by its ascii value
		ascii_maskingby_product(ascii_value_store); // ascii values are masked by product based manipulation
		bit_converter(ascii_value_store); // substitutued finally again by the bits
		return manipulationStr; // return the cipher text. 
    }
    
//----------------------testing ---------------------------------- [this main is not for grading]
//	public static void main(String[] args)
//	{
//		Ascii_converter(bit_manipulation("ANUDEEP"));
//		ascii_maskingby_product(ascii_value_store);
//		bit_converter(ascii_value_store);
//		System.out.println(Arrays.toString(ascii_value_store));
//		System.out.println(manipulationStr);
//	}
	
	
}
