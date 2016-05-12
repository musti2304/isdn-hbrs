package de.hbrs.se.learning.operations;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RootOperationTest extends AbstractOperationTest {
	
	RootOperation rootOperation;
//	
//	@Before 
//	public void setUp() {
//		rootOperation = new RootOperation();
//	}

	@Test
	public void secondRootOfTwentyFive() {
		testOperation(20, 2, 25, 5);
	}
	
	@Test
	public void thirdRootOfSixtyFour() {
		testOperation(20, 3, 64, 4);
	}
	
	@Test
	public void thirdRootOfHundredTwentyFive() {
		testOperation(20, 3, 125, 5);
	}
	
	@Test
	public void fourthRootOfSixteen() {
		testOperation(20, 4, 16, 2);
	}
	
	@Test
	public void fourthRootOfSixHundredTwentyFive() {
		testOperation(20, 4, 625, 5);
	}
	
	@Override
	protected RpnOperation createOperationObject() {
		return new RootOperation();
	}

}