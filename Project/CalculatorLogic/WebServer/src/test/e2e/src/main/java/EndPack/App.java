import com.microsoft.playwright.*;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class App {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate("http://localhost:8080");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));

            // Expect a title "to contain" a substring.
            assertThat(page).hasTitle(Pattern.compile("Calculator"));
        }
    }
}