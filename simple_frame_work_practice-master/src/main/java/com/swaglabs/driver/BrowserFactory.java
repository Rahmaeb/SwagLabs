package com.swaglabs.driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;
import java.util.UUID;

public class BrowserFactory {

    // Method to create and configure a WebDriver instance based on the browser name
    public static WebDriver getBrowser(String BrowserName) {
        // Convert the browser name to lowercase to handle case insensitivity
        // Throw an exception if the browser name is not supported
        return switch (BrowserName.toLowerCase()) {
            case "chrome" -> {
                // Create an instance of ChromeOptions to configure Chrome-specific settings
                final var chromeOptions = getChromeOptions();

                // Return a new ChromeDriver instance with the configured options
                yield new ChromeDriver(chromeOptions);

                // Return a new ChromeDriver instance with the configured options
            }
            case "edge" -> getEdgeDriver();
            case "firefox" -> {
                // Create an instance of FirefoxOptions to configure Firefox-specific settings
                final var firefoxOptions = getFirefoxOptions();

                // Return a new FirefoxDriver instance with the configured options
                yield new FirefoxDriver(firefoxOptions);

                // Return a new FirefoxDriver instance with the configured options
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + BrowserName);
        };
    }

    private static EdgeDriver getEdgeDriver() {
        // Create an instance of EdgeOptions to configure Edge-specific settings
        EdgeOptions edgeOptions = new EdgeOptions();

        // Add arguments to configure Edge behavior
        edgeOptions.addArguments("--start-maximized"); // Start the browser in maximized mode
        edgeOptions.addArguments("--disable-extensions"); // Disable browser extensions
        edgeOptions.addArguments("--remote-allow-origins=*"); // Allow all origins for remote debugging
        edgeOptions.addArguments("--disable-notifications"); // Disable browser notifications
        edgeOptions.addArguments("--disable-infobars"); // Disable infobars
//        edgeOptions.addArguments("--headless");
        // Generate a unique user data directory for each Edge instance
        String edgeUserDataDir = System.getProperty("java.io.tmpdir") + "edge-" + UUID.randomUUID();
        edgeOptions.addArguments("--user-data-dir=" + edgeUserDataDir);

        // Create a Map to store Edge-specific preferences
        Map<String, Object> edgePrefs = Map.of(
                "profile.default_content_setting_values.notifications", 2, // Disable notifications
                "credentials_enable_service", false, // Disable the credential manager
                "profile.password_manager_enabled", false, // Disable the password manager
                "autofill.profile_enabled", false // Disable autofill for profiles
        );

        // Set the preferences using the experimental option (use "prefs" instead of "edgeprefs")
        edgeOptions.setExperimentalOption("prefs", edgePrefs);

        // Set the page load strategy to NORMAL (wait for the entire page to load)
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        // Return a new EdgeDriver instance with the configured options
        return new EdgeDriver(edgeOptions);
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        // Set the page load strategy to NORMAL (wait for the entire page to load)
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        // Add arguments to configure Firefox behavior
        firefoxOptions.addArguments("--start-maximized"); // Start the browser in maximized mode
        firefoxOptions.addArguments("--disable-extensions"); // Disable browser extensions
        firefoxOptions.addArguments("--remote-allow-origins=*"); // Allow all origins for remote debugging
        firefoxOptions.addArguments("--disable-notifications"); // Disable browser notifications
        firefoxOptions.addArguments("--disable-infobars"); // Disable infobars
        //firefoxOptions.addArguments("--headless");

        // Allow Firefox to accept insecure (self-signed) certificates
        firefoxOptions.setAcceptInsecureCerts(true);
        return firefoxOptions;
    }

    private static ChromeOptions getChromeOptions() {
        final var chromeOptions = getOptions();

        // Create a Map to store Chrome-specific preferences
        Map<String, Object> prefs = Map.of(
                "profile.default_content_setting_values.notifications", 2, // Disable notifications
                "credentials_enable_service", false, // Disable the credential manager
                "profile.password_manager_enabled", false, // Disable the password manager
                "autofill.profile_enabled", false // Disable autofill for profiles
        );

        // Set the preferences using the experimental option
        chromeOptions.setExperimentalOption("prefs", prefs);

        // Set the page load strategy to NORMAL (wait for the entire page to load)
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return chromeOptions;
    }

    private static ChromeOptions getOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();

        // Add arguments to configure Chrome behavior
        chromeOptions.addArguments("--start-maximized"); // Start the browser in maximized mode
        chromeOptions.addArguments("--disable-extensions"); // Disable browser extensions
        chromeOptions.addArguments("--remote-allow-origins=*"); // Allow all origins for remote debugging
        chromeOptions.addArguments("--disable-notifications"); // Disable browser notifications
        chromeOptions.addArguments("--disable-infobars"); // Disable infobars (e.g., "Chrome is being controlled by automated test software")
        // chromeOptions.addArguments("--headless");
        return chromeOptions;
    }
}
