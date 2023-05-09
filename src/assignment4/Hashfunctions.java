package assignment4;

import java.util.BitSet;

public class Hashfunctions 
{
	//two large prime numbers for n
	public static int p = 101; 
	public static int q = 103; 
	public static int n = p*q; // n calculation	
	public static int maxblockbits = 160; // for hash function 2 max bits
	
	public Hashfunctions()
	{
		
	}
		
	//hash function 1
	public static int hashfunction1(int x)
	{	
		//hash calculation
		return (int) (Math.pow(x, 2) % (p*q));
	}	

	//hash function 2
	public static BitSet hashfunction2(BitSet message)
	{
		BitSet returner = new BitSet(maxblockbits); // a temp array for to store hashed bits.
		int number_of_runs = (int) Math.ceil(message.length()/maxblockbits); //calculation of number of runs
		
		for(int i = 0 ; i < number_of_runs; i++)
		{
			int from = i * maxblockbits; //from i*160th bit
			int to = Math.min(from+maxblockbits, message.length()); // calculation of to bit
			BitSet currentBlock = message.get(from, to); // setting current block of message.
			
			returner.xor(currentBlock); //xoring with the returner to give out hashed value
		}
		return returner; //returns hash message of 160 bit.
	}
}
