package de.hbrs.se.learning.operations;

import de.hbrs.se.learning.collections.ArrayBackedStack;

public class PushOperation implements RpnOperation {
    private double value;

    public PushOperation(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void execute(ArrayBackedStack<Double> stack) {
        stack.push(value);
    }
}
