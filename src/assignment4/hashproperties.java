package assignment4;

import java.util.BitSet;
import java.util.Random;

public class hashproperties 
{
	public hashproperties()
	{
		
	}
	public static boolean onewayproperty(int answer, int X)
	{
		
		for(int i = 1; i<= Math.pow(Hashfunctions.n, 2); i++)
		{
			if(answer == Hashfunctions.hashfunction1(i))
			{
				if(i == X)
				{
					continue;
				}
				else
				{
					System.out.println("same value found at : " + i + " which is not equal to " + X);
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	public static boolean onewayproperty(BitSet answer, BitSet message)
	{		
		BitSet checktemp = new BitSet(160);
		
		for(int i = 0; i<= Math.pow(message.length(),2); i++)
		{
			BitSet test = generateRandomBitSet(message.length());
			BitSet hashoftest = Hashfunctions.hashfunction2(test);
			
			if(hashoftest.equals(answer))
			{
				if(test.equals(message))
				{
					checktemp = test;
					continue;
				}
				else
				{
					System.out.println(i);
					return false;
				}
			}
		}
		
		if(checktemp == message)
		{
			return true;
		}
		else
		{
			System.out.println("It satisfies oneway property");
			return false;
		}
		
	}
	
	
	public static boolean weakcollisionproperty(int X)
	{		
		int result = Hashfunctions.hashfunction1(X);
		
		for (int i = 0; i <= Math.pow(Hashfunctions.n,2);i++)
		{
			if(result == Hashfunctions.hashfunction1(i) && i != X)
			{
				System.out.println("weak collision of "+ X+ " with "+ i);
				return true;
			}	
		}	
		return false;
	}
	
	public static boolean weakcollisionproperty(BitSet message)
	{
		BitSet hashvalue = Hashfunctions.hashfunction2(message);
		BitSet newmessage = generatemessageforgiven(message);
		BitSet hashtemp = Hashfunctions.hashfunction2(newmessage);
		
		boolean check = (newmessage == message);
		check = !check;
		
		if(hashtemp.equals(hashvalue) && check)
		{
			System.out.println("a new message found which is not equal to given and has same hash value");
			return true;
		}
		
		return false;		
	}
	
	public static boolean strongcollision(String hashname)
	{	
		if(hashname.equals("Hash1"))
		{
			Random rand = new Random();
			int temp = rand.nextInt(Hashfunctions.n);
			int result = Hashfunctions.hashfunction1(temp);
			
			for(int j = 0; j <= Hashfunctions.n; j++)
			{
				if(result == Hashfunctions.hashfunction1(j) && temp != j)
				{
					System.out.println("Strong collision pair: (X,y) = ("+temp + " ," + j+") ");
					return true;
				}
			}
			System.out.println("it satisfies strong collision property");
			
		}
		
		else if(hashname.equals("Hash2"))
		{
			
			BitSet check1 = generateRandomBitSet(320);
			
			for(int i = 0; i<= Math.pow(320,2); i++)
			{
				BitSet check2 = generateRandomBitSet(320);
				
				if((check2 != check1) && Hashfunctions.hashfunction2(check2).equals(Hashfunctions.hashfunction2(check1)))
				{
					System.out.println("it has a collision between two (X,y) found after :" + i+ "runs");
					return true;
				}
			}
			System.out.println("it satisfies strong collision property");

		}
		
		return false;
	}

	
	 public static BitSet generateRandomBitSet(int length) 
	 {
	        Random random = new Random();
	        BitSet bits = new BitSet(length);
	        for (int i = 0; i < length; i++) 
	        {
	            boolean bit = random.nextBoolean();
	            bits.set(i, bit);
	        }
	        
	        return bits;
	 }
	 
	 public static BitSet generatemessageforgiven(BitSet message)
	 {	 
		 BitSet newMessage = new BitSet(message.length());
		 newMessage.xor(message.get(0, message.length()));
		 return newMessage;
	 }
}
