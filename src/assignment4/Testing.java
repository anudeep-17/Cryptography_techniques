package assignment4;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.BitSet;

public class Testing 
{
	static Hashfunctions func = new Hashfunctions();
	static hashproperties props = new hashproperties();
	
	static BitSet message = new BitSet(320);

	// Set the bits for the message
	
	@Test
	public void onewayproperty()
	{
		//test for hashfunction 1
		System.out.println("hash function 1 -------------- oneway property");
		
		int X1 = 200;
		int X2 = 1233;
		int X3 = 2123;
		int X4 = 2121;

		int hashvalue = func.hashfunction1(X1);
		int hashvalue2 = func.hashfunction1(X2);
		int hashvalue3 = func.hashfunction1(X3);
		int hashvalue4 = func.hashfunction1(X4);
		
		assertFalse(hashproperties.onewayproperty(hashvalue, X1));
		assertFalse(hashproperties.onewayproperty(hashvalue2,X2));
		assertFalse(hashproperties.onewayproperty(hashvalue3,X3));
		assertFalse(hashproperties.onewayproperty(hashvalue4,X4));
		
		//test for hashfunction 2 
		System.out.println();
		
		System.out.println("hashfunction 2 ------- oneway property");
		
		message = props.generateRandomBitSet(320);
		
		BitSet hashmessage = func.hashfunction2(message);
		
		assertFalse(props.onewayproperty(hashmessage, message));
		
		System.out.println("--------------------------------------");
		
	}
	
	@Test
	public void weakpropertytest()
	{			
		System.out.println("weak collision property of hash function 1:");
		
		
		assertTrue(hashproperties.weakcollisionproperty(200));
		assertTrue(hashproperties.weakcollisionproperty(250));
		assertTrue(hashproperties.weakcollisionproperty(300));
		assertTrue(hashproperties.weakcollisionproperty(350));
		
		System.out.println();	
		System.out.println("weak collision property of hash function 2:");
		
		message = props.generateRandomBitSet(320);
		assertTrue(hashproperties.weakcollisionproperty(message));
		
		message = props.generateRandomBitSet(320);
		assertTrue(hashproperties.weakcollisionproperty(message));

		message = props.generateRandomBitSet(320);
		assertTrue(hashproperties.weakcollisionproperty(message));

		message = props.generateRandomBitSet(320);
		assertTrue(hashproperties.weakcollisionproperty(message));
		
		System.out.println("--------------------------------------");
	}
	
	@Test
	public void Strongpropertytest()
	{		
		System.out.println("Strong collision property of hash function 1: ");		
		assertTrue(hashproperties.strongcollision("Hash1"));
		assertTrue(hashproperties.strongcollision("Hash1"));
		assertTrue(hashproperties.strongcollision("Hash1"));
		assertTrue(hashproperties.strongcollision("Hash1"));
		
		System.out.println();
		System.out.println("Strong collision property of hash function 2: ");		
		
		assertFalse(hashproperties.strongcollision("Hash2"));
		
		System.out.println("--------------------------------------");
		
	}
}
