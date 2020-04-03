import java.util.List;
import java.lang.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
     * @author jklouda
     * @since 28.03.2020
     */

public class PageTest {
    public static void main (String[] args)  {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        openAndSearchTextOnWikiPage(driver);
    }

    public static void openAndSearchTextOnWikiPage(WebDriver driver) {
        final String wikiPage = "https://en.wikipedia.org/wiki/Philosophical_theory";
        final String word = "Philosophy";

        openWikiPage(driver, wikiPage);
        searchWordOnPage(driver, word);
        closeWikiPage(driver);
    }

    public static void openWikiPage(WebDriver driver, String wikiPage) {
        driver.get(wikiPage);
    }

    public static void searchWordOnPage(WebDriver driver, String word) {
        int cnt = 0;
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links)
        {
            if (link.getAttribute("title").contains(word))
            {
                link.click();
                System.out.println("Link contains word " + word + ".");
                System.out.println(cnt++);
                break;
            }
            else {
                System.out.println("Link does not contains word " + word + ".");
                System.out.println(cnt++);
            }
        }
    }

    public static void closeWikiPage(WebDriver driver) {
        driver.close();
    }
}
