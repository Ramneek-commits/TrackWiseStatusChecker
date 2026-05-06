package com.trackwise;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Selenium UI tests for TrackWise Status Checker
 */
public class TaskStatusCheckerSeleniumTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testCompletedStatus() {
        openPage();

        driver.findElement(By.id("studentId")).sendKeys("S101");
        driver.findElement(By.id("taskId")).sendKeys("T1");
        driver.findElement(By.id("checkButton")).click();

        assertEquals("Completed", getResult());
    }

    @Test
    public void testNeedResubmissionStatus() {
        openPage();

        driver.findElement(By.id("studentId")).sendKeys("S103");
        driver.findElement(By.id("taskId")).sendKeys("T3");
        driver.findElement(By.id("checkButton")).click();

        assertEquals("Need Resubmission", getResult());
    }

    @Test
    public void testInvalidStudentId() {
        openPage();

        driver.findElement(By.id("studentId")).sendKeys("ABC");
        driver.findElement(By.id("taskId")).sendKeys("T1");
        driver.findElement(By.id("checkButton")).click();

        assertEquals("Invalid Student ID", getResult());
    }

    @Test
    public void testEmptyStudentId() {
        openPage();

        driver.findElement(By.id("taskId")).sendKeys("T1");
        driver.findElement(By.id("checkButton")).click();

        assertEquals("Student ID cannot be empty", getResult());
    }


    private void openPage() {
        File file = new File("src/main/resources/trackwise.html");
        driver.get(file.toURI().toString());
    }

    private String getResult() {
        WebElement result = driver.findElement(By.id("result"));
        return result.getText();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}