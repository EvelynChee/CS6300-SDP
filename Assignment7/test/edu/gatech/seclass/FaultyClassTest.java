package edu.gatech.seclass;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.gatech.seclass.FaultyClass;

public class FaultyClassTest {
   
	@Test
	public void Method1SC() throws Exception {
        FaultyClass faultyClass = new FaultyClass();
        faultyClass.method1(true, true);
    }

	@Test
	public void Method1BC() throws Exception {
        FaultyClass faultyClass = new FaultyClass();
        faultyClass.method1(true, true);
        faultyClass.method1(false, false);
    }
	
	@Test
	public void Method1PC() throws Exception {
        FaultyClass faultyClass = new FaultyClass();
        faultyClass.method1(true, true);
        faultyClass.method1(false, false);
        faultyClass.method1(true, false);
        faultyClass.method1(false, true);
    }
	
	@Test
	public void Method2PC() throws Exception {
        FaultyClass faultyClass = new FaultyClass();
        faultyClass.method2(1, true, true);
        faultyClass.method2(1, false, false);
        faultyClass.method2(1, true, false);
        faultyClass.method2(1, false, true);
    }
	
	@Test
	public void Method2SC() throws Exception {
        FaultyClass faultyClass = new FaultyClass();
        faultyClass.method2(1, true, true);
    }

	@Test
	public void Method2BC() throws Exception {
        FaultyClass faultyClass = new FaultyClass();
        faultyClass.method2(3, true, false);
        faultyClass.method2(3, false, true);
    }
	
}
