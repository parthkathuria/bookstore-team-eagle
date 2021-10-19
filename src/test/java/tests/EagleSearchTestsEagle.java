package tests;

import com.applitools.eyes.AccessibilityRegionType;
import com.applitools.eyes.AccessibilityStatus;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.fluent.Target;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EagleSearchTestsEagle extends EagleBaseTests {

    @Test
    public void searchByFullTitle() {
        String title = "Agile Testing";
        page.search(title);
        assertTrue(page.isBookVisible(title));
        assertEquals(1, page.getNumberOfVisibleBooks());
    }

    @Test
    public void searchResultView() {
        driver.get(System.getProperty("app.url"));
        eyes.open(driver, "Bookstore App", "Search Result View", rectangleSize);
        String title = "Agile Testing";
        page.search(title);
        eyes.check(Target.window().fully().withName("Automation Bookstore"));
        eyes.close();
    }

    @Test
    public void abTesting() {
        driver.get(System.getProperty("app.url") + "?abtest=1");
        eyes.open(driver, "Bookstore App", "AB Testing", rectangleSize);
        eyes.check(Target.window().fully().withName("AB Test Variation"));
        eyes.close();
    }

    @Test
    public void accessibilityTest() {
        eyes.open(driver, "Bookstore App", "Accessibility Testing", rectangleSize);
        eyes.check("Accessibility test", Target.window()
                .accessibility(By.className("ui-input-search"), AccessibilityRegionType.IgnoreContrast)
                .accessibility(By.cssSelector("img"), AccessibilityRegionType.IgnoreContrast));
        TestResults result = eyes.close(false);
        assertEquals(result.getAccessibilityStatus().getStatus(), AccessibilityStatus.Passed);
    }
}