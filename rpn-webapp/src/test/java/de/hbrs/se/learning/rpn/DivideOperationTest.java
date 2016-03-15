package de.hbrs.se.learning.rpn;

import org.junit.Test;

public class DivideOperationTest extends AbstractOperationTest {

    @Test
    public void operationDivideFiveAndTwoPointFive() {
        testOperation(20, 2.5, 5, 2);
    }

    @Test
    public void operationDivideOneAndOne() {
        testOperation(2, 1, 1, 1);
    }


    @Override
    protected RpnOperation createOperationObject() {
        return new DivideOperation();
    }
}
