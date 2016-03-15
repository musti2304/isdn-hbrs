package de.hbrs.se.learning.rpnparser;

import de.hbrs.se.learning.operations.AddOperation;
import de.hbrs.se.learning.operations.DivideOperation;
import de.hbrs.se.learning.operations.MultiplyOperation;
import de.hbrs.se.learning.operations.PushOperation;
import de.hbrs.se.learning.operations.RpnOperation;
import de.hbrs.se.learning.operations.SubtractOperation;
import de.hbrs.se.learning.service.RpnParser;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.hamcrest.collection.IsIterableWithSize;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class RpnParserTest {

    private RpnParser parser;

    @Before
    public void setup() {
        parser = new RpnParser();
    }

    @Test
    public void emptyStringYieldsEmptyList() {
        Assert.assertThat(parser.parse(""), CoreMatchers.is(IsIterableWithSize.iterableWithSize(0)));
        Assert.assertThat(parser.parse("   "), CoreMatchers.is(IsIterableWithSize.iterableWithSize(0)));
    }

    @Test
    public void plusIsTransformed() {
        Assert.assertThat(parser.parse("+"), containsOperations(Matchers.any(AddOperation.class)));
    }

    @Test
    public void multiplyIsTransformed() {
        Assert.assertThat(parser.parse("*"), containsOperations(Matchers.any(MultiplyOperation.class)));
    }

    @Test
    public void divideIsTransformed() {
        Assert.assertThat(parser.parse("/"), containsOperations(Matchers.any(DivideOperation.class)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unknownStringsThrowException() {
        parser.parse("+ -1");
    }

    @Test
    public void plusAndMinusIsTransformed() {
        Assert.assertThat(parser.parse(" -      +"), containsOperations(Matchers.any(SubtractOperation.class), Matchers.any(AddOperation.class)));
    }

    public Matcher containsOperations(Matcher ... operations) {
        return IsIterableContainingInOrder.contains(operations);
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
        Assert.assertThat(op, CoreMatchers.is(CoreMatchers.instanceOf(PushOperation.class)));
        Assert.assertThat(((PushOperation) op).getValue(), CoreMatchers.is(result));
    }
}
