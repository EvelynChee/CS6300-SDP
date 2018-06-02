package edu.gatech.seclass;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyWeirdStringTest {

	private MyWeirdStringInterface myweirdstring;

	@Before
	public void setUp() throws Exception {
		myweirdstring = new MyWeirdString();
	}

	@After
	public void tearDown() throws Exception {
		myweirdstring = null;
	}
    
	//Test whether method countDigits works for any valid string as we expected
	@Test
	public void testCountDigits1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		assertEquals(5, myweirdstring.countDigits());
	}
    //Test no digits: whether method countDigits returns 0 for no digits
	@Test
	public void testCountDigits2() {
		myweirdstring.setWeirdString("There is no digits in this string!");
		assertEquals(0, myweirdstring.countDigits());
	}
    //Test empty: whether method countDigits returns 0 for empty string
	@Test
	public void testCountDigits3() {
		myweirdstring.setWeirdString("");
		assertEquals(0, myweirdstring.countDigits());
	}
    
	//Test null: whether method countDigits returns 0 for null string as I defined
	@Test
	public void testCountDigits4() {
		myweirdstring.setWeirdString(null);
		assertEquals(0, myweirdstring.countDigits());
	}
    
	//Test null: whether method countDigits can count all the digits
	@Test
	public void testCountDigits5() {
		myweirdstring.setWeirdString("All digits are here: 0123456789");
		assertEquals(10, myweirdstring.countDigits());
	}
	
	//Test whether method getEvenCharacters works for any valid string as we expected
	@Test
	public void testGetEvenCharacters1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		assertEquals("' etrptsm 1isi hs5rn,rgt", myweirdstring.getEvenCharacters());
	}

	//Test null: whether method getEvenCharacters returns null for null string
	@Test
	public void testGetEvenCharacters2() {
		myweirdstring.setWeirdString(null);
		assertNull(myweirdstring.getEvenCharacters());
	}

	//Test empty: whether method getEvenCharacters returns empty for empty string
	@Test
	public void testGetEvenCharacters3() {
		myweirdstring.setWeirdString("");
		assertEquals("", myweirdstring.getEvenCharacters());
	}

	//Test after running the convertDigitsToRomanNumeralsInSubstring, 
	//whether method getEvenCharacters can process the updated current string
	@Test
	public void testGetEvenCharacters4() {
		myweirdstring.setWeirdString("0str7ing");
		myweirdstring.convertDigitsToRomanNumeralsInSubstring(1, 6);
		assertEquals("srIig", myweirdstring.getEvenCharacters());
	}
	
	//Test 1 string: whether method getEvenCharacters returns empty for 1 string
	@Test
	public void testGetEvenCharacters5() {
		myweirdstring.setWeirdString("A");
		assertEquals("", myweirdstring.getEvenCharacters());
	}
	    
	//Test whether method getOddCharacters works for any valid string as we expected
	@Test
	public void testGetOddCharacters1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		assertEquals("Idbte u 0edgt nti t19 ih?", myweirdstring.getOddCharacters());
	}
    
	//Test null: whether method getOddCharacters returns null for null string
	@Test
	public void testGetOddCharacters2() {
		myweirdstring.setWeirdString(null);
		assertNull(myweirdstring.getOddCharacters());
	}
    
	//Test empty: whether method getOddCharacters returns empty for empty string
	@Test
	public void testGetOddCharacters3() {
		myweirdstring.setWeirdString("");
		assertEquals("", myweirdstring.getOddCharacters());
	}
    
    //Test after running the convertDigitsToRomanNumeralsInSubstring method, 
	//whether the getOddCharacters can process the updated current string
	@Test
	public void testGetOddCharacters4() {
		myweirdstring.setWeirdString("this1string");
		myweirdstring.convertDigitsToRomanNumeralsInSubstring(3, 6);
		assertEquals("tiItig", myweirdstring.getOddCharacters());
	}
    
	//Test 1 string: whether method getOddCharacters returns itself for 1 string
	@Test
	public void testGetOddCharacters5() {
		myweirdstring.setWeirdString("A");
		assertEquals("A", myweirdstring.getOddCharacters());
	}
	
	//Test if ConvertDigitsToRomanNumeralsInSubstring method
	//works for any valid string as we expected
	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring1() {
		myweirdstring.setWeirdString("I'd better put s0me d1gits in this 5tr1n9, right?");
		myweirdstring.convertDigitsToRomanNumeralsInSubstring(40, 45);
		assertEquals("I'd better put s0me d1gits in this 5tr1nIX, right?", myweirdstring.getWeirdString());
	}
    
	
	//This test checks whether method testConvertDigitsToRomanNumeralsInSubstring2 
	//suitably throws an IllegalArgumentException 
	//if startPosition is greater than endPosition (both are within bounds)
	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring2() {
		
		try {
			myweirdstring.setWeirdString("This is a 0123 string I want to test");
			myweirdstring.convertDigitsToRomanNumeralsInSubstring(15, 10);
		    fail("Start position cannot be greater than end position");
		  } catch(IllegalArgumentException e) {
		    // expected
		  }
	}
    
	//This test checks whether method testConvertDigitsToRomanNumeralsInSubstring3 
	//suitably throws an MyIndexOutOfBoundsException 
	//if  string is null 
	@Test(expected = MyIndexOutOfBoundsException.class)
	public void testConvertDigitsToRomanNumeralsInSubstring3() {
		myweirdstring.setWeirdString(null);
		myweirdstring.convertDigitsToRomanNumeralsInSubstring(1, 2);
	}
	
	//This test checks whether method testConvertDigitsToRomanNumeralsInSubstring4 
	//suitably throws an MyIndexOutOfBoundsException 
	//if  string is empty 
	@Test(expected = MyIndexOutOfBoundsException.class)
	public void testConvertDigitsToRomanNumeralsInSubstring4() {
		myweirdstring.setWeirdString("");
		myweirdstring.convertDigitsToRomanNumeralsInSubstring(1, 2);
	}
	
	
	//This test checks whether method testConvertDigitsToRomanNumeralsInSubstring5 
	//suitably throws an MyIndexOutOfBoundsException 
	//if  string's start position is less than 1 	
	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring5() {
		try {
			myweirdstring.setWeirdString("This is a 0123 string I want to test");
			myweirdstring.convertDigitsToRomanNumeralsInSubstring(0, 1);
		    fail("Start and end position must be at least 1");
		  } catch(MyIndexOutOfBoundsException e) {
		    // expected
		  }
	}

	
	//This test checks whether method testConvertDigitsToRomanNumeralsInSubstring6
	//suitably throws an MyIndexOutOfBoundsException
	//if string's end position greater then the length of the string
	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring6() {
		try {
			myweirdstring.setWeirdString("string");
			myweirdstring.convertDigitsToRomanNumeralsInSubstring(2, 20);
		    fail("Start and end position cannot be greater than the length of string");
		  } catch(MyIndexOutOfBoundsException e) {
		    // expected
		  }
	}
		
	//This test checks whether method testConvertDigitsToRomanNumeralsInSubstring7 
	//suitably throws an MyIndexOutOfBoundsException 
	//if  string's end position is less than 1 	
	@Test
	public void testConvertDigitsToRomanNumeralsInSubstring7() {
		try {
			myweirdstring.setWeirdString("This is a 0123 string I want to test");
			myweirdstring.convertDigitsToRomanNumeralsInSubstring(1, 0);
			fail("Start and end position must be at least 1");
		  } catch(MyIndexOutOfBoundsException e) {
			    // expected
		  }
	 }
			

}
