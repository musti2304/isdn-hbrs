package de.hbrs.se.learning.collections;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ArrayBackedStackTest {
    private ArrayBackedStack<Double> stack;

    @Before
    public void setup() {
        stack = new ArrayBackedStack<>();
    }

    @Test
    public void initialSizeIsZero() {
        assertThat(stack.size(), is(0));
    }

    @Test
    public void pushAndPopOneOnce() {
        stack.push(1.0);
        assertThat(stack.pop(), is(1.0));
    }

    @Test
    public void pushAndPopTwoOnce() {
        stack.push(2.0);
        assertThat(stack.pop(), is(2.0));
    }

    @Test
    public void pushAndPopTwice() {
        stack.push(3.0);
        stack.push(4.0);
        assertThat(stack.pop(), is(4.0));
        assertThat(stack.pop(), is(3.0));
    }

    @Test
    public void pushAndPopFiveTimes() {
        stack.push(5.0);
        stack.push(6.0);
        stack.push(8.0);
        stack.push(7.0);
        stack.push(5.0);
        assertThat(stack.pop(), is(5.0));
        assertThat(stack.pop(), is(7.0));
        assertThat(stack.pop(), is(8.0));
        assertThat(stack.pop(), is(6.0));
        assertThat(stack.pop(), is(5.0));
    }

    @Test
    public void sizeAfterTwoPushes() {
        stack.push(0.0);
        stack.push(0.0);
        assertThat(stack.size(), is(2));
    }

    @Test
    public void sizeAfterTwoPushesAndOnePop() {
        stack.push(0.0);
        stack.push(0.0);
        stack.pop();
        assertThat(stack.size(), is(1));
    }
}
