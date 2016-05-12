package de.hbrs.se.learning.operations;

public class SinOperation extends AbstractUnaryRpnOperation {

	protected double applyOperation(double lhs) {
		return Math.sin(lhs);
	}

}
