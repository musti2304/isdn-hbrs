package de.hbrs.se.learning.operations;

import org.junit.Test;

public class SquareRootOperationTest extends AbstractOperationTest {

	@Override
	protected RpnOperation createOperationObject() {
		return new SquareRootOperation();
	}

	@Test
	public void sqrtOftwentyFive() {
		testOperation(10, 25, 5);
	}
	
	@Test
	public void sqrtOfEightyOne() {
		testOperation(10, 81, 9);
	}
	
	@Test
	public void sqrtOfThirtySix() {
		testOperation(10, 36, 6);
	}
	
	@Test
	public void sqrtOfHundredTwentyOne() {
		testOperation(10, 121, 11);
	}
}