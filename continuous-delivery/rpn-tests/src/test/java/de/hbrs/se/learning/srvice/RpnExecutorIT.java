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
        // Add operation
    	assertThat(executor.execute("3 4 + 3 +"), is(10.0));
    	
    	// Subtract operation
        assertThat(executor.execute("10 5 - 2 -"), is(3.0));
        
        // Add and divide operation
        assertThat(executor.execute("3 20 5 / +"), is(7.0));
        
        // Multiply operation
        assertThat(executor.execute("4 2 * 4 *"), is(32.0));
        
        // Square root operation
        assertThat(executor.execute("25 sqrt"), is(5.0));
        
        // Add, divide and sinus operation
        assertThat(executor.execute("100 2 / 40 + sin"), is(0.8939966636005579));
        
        // Subtract and absolute operation
        assertThat(executor.execute("10 20 - abs"), is(10.0));
        
        // Square root sin and absolute operation
        assertThat(executor.execute("36 sqrt sin abs"), is(0.27941549819892586));
        
        // N-th root operation combined with square root sin and absolute operation
        assertThat(executor.execute("81 4 root sin abs"), is(0.1411200080598672));
    }
    

    @Test(expected = IllegalArgumentException.class)
    public void testRpnError() {
        executor.execute("1 1");
    }
}