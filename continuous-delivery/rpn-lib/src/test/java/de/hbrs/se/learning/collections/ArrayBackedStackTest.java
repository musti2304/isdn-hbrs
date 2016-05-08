package de.hbrs.se.learning.collections;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayBackedStackTest {
    private ArrayBackedStack<Double> stack;

    @Before
    public void setup() {
        stack = new ArrayBackedStack<>();
    }

    @Test
    public void initialSizeIsZero() {
        Assert.assertThat(stack.size(), CoreMatchers.is(0));
    }

    @Test
    public void pushAndPopOneOnce() {
        stack.push(1.0);
        Assert.assertThat(stack.pop(), CoreMatchers.is(1.0));
    }

    @Test
    public void pushAndPopTwoOnce() {
        stack.push(2.0);
        Assert.assertThat(stack.pop(), CoreMatchers.is(2.0));
    }

    @Test
    public void pushAndPopTwice() {
        stack.push(3.0);
        stack.push(4.0);
        Assert.assertThat(stack.pop(), CoreMatchers.is(4.0));
        Assert.assertThat(stack.pop(), CoreMatchers.is(3.0));
    }

    @Test
    public void pushAndPopFiveTimes() {
        stack.push(5.0);
        stack.push(6.0);
        stack.push(8.0);
        stack.push(7.0);
        stack.push(5.0);
        Assert.assertThat(stack.pop(), CoreMatchers.is(5.0));
        Assert.assertThat(stack.pop(), CoreMatchers.is(7.0));
        Assert.assertThat(stack.pop(), CoreMatchers.is(8.0));
        Assert.assertThat(stack.pop(), CoreMatchers.is(6.0));
        Assert.assertThat(stack.pop(), CoreMatchers.is(5.0));
    }

    @Test
    public void sizeAfterTwoPushes() {
        stack.push(0.0);
        stack.push(0.0);
        Assert.assertThat(stack.size(), CoreMatchers.is(2));
    }

    @Test
    public void sizeAfterTwoPushesAndOnePop() {
        stack.push(0.0);
        stack.push(0.0);
        stack.pop();
        Assert.assertThat(stack.size(), CoreMatchers.is(1));
    }

    @Test(expected = IllegalStateException.class)
    public void popOnEmptyStackYieldsException() {
        stack.pop();
    }

    @Test
    public void stackIsGeneric() {
        ArrayBackedStack<String> otherStack = new ArrayBackedStack<>();
        otherStack.push("Hello");
        otherStack.push("World");
        Assert.assertThat(otherStack.pop(), CoreMatchers.is(CoreMatchers.equalTo("World")));
    }
    
    @Test
    public void stackHasRightCapacity() {
    	stack.push(1.0);
    	Assert.assertThat(stack.getCapacity(), CoreMatchers.is(2));
    	stack.push(2.0);
    	Assert.assertThat(stack.getCapacity(), CoreMatchers.is(4));
    	stack.push(3.0);
    	Assert.assertThat(stack.getCapacity(), CoreMatchers.is(4));
    	stack.push(4.0);
    	Assert.assertThat(stack.getCapacity(), CoreMatchers.is(8));
    }
    
}
