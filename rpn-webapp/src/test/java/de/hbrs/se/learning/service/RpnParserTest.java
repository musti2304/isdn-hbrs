package de.hbrs.se.learning.service;

import de.hbrs.se.learning.rpn.*;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unchecked")
public class RpnParserTest {

    private RpnParser parser;

    @Before
    public void setup() {
        parser = new RpnParser();
    }

    @Test
    public void emptyStringYieldsEmptyList() {
        assertThat(parser.parse(""), is(iterableWithSize(0)));
        assertThat(parser.parse("   "), is(iterableWithSize(0)));
    }

    @Test
    public void plusIsTransformed() {
        assertThat(parser.parse("+"), containsOperations(any(AddOperation.class)));
    }

    @Test
    public void multiplyIsTransformed() {
        assertThat(parser.parse("*"), containsOperations(any(MultiplyOperation.class)));
    }

    @Test
    public void divideIsTransformed() {
        assertThat(parser.parse("/"), containsOperations(any(DivideOperation.class)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unknownStringsThrowException() {
        parser.parse("+ -1");
    }

    @Test
    public void plusAndMinusIsTransformed() {
        assertThat(parser.parse(" -      +"), containsOperations(any(SubtractOperation.class), any(AddOperation.class)));
    }

    public Matcher containsOperations(Matcher... operations) {
        return contains(operations);
    }

    @Test
    public void parseDoubleValue1() {
        assertParseDouble("1", 1);
    }

    @Test
    public void parseDoubleValue2() {
        assertParseDouble(".5", .5);
    }

    @Test
    public void parseDoubleValue3() {
        assertParseDouble("4.5", 4.5);
    }

    private void assertParseDouble(String expression, double result) {
        RpnOperation op = parser.parse(expression).get(0);
        assertThat(op, is(instanceOf(PushOperation.class)));
        assertThat(((PushOperation) op).getValue(), is(result));
    }
}
