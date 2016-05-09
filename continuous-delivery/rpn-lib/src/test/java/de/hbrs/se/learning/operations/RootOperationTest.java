package de.hbrs.se.learning.operations;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RootOperationTest extends AbstractOperationTest {
	
	RootOperation rootOperation;
	
	@Before 
	public void setUp() {
		rootOperation = new RootOperation();
	}

	@Test
	public void secondRootOfTwentyFive() {
		Assert.assertEquals(5.0, rootOperation.applyOperation(25.0, 2.0), 0);
	}
	
	@Test
	public void thirdRootOfSixtyFour() {
		Assert.assertEquals(4.0, rootOperation.applyOperation(64.0, 3.0), 0);
	}
	
	@Test
	public void thirdRootOfHundredTwentyFive() {
		Assert.assertEquals(5.0, rootOperation.applyOperation(125.0, 3.0), 0);
	}
	
	@Test
	public void fourthRootOfSixteen() {
		Assert.assertEquals(2.0, rootOperation.applyOperation(16.0, 4.0), 0);
	}
	
	@Test
	public void fourthRootOfSixHundredTwentyFive() {
		Assert.assertEquals(5.0, rootOperation.applyOperation(625.0, 4.0), 0);
	}
	
	@Override
	protected RpnOperation createOperationObject() {
		return new RootOperation();
	}

}