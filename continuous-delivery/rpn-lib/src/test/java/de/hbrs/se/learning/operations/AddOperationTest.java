package de.hbrs.se.learning.operations;

import org.junit.Test;


public class AddOperationTest extends AbstractOperationTest {

    @Test
    public void operationAddsTwoAndFive() {
        testOperation(20, 2, 5, 7);
    }

    @Test
    public void operationAddsOneAndOne() {
        testOperation(2, 1, 1, 2);
    }


    @Override
    protected RpnOperation createOperationObject() {
        return new AddOperation();
    }
}
