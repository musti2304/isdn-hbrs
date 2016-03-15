package de.hbrs.se.learning.srvice;

import de.hbrs.se.learning.service.RpnExecutor;
import de.hbrs.se.learning.service.RpnParser;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RpnExecutorIT {

    private RpnExecutor executor;

    @Before
    public void setup() {
        this.executor = new RpnExecutor(new RpnParser());
    }

    @Test
    public void testRpnExecution() {
        assertThat(executor.execute("3 4 + 3 +"), is(10.0));
        assertThat(executor.execute("3 20 5 / +"), is(7.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRpnError() {
        executor.execute("1 1");
    }
}
