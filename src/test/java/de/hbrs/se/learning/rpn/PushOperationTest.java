package de.hbrs.se.learning.rpn;

import de.hbrs.se.learning.collections.ArrayBackedStack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PushOperationTest {

    @Mock
    ArrayBackedStack<Double> stack;

    @Test
    public void operationPushesFive() {
        RpnOperation operation = new PushOperation(5.0);

        operation.execute(stack);

        verify(stack).push(5.0);
    }

    @Test
    public void operationPushesSeven() {
        RpnOperation operation = new PushOperation(7.0);

        operation.execute(stack);

        verify(stack).push(7.0);
    }
}
