package de.hbrs.se.learning.operations;

public class AbsoluteOperation extends AbstractUnaryRpnOperation {

	@Override
	protected double applyOperation(double lhs) {
		if(lhs < 0) {
			return Math.abs(lhs);
		} else {
			return Math.abs(lhs);
		}
	}
}