package com;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

class MathUtilTest {
	
	MathUtils util;
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("execute before Class in initialized");
	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		System.out.println("**Init**");
		util = new MathUtils();
		testReporter.publishEntry("Publish value to Junit console"+testInfo.getDisplayName());
		testReporter.publishEntry("Test method being called-- "+testInfo.getTestMethod());
	}
	
	@AfterEach
	void cleanUp() {
		System.out.println("**Clean Up**");
		util = null;
	}

	@Test
	@DisplayName("testing add method")
	void testAdd() {
		//MathUtils util = new MathUtils();
		int expected = 3;
		int actual = util.add(1, 2);
		// assertEquals(expected, actual);
		//assertEquals(expected, actual, "Add 2 numbers"); // on execution message Add 2 numbers will be included while displaying error
		assertEquals(expected, actual, () -> " should return "+ expected + " but returned "+actual);
		// when lambda is used as supplier, msg will be lazy loaded and displayed on failure scenarios
	}

	@Test
	void testCircleRadius() {
		//MathUtils util = new MathUtils();
		assertEquals(Math.PI * 100, util.computeCircleArea(10), "computes the circle area");
	}

	@Test
	void testDivide() {
		//MathUtils util = new MathUtils();
		boolean someCondition = true;
		assumeTrue(someCondition); // assert will be executed only if assumeTrue is true;
		assertThrows(ArithmeticException.class, () -> util.divide(1, 0), "Divide by zero error");
	}
	
	@Test
	@Tag("Math") //using tag attribute allows us to include or exclude specific tags during execution, eclipse or maven config
	void testMultiply() {
		assertAll(
				()->assertEquals(4, util.multiply(2, 2)),
				()->assertEquals(6, util.multiply(3, 2)),
				()->assertEquals(20, util.multiply(5, 4))
				);
	}
	
	// we can make use of @RepeatedTest annotation to repeat the method execution for n number of time
	@RepeatedTest(2)
	void testAddRepeatedTest(RepetitionInfo repeatInfo) {
		if(repeatInfo.getCurrentRepetition()==1) {
			assertEquals(2, util.add(1, 1));
		} else if(repeatInfo.getCurrentRepetition()==2) {
			assertEquals(4, util.add(3, 1));
		}
		
	}

	
	@Test
	@DisplayName("skip testing this method")
	@Disabled
	void testDisabled() {
		fail("fail this method");
	}
	
	//working with nested classes --
	@Nested
	@DisplayName("Nested TestAdd Class")
	class TestAdd {
		
		@Test
		void testPositiveAdd() {
			assertEquals(4, util.add(2, 2));
		}
		
		@Test
		void testNegativeAdd() {
			assertEquals(-4, util.add(-2, -2));
		}
	}
	
	
}
