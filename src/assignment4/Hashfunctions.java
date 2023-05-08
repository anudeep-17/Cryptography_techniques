package assignment4;

import java.util.BitSet;

public class Hashfunctions 
{
	public static int p = 101;
	public static int q = 103; 
	public static int n = p*q;
	public static int maxblockbits = 160;
	
	public Hashfunctions()
	{
		
	}
		
	public static int hashfunction1(int x)
	{	
		//large prime numbers
		return (int) (Math.pow(x, 2) % (p*q));
	}	

	public static BitSet hashfunction2(BitSet message)
	{
		BitSet returner = new BitSet(maxblockbits);
		int number_of_runs = (int) Math.ceil(message.length()/maxblockbits);
		
		for(int i = 0 ; i < number_of_runs; i++)
		{
			int from = i * maxblockbits;
			int to = Math.min(from+maxblockbits, message.length());
			BitSet currentBlock = message.get(from, to);
			
			returner.xor(currentBlock);
		}
		return returner;
	}
}
