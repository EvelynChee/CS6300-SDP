package edu.gatech.seclass;

public class FaultyClass {
		
	public int method1(boolean condition1, boolean condition2) {
		  int x = 1;
		  if (condition1) 
		    x=x-1;
		  if (condition2) 
		    x=x-1;
		  return 5/x;
		}
	
	
	public int method2(int input, boolean condition1, boolean condition2) {
		  int x = input;
		  if (condition1) 
		    x=x-3;
		  if (condition2) 
		    x=x-3;
		  return 5/x;
		}
	
	
	public int method3(int x) {
		boolean C = true;
		if (C = false){
		  return 5/x;
		}
		return x;
	}
	

	public void method4() {
//		It is not possible to create such a method, 
//		because the branch coverage subsumes the statement coverage, 
//		so 100% branch coverage means 100% statement coverage. 
//		It is not possible to create such a method that has a test 
//		suite achieves 100% branch coverage and does not reveal the 
//		fault, but every test suite that achieves 100% statement coverage 
//		reveals the fault. 
	}
}
