package de.hbrs.se.learning.rpn;

import org.junit.Test;

public class SubtractOperationTest extends AbstractOperationTest {

    @Test
    public void operationSubtractTwoAndFive() {
        testOperation(20, 5, 2, -3);
    }

    @Test
    public void operationSubtractOneAndOne() {
        testOperation(2, 1, 1, 0);
    }

    @Override
    protected RpnOperation createOperationObject() {
        return new SubtractOperation();
    }
}
