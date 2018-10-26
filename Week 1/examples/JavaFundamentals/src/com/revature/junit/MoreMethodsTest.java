package com.revature.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MoreMethodsTest {
	
	MoreMethods mm;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mm = new MoreMethods();
	}

	@After
	public void tearDown() throws Exception {
		mm = null;
	}

	@Test
	public void test() {
		assertTrue(mm.returnZero()==0);
	}

}
