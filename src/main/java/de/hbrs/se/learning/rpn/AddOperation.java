package de.hbrs.se.learning.rpn;

public class AddOperation extends AbstractBinaryRpnOperation {

    @Override
    protected double applyOperation(double lhs, double rhs) {
        return lhs + rhs;
    }
}
