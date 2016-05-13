package de.hbrs.se.learning.operations;

import de.hbrs.se.learning.collections.ArrayBackedStack;

public abstract class AbstractUnaryRpnOperation extends AbstractRpnOperation {
	 public void execute(ArrayBackedStack<Double> stack) {
	        assertMinSize(stack, 1);

	        final double lhs = stack.pop();
	        final double result = applyOperation(lhs);

	        stack.push(result);
	    }

	    protected abstract double applyOperation(double lhs);
}
