package edu.gatech.seclass;

import java.util.HashMap;
import java.util.Map;

public class MyWeirdString implements MyWeirdStringInterface {

	private String weirdString = null;
	private String evenCharacters = null;
	private String oddCharacters = null;
	private int digits = 0;
	private boolean processed = false;
	private static final Map<Character, String> romanDigits = new HashMap<Character, String>();
	static {
		romanDigits.put('1', "I");
		romanDigits.put('2', "II");
		romanDigits.put('3', "III");
		romanDigits.put('4', "IV");
		romanDigits.put('5', "V");
		romanDigits.put('6', "VI");
		romanDigits.put('7', "VII");
		romanDigits.put('8', "VIII");
		romanDigits.put('9', "IX");
	}
	
	@Override
	public void setWeirdString(String string) {
		this.weirdString = string;
		this.processed = false;
	}

	@Override
	public String getWeirdString() {
		return this.weirdString;
	}

	@Override
	public String getEvenCharacters() {
		if (!processed) {
			processCharacters();
		}
		return evenCharacters;
	}

	@Override
	public String getOddCharacters() {
		if (!processed) {
			processCharacters();
		}
		return oddCharacters;
	}

	private void processCharacters() {
		oddCharacters = null;
		evenCharacters = null;
		digits = 0;
		
		if (weirdString == null) {
			processed = true;
			return;
		}
		
		StringBuffer evenCharsBuffer = new StringBuffer();
		StringBuffer oddCharsBuffer = new StringBuffer();
		for (int i = 0; i < weirdString.length(); i++) {
			char c = weirdString.charAt(i);
			if (i % 2 == 0) {
				oddCharsBuffer.append(c);
			} else {
				evenCharsBuffer.append(c);
			}
			
			if (c >= '0' && c <= '9') {
				digits++;
			}
		}
		
		evenCharacters = evenCharsBuffer.toString();
		oddCharacters = oddCharsBuffer.toString();
		processed = true;
	}
	
	@Override
	public int countDigits() {
		if (!processed) {
			processCharacters();
		}
		
		return digits;
	}

	@Override
	public void convertDigitsToRomanNumeralsInSubstring(int startPosition,
			int endPosition) throws MyIndexOutOfBoundsException,
			IllegalArgumentException {
		if (this.weirdString == null || this.weirdString.isEmpty()) {
			throw new MyIndexOutOfBoundsException("String is null or empty");
		}
		
		if (startPosition < 1 || endPosition < 1) {
			throw new MyIndexOutOfBoundsException("Start and end position must be at least 1");
		}

		if (startPosition > this.weirdString.length() || endPosition > this.weirdString.length()) {
			throw new MyIndexOutOfBoundsException("Start and end position cannot be greater than the length of string");
		}
		
		if (startPosition > endPosition) {
			throw new IllegalArgumentException("Start position cannot be greater than end position");
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < weirdString.length(); i++) {
			char c = weirdString.charAt(i);
			if (i < (startPosition - 1) || i >= endPosition) {
				sb.append(c);
			} else if (c >= '1' && c <= '9') {
				sb.append(romanDigits.get(c));
			} else {
				sb.append(c);
			}
		}
		weirdString = sb.toString();
		processed = false;
	}

}
