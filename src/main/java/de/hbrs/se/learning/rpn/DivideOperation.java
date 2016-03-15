package de.hbrs.se.learning.rpn;

public class DivideOperation extends AbstractBinaryRpnOperation {
    @Override
    protected double applyOperation(double lhs, double rhs) {
        return lhs / rhs;
    }
}
