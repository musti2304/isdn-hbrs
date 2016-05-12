package de.hbrs.se.learning.operations;

import org.junit.Test;

public class SinOperationTest extends AbstractOperationTest {

	@Override
	protected RpnOperation createOperationObject() {
		return new SinOperation();
	}
	
	@Test
	public void sinOfNinety() {
		testOperation(10, 90, 0.8939966636005579);
	}
	
	@Test
	public void sinOfHundredEighty() {
		testOperation(10, 180, -0.8011526357338304);
	}
	
	@Test
	public void sinOfTwoHundredSeventy() {
		testOperation(10, 270, -0.1760459464712114);
	}

}