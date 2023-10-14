package org.saurabh.users;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.assertj.core.api.Assertions.assertThat;

class JavalinOpenApiExampleAppE2ESpec {

    private final Javalin app = new JavalinOpenApiExampleApp().app; // inject any dependencies you might have

    @Test
    void UI_contains_correct_heading() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        JavalinTest.test(app, (server, client) -> {
            WebDriverManager.chromedriver().setup();
            var options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            WebDriver driver = new ChromeDriver(options);
            driver.get(client.getOrigin() + "/ui");
            assertThat(driver.getPageSource()).contains("<h1>User UI</h1>");
            driver.quit();
        });
    }

}