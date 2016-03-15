package de.hbrs.se.learning.rpn;

import de.hbrs.se.learning.collections.ArrayBackedStack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
abstract class AbstractOperationTest {

    @Mock
    protected ArrayBackedStack<Double> stack;

    protected void testOperation(int size, double lhs, double rhs, double result) {
        when(stack.size()).thenReturn(size);
        when(stack.pop()).thenReturn(lhs, rhs);

        createOperationObject().execute(stack);

        verify(stack).push(result);
    }

    @Test(expected = IllegalStateException.class)
    public void emptyStackThrowException1() {
        when(stack.size()).thenReturn(1);
        createOperationObject().execute(stack);
    }

    @Test(expected = IllegalStateException.class)
    public void emptyStackThrowException2() {
        when(stack.size()).thenReturn(0);
        createOperationObject().execute(stack);
    }

    protected abstract RpnOperation createOperationObject();

}
