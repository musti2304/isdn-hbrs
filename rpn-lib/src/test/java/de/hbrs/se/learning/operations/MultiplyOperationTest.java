package de.hbrs.se.learning.operations;

import org.junit.Test;

public class MultiplyOperationTest extends AbstractOperationTest {

    @Test
    public void operationMultiplyFiveAndFive() {
        testOperation(20, 5, 5, 25);
    }

    @Test
    public void operationMultiplyTwoAndFour() {
        testOperation(2, 2, 4, 8);
    }


    @Override
    protected RpnOperation createOperationObject() {
        return new MultiplyOperation();
    }
}
