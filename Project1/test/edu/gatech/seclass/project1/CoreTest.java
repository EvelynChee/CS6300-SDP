package edu.gatech.seclass.project1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import edu.gatech.seclass.project1.Core;
import edu.gatech.seclass.project1.AppParametersParser;

public class CoreTest {

	private  Core wc; 

	/*
	@Before
	public void setUp() throws Exception {
		wc = new Core(fullFilePath);
	}

	@After
	public void tearDown() throws Exception {
		wc = null;
	}
    */
	@Test
	//	test case for blank filename
	public void testBlankFilename() {
		wc = new Core("");
		assertEquals(false, wc.Analyze());
	} 
	
	@Test
	//	test case for FileNotFoundException
	public void testFileNotExist() {
		wc = new Core("1.txt");
		assertEquals(false, wc.Analyze());
	} 

	@Test
	//	test reading file using tb1.txt
	public void testReadFile() {
		wc = new Core("testfile/tb1.txt");
		assertEquals(true, wc.Analyze());
		assertEquals (1, wc.TotalWordCount);
	} 
	@Test
	//	test setting word length limit using tb1.txt
	public void testWordLengthLimit() {
		wc = new Core("testfile/tb1.txt");
		wc.WordLetterLimit=3;
		assertEquals(true, wc.Analyze());
		assertEquals (4, wc.TotalWordCount);
		assertEquals (1, wc.TotalSentenceCount);
	} 

	@Test
	//	test using other delimiters
	public void testOtherDelimiter() {
		wc = new Core("testfile/Other_delimiter.txt");
		byte[] delimiters = null;
		String delim="a";
		delimiters = delim.getBytes();
		wc.setDelimiters(delimiters);
		assertEquals(true, wc.Analyze());
		assertEquals (4, wc.TotalWordCount);
		assertEquals (2, wc.TotalSentenceCount);
	} 
	
	@Test
	//	test sentence with 0 word count using tb2.txt
	public void test0WordCount() {
		wc = new Core("testfile/tb2.txt");
		assertEquals(true, wc.Analyze());
		assertEquals (0, wc.TotalWordCount);
		assertEquals (0, wc.TotalSentenceCount);
	} 
	
	@Test
	//test Core class:
	//	test blank file using tb0.txt
	public void testBlankFile() {
		wc = new Core("testfile/tb0.txt");
		assertEquals(true, wc.Analyze());
		assertEquals (0, wc.TotalWordCount);
		assertEquals (0, wc.TotalSentenceCount);
	} 
	
	@Test
	//test Core class:
	//	Test normal case
	public void testNormalCase() {
		wc = new Core("testfile/general.txt");
		assertEquals(true, wc.Analyze());
		assertEquals (18, wc.TotalWordCount);
		assertEquals (5, wc.TotalSentenceCount);
	} 

	
	@Test
	//	test for different word delimiters. Test tab, space, carriage return, linefeed as word delimiter
	public void testWordDlimiter() {
		wc = new Core("testfile/delimit_word.txt");
		wc.WordLetterLimit=4;
		assertEquals(true, wc.Analyze());
		assertEquals (3, wc.TotalWordCount);
		assertEquals (1, wc.TotalSentenceCount);
	} 
	
	@Test
	//	test for ASCII code collections
	public void testASCIIcode() {
		wc = new Core("testfile/ASCII.txt");
		wc.WordLetterLimit=1;
		byte[] delimiters = null;
		String delim=".";
		delimiters = delim.getBytes();
		wc.setDelimiters(delimiters);
		assertEquals(true, wc.Analyze());
		assertEquals (220, wc.TotalWordCount);
		assertEquals (2, wc.TotalSentenceCount);
	} 
	
	 @Test
     //Test program runs on docx file
     public void testDocxFile() {
         wc = new Core("testfile/docx.docx");
         assertEquals(true, wc.Analyze());
         assertEquals (2, wc.TotalWordCount);
         assertEquals (2, wc.TotalSentenceCount);
     }
    
     @Test
     // Test program runs on pdf file
     public void testPDFFile() {
         wc = new Core("testfile/pdf.pdf");
         assertEquals(true, wc.Analyze());
         assertEquals (1, wc.TotalWordCount);
         assertEquals (1, wc.TotalSentenceCount);
     }
    
     @Test
     // Test program runs on gif file
     public void testgifFile() {
         wc = new Core("testfile/gif.gif");
         assertEquals(true, wc.Analyze());
         assertEquals (1, wc.TotalWordCount);
         assertEquals (1, wc.TotalSentenceCount);
     }
    
    
     @Test
     // Test last sentence end with no delimiter
     public void testLSNDFile() {
         wc = new Core("testfile/last sentence no end delimiter.txt");
         assertEquals(true, wc.Analyze());
         assertEquals (4, wc.TotalWordCount);
         assertEquals (2, wc.TotalSentenceCount);
     }
    
     @Test
 	//	Test sequence other than characters counted as word
 	public void testOtherSequence() {
 		wc = new Core("testfile/digit word.txt");
 		byte[] delimiters = null;
 		String delim="$";
 		delimiters = delim.getBytes();
 		wc.setDelimiters(delimiters);
 		assertEquals(true, wc.Analyze());
 		assertEquals (1, wc.TotalWordCount);
 		assertEquals (1, wc.TotalSentenceCount);
 	} 
     
     
 	
}

