package assignment4;

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
	
	static byte[] iv = new byte[16];
    static byte[] key = new byte[16];
    
    static SecureRandom random = new SecureRandom();
    
    static String[] randomnums = new String[10];
    
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException 
	{
		byte[][] prng_random_gen = new byte[10][16];
	    
		Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
		
		random.nextBytes(key);
		SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
		
		random.nextBytes(iv);
	    IvParameterSpec ivspec = new IvParameterSpec(iv);
	    
	    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
	    
	    byte[] temp = new byte[16];
  
		for(int i = 0; i< prng_random_gen.length; i++)
		{
				prng_random_gen[i] = cipher.update(temp);
		}

		printBits(prng_random_gen);
		
		double[] fractionsofones = fraction_of_one_bits(randomnums);
		double[] fractionsofmatch = fraction_of_prevmatch(randomnums);
		
		printingall(randomnums, fractionsofones, fractionsofmatch);
	}
	
    public static void printBits(byte[][] bytes) {
       
    	for(int j = 0 ; j< bytes.length; j++)
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
    
    public static double[] fraction_of_one_bits(String[] bits)
    {
    	double[] fractionofone = new double[10];
    	
    	for(int i = 0; i < bits.length; i++)
    	{   	
    		fractionofone[i] =  countofones(bits[i])/128;
    	}
    	return fractionofone;
    }
    
    public static double[] fraction_of_prevmatch(String[] bits)
    {
    	double[] fractionofmatch = new double[10];
    	
    	for(int i = 1; i < bits.length; i++)
    	{
    		fractionofmatch[i] =  countofmatch(bits[i-1], bits[i])/128;
    	}
    	return fractionofmatch;
    }
    
    
    public static double countofones(String str)
    {
    	double count = 0.0;
    	
    	for (int i = 0; i< str.length() ; i++)
    	{
    		if(str.charAt(i) == '1')
    		{
    			count += 1;
    		}
    	}
    	
    	return count;
    }
    
    public static double countofmatch(String str, String str2)
    {
    	double count = 0.0;
    	
    	for (int i = 0; i< str.length() ; i++)
    	{
    		if(str.charAt(i) == str2.charAt(i) && str.charAt(i) != ' ')
    		{
    			count += 1;
    		}
    	}
    	
    	return count;
    }
    
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
