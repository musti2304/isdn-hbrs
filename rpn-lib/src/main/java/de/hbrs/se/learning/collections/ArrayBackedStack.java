package de.hbrs.se.learning.collections;

public class ArrayBackedStack<E> {
    private int nextFreePosition = 0;
    private E[] items;

    @SuppressWarnings("unchecked")
    public ArrayBackedStack() {
        items = (E[]) new Object[2];
    }

    public int size() {
        return nextFreePosition;
    }

    public void push(E item) {
        assureFreeCapacity();
        this.items[nextFreePosition] = item;
        nextFreePosition++;
    }

    @SuppressWarnings("unchecked")
    private void assureFreeCapacity() {
        if (nextFreePosition == items.length) {
            E[] newItems = (E[]) new Object[items.length * 2];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
    }

    public E pop() {
        if (nextFreePosition == 0)
            throw new IllegalStateException("Stack is empty");

        nextFreePosition--;
        E result = items[nextFreePosition];
        items[nextFreePosition] = null;
        return result;
    }
}
