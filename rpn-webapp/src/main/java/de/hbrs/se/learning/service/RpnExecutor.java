package de.hbrs.se.learning.service;

import de.hbrs.se.learning.collections.ArrayBackedStack;
import de.hbrs.se.learning.rpn.RpnOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RpnExecutor {
    private RpnParser parser;

    @Autowired
    public RpnExecutor(RpnParser parser) {
        this.parser = parser;
    }

    public double execute(String s) {
        ArrayBackedStack<Double> stack = new ArrayBackedStack<>();
        List<RpnOperation> operations = parser.parse(s);
        for (RpnOperation operation : operations) {
            operation.execute(stack);
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Final stack size is != 1: " + stack.size());
        }

        return stack.pop();
    }
}
