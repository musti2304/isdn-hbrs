package de.hbrs.se.learning.operations;

public class AbsoluteOperation extends AbstractUnaryRpnOperation {

	@Override
	protected double applyOperation(double lhs) {
		return Math.abs(lhs);
	}

}
