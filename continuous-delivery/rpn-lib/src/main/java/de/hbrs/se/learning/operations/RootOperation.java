package de.hbrs.se.learning.operations;

public class RootOperation extends AbstractBinaryRpnOperation {

	@Override
	protected double applyOperation(double n, double root) {
		return Math.ceil(Math.pow(n, (1/root)));
	}

}
