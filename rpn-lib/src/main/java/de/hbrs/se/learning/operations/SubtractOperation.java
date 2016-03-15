package de.hbrs.se.learning.operations;

public class SubtractOperation extends AbstractBinaryRpnOperation {

    @Override
    protected double applyOperation(double lhs, double rhs) {
        return lhs - rhs;
    }
}
