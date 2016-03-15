package de.hbrs.se.learning.rpn;

import de.hbrs.se.learning.collections.ArrayBackedStack;

public interface RpnOperation {
    void execute(ArrayBackedStack<Double> stack);
}
