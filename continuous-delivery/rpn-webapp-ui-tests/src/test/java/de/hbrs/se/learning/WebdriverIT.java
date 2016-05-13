package de.hbrs.se.learning;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.springframework.test.context.TestExecutionListeners;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class WebdriverIT extends AbstractWebdriverIT {

    @Test
    public void simpleOperationTest() {
        driver.findElement(By.id("expression")).clear();
        driver.findElement(By.id("expression")).sendKeys("3 2 +");
        driver.findElement(By.id("submit")).click();
        assertThat(driver.findElement(By.id("result")).getText(), is(equalTo("5.0")));
        assertThat(driver.findElement(By.id("oldExpression")).getText(), is(equalTo("3 2 +")));
    } 

    @Test
    public void simpleFailureTest() {
        driver.findElement(By.id("expression")).clear();
        driver.findElement(By.id("expression")).sendKeys("error!");
        driver.findElement(By.id("submit")).click();
        assertThat(driver.findElement(By.id("errorMessage")).getText(), is(equalTo("Error: Unknown string: error!")));
    }
    
    // TODO
    // Implement UI Tests for different operations
    @Test
    public void testNewOperation() {
    	driver.findElement(By.id("expression")).clear();
    	driver.findElement(By.id("expression")).sendKeys("");
        driver.findElement(By.id("submit")).click();
        assertThat(driver.findElement(By.id("result")).getText(), is(equalTo("")));
        assertThat(driver.findElement(By.id("oldExpression")).getText(), is(equalTo("")));
        
    }

}
