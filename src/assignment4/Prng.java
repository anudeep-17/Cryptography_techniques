package assignment4;

//***** AES encryption package for java is used and code is taken from online sources for cipher here.
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Prng 
{    
	// key and iv for AES
	static byte[] iv = new byte[16]; 
    static byte[] key = new byte[16];
    
    // a secure random generator for key and Iv
    static SecureRandom random = new SecureRandom();
    
    static String[] randomnums = new String[10]; //random 10 numbers storage for fraction calculation.
    
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException 
	{
		byte[][] prng_random_gen = new byte[10][16]; // to store 10 - 16 bytes.
	    
		Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding"); // the initialization of cipher with no padding as to make same sized ciphers
		
		random.nextBytes(key); // generates random keys
		
		SecretKeySpec secretKey = new SecretKeySpec(key, "AES"); // we set the key to the aes
		
		random.nextBytes(iv); // we generate a random iv for encryption
		
	    IvParameterSpec ivspec = new IvParameterSpec(iv); // initialization of iv vector for aes
	    
	    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec); // aes mode with ofb creation 
	    
	    byte[] temp = new byte[16]; // a temp array to encrypt and store that.
  
		for(int i = 0; i< prng_random_gen.length; i++)
		{
				prng_random_gen[i] = cipher.update(temp); // encrypts the and stores the random numbers 
		}

		printBits(prng_random_gen); //stores the random numbers in the string []
		
		double[] fractionsofones = fraction_of_one_bits(randomnums); //calculates the one bits in the generated random numbers
		double[] fractionsofmatch = fraction_of_prevmatch(randomnums); //calculates the match previous bits in the generated random numbers
		
		printingall(randomnums, fractionsofones, fractionsofmatch); //prints all the data to the console.
	}
	
	//stores the BitSet as bits form for the calculation
    public static void printBits(byte[][] bytes) {
       
    	for(int j = 0 ; j< bytes.length; j++) //reads through the 2D array and convers the bits to 0's and 1's
    	{
    		String bits = "";
    		for (byte b : bytes[j]) {
                for (int i = 7; i >= 0; i--) {
                    bits += ((b & (1 << i)) != 0 ? "1" : "0");
                }
                bits+= " ";
            }
    		randomnums[j] = bits;
    	}     
    }
    
    //calculation of fraction of ones
    public static double[] fraction_of_one_bits(String[] bits)
    {
    	double[] fractionofone = new double[10];
    	
    	for(int i = 0; i < bits.length; i++) //loops through bits and of a give string and counts 1s and divides it by 128
    	{   	 
    		fractionofone[i] =  countofones(bits[i])/128; // stores in the array of doubles
    	}
    	return fractionofone;
    }
    
    // calculation of previous match
    public static double[] fraction_of_prevmatch(String[] bits)
    {
    	double[] fractionofmatch = new double[10];
    	
    	for(int i = 1; i < bits.length; i++) //loops through all the bit length and counts how many bits matched between two random numbers
    	{
    		fractionofmatch[i] =  countofmatch(bits[i-1], bits[i])/128; // and divides it by 128 and stores in array
    	}
    	return fractionofmatch;
    }
    
    
    //for a given string counts the 1s in it
    public static double countofones(String str)
    {
    	double count = 0.0;
    	
    	for (int i = 0; i< str.length() ; i++)
    	{
    		if(str.charAt(i) == '1') //if 1 increase the count
    		{
    			count += 1;
    		}
    	}
    	
    	return count;
    }
    
    //for each bit find the common
    public static double countofmatch(String str, String str2)
    {
    	double count = 0.0;
    	
    	for (int i = 0; i< str.length() ; i++) //reads through both the strings and if a bit is common we increase the count.
    	{
    		if(str.charAt(i) == str2.charAt(i) && str.charAt(i) != ' ')
    		{
    			count += 1;
    		}
    	}
    	
    	return count;
    }
    
    //printing format for the console printing.
    public static void printingall(String[] allnum, double[] fractions, double[]fractions2)
    {
    	for(int i = 0; i<allnum.length; i++)
    	{
    		System.out.println("random number "+(i+1)+" : "+allnum[i]);
    		System.out.println("fraction of ones: " + fractions[i]);
    		System.out.println("fraction of Bits match with preceding: " + fractions2[i]);
    		System.out.println();
    	}
    }
    
}
