package de.hbrs.se.learning.rpn;

import de.hbrs.se.learning.collections.ArrayBackedStack;

abstract class AbstractRpnOperation implements RpnOperation {
    protected void assertMinSize(ArrayBackedStack<Double> stack, int minSize) {
        if (stack.size() < minSize) {
            throw new IllegalStateException("Stack must contain at least " + minSize + " elements");
        }
    }
}
