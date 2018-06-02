<<<<<<< HEAD:Project1/WordCount/test/edu/gatech/seclass/project1/AppParametersParserTest.java
package edu.gatech.seclass.project1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import edu.gatech.seclass.project1.Core;
import edu.gatech.seclass.project1.AppParametersParser;


public class AppParametersParserTest {

	private AppParametersParser appParameters = new AppParametersParser();
	
	@Before
	public void setUp() throws Exception {
		
					appParameters
                            .setDelimiterCharacter('-')
                            .AddParam("d",":?!;.")
                            .AddParam("l","4")
                            .AddParam("f","");
	}
	
	@Test
	// test a regular case
	public void testRegularCase() {
		String[] args = {"-d", ".,?!;", "-l", "5", "abc.txt"};
		appParameters.Parse(args);
		assertEquals(appParameters.ParametersKeyValue.get("-d"), ".,?!;");
		assertEquals(appParameters.ParametersKeyValue.get("-l"), "5");
		assertEquals(appParameters.ParametersKeyValue.get("-f"), "abc.txt");
	}
	@Test
	// filename at front
	public void testFilefront() {
		String[] args = {"abc.txt","-d", ".,?!;", "-l", "5"};
		appParameters.Parse(args);
		assertEquals(appParameters.ParametersKeyValue.get("-d"), ".,?!;");
		assertEquals(appParameters.ParametersKeyValue.get("-l"), "5");
		assertEquals(appParameters.ParametersKeyValue.get("-f"), "abc.txt");
	}
	
	@Test
	// filename in the middle
	public void testFileMiddle() {
		String[] args = {"-d", ".,?!;", "abcd.txt", "-l", "5"};
		appParameters.Parse(args);
		assertEquals(appParameters.ParametersKeyValue.get("-d"), ".,?!;");
		assertEquals(appParameters.ParametersKeyValue.get("-l"), "5");
		assertEquals(appParameters.ParametersKeyValue.get("-f"), "abcd.txt");
	}
	
	@Test
	// no filename provided
	public void testNoFilename() {
		String[] args = {"-d", ".,?!;", "-l", "5"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("File name not been provided for analysis!",appParameters.Error);
	}
	
	@Test
	// more arguments than required. The last value without a key is the filename
	public void testTooManyArguments() {
		String[] args = {"abc.txt","-d", ".", "!","?", "-l", "5"};
		assertEquals(true, appParameters.Parse(args));
		assertEquals(appParameters.ParametersKeyValue.get("-d"), ".");
		assertEquals(appParameters.ParametersKeyValue.get("-l"), "5");
		assertEquals(appParameters.ParametersKeyValue.get("-f"), "?");
	}
	
	@Test
	// missing value for a key
	public void testMissingKeyValue1() {
		String[] args = {"abc.txt","-d","-l", "5"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("Arguement missing for parameter -d",appParameters.Error);
	}
	
	@Test
	// missing value for a key
	public void testMissingKeyValue2() {
		String[] args = {"abc.txt","-d",".,?!;","-l"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("Arguement missing for parameter -l",appParameters.Error);
	}
	
	@Test
	// one key doensn't exist 
	public void testNonExistingKey() {
		String[] args = {"abc.txt","-x","5","-l", "5"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("Unrecognized key -x", appParameters.Error);
	}
	
	@Test
	// wordlimitlength = 0
	public void testWordLengthLimit0() {
		String[] args = {"abc.txt","-l", "0"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("Word length limit cannot be 0", appParameters.Error);
	}
	
	@Test
	// wordlimitlength is non integer
	public void testNonIntWordlength() {
		String[] args = {"abc.txt","-l", "four"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("Parameter -l should be used with integer number only", appParameters.Error);
	}
	
	@Test
	// wordlimitlength is negative
	public void testNegativeWordlength() {
		String[] args = {"abc.txt","-l", "-4"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("Unrecognized key -4", appParameters.Error);
	}
	
	
}
=======
package edu.gatech.seclass.project1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



import edu.gatech.seclass.project1.AppParametersParser;


public class AppParametersParserTest {

	private AppParametersParser appParameters = new AppParametersParser();
	
	@Before
	public void setUp() throws Exception {
		
					appParameters
                            .setDelimiterCharacter('-')
                            .AddParam("d",":?!;.")
                            .AddParam("l","4")
                            .AddParam("f","");
	}
	
	@Test
	// test a regular case
	public void testRegularCase() {
		String[] args = {"-d", ".,?!;", "-l", "5", "abc.txt"};
		appParameters.Parse(args);
		assertEquals(appParameters.ParametersKeyValue.get("-d"), ".,?!;");
		assertEquals(appParameters.ParametersKeyValue.get("-l"), "5");
		assertEquals(appParameters.ParametersKeyValue.get("-f"), "abc.txt");
	}
	@Test
	// filename at front
	public void testFilefront() {
		String[] args = {"abc.txt","-d", ".,?!;", "-l", "5"};
		appParameters.Parse(args);
		assertEquals(appParameters.ParametersKeyValue.get("-d"), ".,?!;");
		assertEquals(appParameters.ParametersKeyValue.get("-l"), "5");
		assertEquals(appParameters.ParametersKeyValue.get("-f"), "abc.txt");
	}
	
	@Test
	// filename in the middle
	public void testFileMiddle() {
		String[] args = {"-d", ".,?!;", "abcd.txt", "-l", "5"};
		appParameters.Parse(args);
		assertEquals(appParameters.ParametersKeyValue.get("-d"), ".,?!;");
		assertEquals(appParameters.ParametersKeyValue.get("-l"), "5");
		assertEquals(appParameters.ParametersKeyValue.get("-f"), "abcd.txt");
	}
	
	@Test
	// no filename provided
	public void testNoFilename() {
		String[] args = {"-d", ".,?!;", "-l", "5"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("File name not been provided for analysis!",appParameters.Error);
	}
	
	@Test
	// more arguments than required. The last value without a key is the filename
	public void testTooManyArguments() {
		String[] args = {"abc.txt","-d", ".", "!","?", "-l", "5"};
		assertEquals(true, appParameters.Parse(args));
		assertEquals(appParameters.ParametersKeyValue.get("-d"), ".");
		assertEquals(appParameters.ParametersKeyValue.get("-l"), "5");
		assertEquals(appParameters.ParametersKeyValue.get("-f"), "?");
	}
	
	@Test
	// missing value for a key
	public void testMissingKeyValue1() {
		String[] args = {"abc.txt","-d","-l", "5"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("Arguement missing for parameter -d",appParameters.Error);
	}
	
	@Test
	// missing value for a key
	public void testMissingKeyValue2() {
		String[] args = {"abc.txt","-d",".,?!;","-l"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("Arguement missing for parameter -l",appParameters.Error);
	}
	
	@Test
	// one key doensn't exist 
	public void testNonExistingKey() {
		String[] args = {"abc.txt","-x","5","-l", "5"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("Unrecognized key -x", appParameters.Error);
	}
	
	@Test
	// wordlimitlength = 0
	public void testWordLengthLimit0() {
		String[] args = {"abc.txt","-l", "0"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("Word length limit cannot be 0", appParameters.Error);
	}
	
	@Test
	// wordlimitlength is non integer
	public void testNonIntWordlength() {
		String[] args = {"abc.txt","-l", "four"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("Parameter -l should be used with integer number only", appParameters.Error);
	}
	
	@Test
	// wordlimitlength is negative
	public void testNegativeWordlength() {
		String[] args = {"abc.txt","-l", "-4"};
		assertEquals(false, appParameters.Parse(args));
		assertEquals("Unrecognized key -4", appParameters.Error);
	}
	
	
}
>>>>>>> development:Project1/test/edu/gatech/seclass/project1/AppParametersParserTest.java
