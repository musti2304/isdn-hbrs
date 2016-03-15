package de.hbrs.se.learning.rpn;

public class SubtractOperation extends AbstractBinaryRpnOperation {

    @Override
    protected double applyOperation(double lhs, double rhs) {
        return lhs - rhs;
    }
}
