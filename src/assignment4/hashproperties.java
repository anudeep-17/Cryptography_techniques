package assignment4;

import java.util.BitSet;
import java.util.Random;

public class hashproperties 
{
	public hashproperties()
	{
		
	}
	
	//oneway property for one way property of hashfunction 1
	public static boolean onewayproperty(int answer, int X)
	{
		
		for(int i = 1; i<= Math.pow(Hashfunctions.n, 2); i++) // check for n to the square times if it can encounter the X and if it find X then returns false else true
		{
			if(answer == Hashfunctions.hashfunction1(i)) // if the answer is found and equal to X then continue to find if there any other number if there is any other then false;
			{
				if(i == X)
				{
					continue;
				}
				else
				{
					System.out.println("same value found at : " + i + " which is not equal to " + X + " --- satisfies one way property");
					System.out.println();
					return true;
				}
			}
		}
		System.out.println("oneway property not satisfied");
		return false; // if no other X is found then return false as we found X back;
	}
	
	// one way propety of the hashfunction 2.
	public static boolean onewayproperty(BitSet answer, BitSet message)
	{		
		BitSet checktemp = new BitSet(160); // the resultant bits are set to this.
		
		for(int i = 0; i<= Math.pow(message.length(),2); i++)
		{
			// generates random bits of message length and tries to find if their hashes match then check if there any
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
					return true; //as some other message found to have same value
				}
			}
		}
		
		//after appending the it with the found message we check if it is same as message else, we will return false. else true.
		if(checktemp == message)
		{
			return false; //as message recovered back
		}
		else
		{
			System.out.println("It satisfies oneway property");
			return true; // satisfied
		}
		
	}
	
	//weak collision for a hashfunction 1.
	public static boolean weakcollisionproperty(int X)
	{		
		int result = Hashfunctions.hashfunction1(X); // we calculate hash value of X 
		
		// now we iterate over n to the square numbers that can give same hashvalue and if we found any then return true else false. and that the Y hence we found a Y for X
		for (int i = 0; i <= Math.pow(Hashfunctions.n,2);i++)
		{
			if(result == Hashfunctions.hashfunction1(i) && i != X)
			{
				System.out.println("weak collision of "+ X+ " with "+ i +" --- weak collision resistance not satisfied");
				return true; //and that the Y hence we found a Y for X
			}	
		}	
		return false;
	}
	
	//weak collision for message 
	public static boolean weakcollisionproperty(BitSet message)
	{
		BitSet hashvalue = Hashfunctions.hashfunction2(message); // calculates hash value of the message
		BitSet newmessage = generatemessageforgiven(message); // generates a hash value basing on the given message
		BitSet hashtemp = Hashfunctions.hashfunction2(newmessage); // and now get the hash value of the message
		
		boolean check = (newmessage == message); //if message == new message and will be false we negate it.
		check = !check;
		
		//so if the hashvalues are same and the new message is not equal to given message
		if(hashtemp.equals(hashvalue) && check)
		{
			System.out.println("a new message found which is not equal to given and has same hash value "+" --- weak collision resistance not satisfied");
			System.out.println("Given message: "+ message);
			System.out.println();
			System.out.println("its hashvalue" + hashvalue);
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Generated message: "+ newmessage);
			System.out.println();
			System.out.println("its hashvalue" + hashtemp);
			
			return true; //return true
		}
		
		return false; //else if there is no match of hash values else if messages are equal then return false.
	}
	
	//strong collision check for hash functions
	public static boolean strongcollision(String hashname)
	{	
		if(hashname.equals("Hash1")) // if it for function 1
		{
			//we generate a random number between 0 to n and make it x 
			Random rand = new Random(); 
			int temp = rand.nextInt(Hashfunctions.n); 
			int result = Hashfunctions.hashfunction1(temp); // we calculate the hashvalue for X
			
			for(int j = 0; j <= Hashfunctions.n; j++) // now check through the all n numbers and 
			{
				if(result == Hashfunctions.hashfunction1(j) && temp != j) //and if we find a same hashvalue and its not equal to the random number generator
				{
					System.out.println("Strong collision pair: (X,y) = ("+temp + " ," + j+") " +" --- strong collision resistance not satisfied"); //then we found the y to the random X hence there is a pair (X,y) that is violation of strong collision
					return true;
				}
			}
			System.out.println("it satisfies strong collision property");
			
		}
		
		//if it is for hash function 2
		else if(hashname.equals("Hash2"))
		{
			
			BitSet check1 = generateRandomBitSet(320); // generate random 320 bit
			
			for(int i = 0; i<= Math.pow(320,2); i++)
			{
				BitSet check2 = generatemessageforgiven(check1); // now generate another random 320 bits a random X and Y are with us
				// if we find then not equal and their hash functions are equal
				if((check2 != check1) && Hashfunctions.hashfunction2(check2).equals(Hashfunctions.hashfunction2(check1)))
				{
					System.out.println("it has a collision between pair (X,y) found"+" --- strong collision resistance not satisfied");
					System.out.println("Given message: "+ check1);
					System.out.println();
					System.out.println("its hashvalue" + Hashfunctions.hashfunction2(check1));
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println("Generated message: "+ check2);
					System.out.println();
					System.out.println("its hashvalue" + Hashfunctions.hashfunction2(check2));
					return true; // if we satisfy this then return true
				}
			}
			System.out.println("it satisfies strong collision property");

		}
		
		return false;
	}

	
	//----------------------------- helpers ---------------------------------------------------
	 public static BitSet generateRandomBitSet(int length)  //random bit generator for the weak and strong collision checks
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
	 
	 public static BitSet generatemessageforgiven(BitSet message) // bit generator for a given message
	 {	 
		 BitSet newMessage = new BitSet(message.length());
		 newMessage.xor(message.get(0, message.length()));
		 return newMessage; // negates the message and returns it so that we can prove weak collision as X is given in weak collision.
	 }
}
