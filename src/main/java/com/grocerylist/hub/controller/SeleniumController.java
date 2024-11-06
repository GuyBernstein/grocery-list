package com.grocerylist.hub.controller;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/selenium")
public class SeleniumController {

    private static final String SELENIUM_GRID_URL = "http://localhost:4444/wd/hub";

    private WebDriver createDriver() throws Exception {
        ChromeOptions options = new ChromeOptions();
        // Basic Chrome options
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return new RemoteWebDriver(new URL(SELENIUM_GRID_URL), options);
    }

    @PostMapping("/navigate")
    public ResponseEntity<Map<String, String>> navigateToUrl(@RequestParam String url) {
        Map<String, String> response = new HashMap<>();
        WebDriver driver = null;

        try {
            driver = createDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.get(url);
            String title = driver.getTitle();

            response.put("status", "success");
            response.put("title", title);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.internalServerError().body(response);

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @PostMapping("/extract-text")
    public ResponseEntity<Map<String, String>> extractText(
            @RequestParam String url,
            @RequestParam String cssSelector) {

        Map<String, String> response = new HashMap<>();
        WebDriver driver = null;

        try {
            driver = createDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.get(url);
            WebElement element = driver.findElement(By.cssSelector(cssSelector));
            String text = element.getText();

            response.put("status", "success");
            response.put("text", text);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.internalServerError().body(response);

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @PostMapping("/click")
    public ResponseEntity<Map<String, String>> clickElement(
            @RequestParam String url,
            @RequestParam String cssSelector) {

        Map<String, String> response = new HashMap<>();
        WebDriver driver = null;

        try {
            driver = createDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.get(url);
            WebElement element = driver.findElement(By.cssSelector(cssSelector));
            element.click();

            response.put("status", "success");
            response.put("message", "Element clicked successfully");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.internalServerError().body(response);

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}