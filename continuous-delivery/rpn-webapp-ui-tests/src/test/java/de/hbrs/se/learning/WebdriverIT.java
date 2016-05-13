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
    
    
    // UITest method for add, subtract, divide and multiply operations
    @Test
    public void testAddSubMultiDivOperation() {
    	driver.findElement(By.id("expression")).clear();
    	driver.findElement(By.id("expression")).sendKeys("4 3 * 2 + 7 /");
        driver.findElement(By.id("submit")).click();
        assertThat(driver.findElement(By.id("result")).getText(), is(equalTo("2.0")));
        assertThat(driver.findElement(By.id("oldExpression")).getText(), is(equalTo("4 3 * 2 + 7 /")));
        
    }
    
    // UITest method for square root sin and absolute operations
    @Test
    public void testSqrtSinusAbsoluteOperation() {
    	driver.findElement(By.id("expression")).clear();
    	driver.findElement(By.id("expression")).sendKeys("36 sqrt sin abs");
        driver.findElement(By.id("submit")).click();
        assertThat(driver.findElement(By.id("result")).getText(), is(equalTo("0.27941549819892586")));
        assertThat(driver.findElement(By.id("oldExpression")).getText(), is(equalTo("36 sqrt sin abs")));
        
    }
    
    // UITest method for the n-th root sin and absolute operations
    @Test
    public void testNthRootSinusAbsoluteOperation() {
    	driver.findElement(By.id("expression")).clear();
    	driver.findElement(By.id("expression")).sendKeys("81 4 root sin abs");
        driver.findElement(By.id("submit")).click();
        assertThat(driver.findElement(By.id("result")).getText(), is(equalTo("0.1411200080598672")));
        assertThat(driver.findElement(By.id("oldExpression")).getText(), is(equalTo("81 4 root sin abs")));
        
    }
    
}
