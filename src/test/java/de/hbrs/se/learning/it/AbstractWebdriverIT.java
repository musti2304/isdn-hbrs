package de.hbrs.se.learning.it;

import de.hbrs.se.learning.Main;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebIntegrationTest
public abstract class AbstractWebdriverIT {

    @Autowired
    private EmbeddedWebApplicationContext server;

    @Value("${local.server.port}")
    private int port;

    protected static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = new HtmlUnitDriver();
        // driver = new FirefoxDriver();
    }

    @AfterClass
    public static void teardown() {
        driver.close();
        driver.quit();
    }

    @Before
    public void gotoStartPage() {
        driver.get("http://localhost:" + port + "/");
    }
}
