package tests;

import com.applitools.eyes.AccessibilityGuidelinesVersion;
import com.applitools.eyes.AccessibilityLevel;
import com.applitools.eyes.AccessibilitySettings;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SearchPage;
import utils.EagleVisualGridConfig;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EagleBaseTests {

    protected static WebDriver driver;
    protected static SearchPage page;
    protected static Eyes eyes;
    protected static RectangleSize rectangleSize;
    protected static Configuration suiteConfig;
    protected static VisualGridRunner runner;

    @BeforeClass
    public static void setUp() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        WebDriverManager.chromedriver().setup();
        Properties props = System.getProperties();
        try {
            props.load(
                    new FileInputStream(new File("resources/test.properties")));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        driver = new ChromeDriver();
        driver.get(System.getProperty("app.url"));
        page = new SearchPage(driver);
        suiteConfig = EagleVisualGridConfig.getGrid("Bookstore App");
        suiteConfig
                .setAccessibilityValidation(
                        new AccessibilitySettings(AccessibilityLevel.AA,
                                AccessibilityGuidelinesVersion.WCAG_2_0));
        runner = new VisualGridRunner(new RunnerOptions().testConcurrency(5));
        runner.setApiKey(System.getProperty("applitools.api.key"));
        eyes = new Eyes(runner);
        eyes.setConfiguration(suiteConfig);
        rectangleSize = new RectangleSize(1200, 800);
    }


    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
