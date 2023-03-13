package assignment1;

import java.util.Arrays;

public class decryption 
{
	static String obtained = ""; // obtained is string for the parts of given cipher text.
	static int[] ascii_retrival = new int[21]; // ascii value storer for the reverse calculation. 
	static String plaintext = "";
	
	public static void bit_to_ascii(String bits)
	{
		for(int i = 0; i<=bits.length(); i++) // loops through all the bits
		{
		    obtained = bits.substring(0,8); // starts reading sets of 8 bits and converts its back to ascii values. 
//			System.out.println(obtained);
//			System.out.println(Integer.parseInt(obtained, 2));
			ascii_retrival[i] = ascii_division(Integer.parseInt(obtained, 2)); // divides the ascii values with the product key.
			bits = bits.substring(8); // reduces the length of bits by 8 on every iteration.
//			System.out.println(bits);
//			System.out.println("length of bits : " + bits.length());
			if(bits.length() == 8) // on the last iteration.
			{ 
				ascii_retrival[i+1] = ascii_division(Integer.parseInt(bits, 2)); // divides the ascii values with the product key.
			}
		}
		
//		ascii_retrival[] = ascii_division(Integer.parseInt(bits, 2));
	}
	
	// ------------------ reverse of product -----------------------
	public static int ascii_division(int a)
	{
		return a/2; // divides the key with the ascii values
	}
	
	//--------------------- ascii to text ------------------------------
	public static void asciitotext(int[] ascii)
	{
		for(int i = 0; i<= ascii.length; i++) //loops over ascii and converts back to text
		{
			if(ascii[i] == 0)
			{
				break;
			}
			else
			{
				plaintext += (char) ascii[i];
			}	
		}
	}
	
	//--------------------decrypter--------------------
	public static String decryptplaintext(String ciphertext)
	{
		bit_to_ascii(ciphertext); // converts the bits to ascii 
//		System.out.println(Arrays.toString(ascii_retrival));
		asciitotext(ascii_retrival); // converts ascii to text
		StringBuilder temp = new StringBuilder();
		temp.append(plaintext);
		
		return temp.reverse().toString(); // reverse the text and return plain text
	}

}
