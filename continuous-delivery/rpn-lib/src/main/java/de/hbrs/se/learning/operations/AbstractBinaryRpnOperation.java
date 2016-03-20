package de.hbrs.se.learning.operations;

import de.hbrs.se.learning.collections.ArrayBackedStack;

abstract class AbstractBinaryRpnOperation extends AbstractRpnOperation {
    @Override
    public void execute(ArrayBackedStack<Double> stack) {
        assertMinSize(stack, 2);

        final double rhs = stack.pop();
        final double lhs = stack.pop();
        final double result = applyOperation(lhs, rhs);

        stack.push(result);
    }

    protected abstract double applyOperation(double lhs, double rhs);
}
