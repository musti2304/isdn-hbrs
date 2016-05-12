package de.hbrs.se.learning.operations;

import org.junit.Test;

public class AbsoluteOperationTest extends AbstractOperationTest {

	@Override
	protected RpnOperation createOperationObject() {
		return new AbsoluteOperation();
	}
	
	@Test
	public void absValueOfMinusFive() {
		testOperation(20, -5, 5);
	}
	
	@Test
	public void absValueOfFive() {
		testOperation(20, 5, 5);
	}
}
