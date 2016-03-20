package de.hbrs.se.learning.operations;

public class MultiplyOperation extends AbstractBinaryRpnOperation {
    @Override
    protected double applyOperation(double lhs, double rhs) {
        return lhs * rhs;
    }
}
