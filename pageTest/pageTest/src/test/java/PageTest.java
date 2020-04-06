import java.util.List;
import java.lang.*;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
     * @author jklouda
     * @since 28.03.2020
     */

public class PageTest {
    public static void main (String[] args)  {
        System.setProperty("webdriver.chrome.driver", "c:/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        openAndSearchTextOnWikiPage(driver);
    }

    public static void openAndSearchTextOnWikiPage(WebDriver driver) {
        final String wikiPage = "https://en.wikipedia.org/wiki/Philosophical_theory";
        final String philosophyPage = "https://en.wikipedia.org/wiki/Philosophy";
        openWikiPage(driver, wikiPage);

        int cnt = 0;
        do {
            List<WebElement> allLinks = driver.findElements(By.tagName("a"));
            allLinks.get(1).click();
            isLinkOnPage(driver);
            System.out.println(cnt);
        } while (isLinkOnPage(driver) != true);

        closePage(driver);
    }

    public static void openWikiPage(WebDriver driver, String wikiPage) {
        driver.get(wikiPage);
    }

    public static boolean isLinkOnPage(WebDriver driver) {
        final String linkText = "Philosophy";
        List<WebElement> link = driver.findElements(By.linkText(linkText));
        if(link.size() != 0){
            System.out.println("Element present");
            return true;
        }
        else{
            System.out.println("Element not present");
            return false;
        }
    }

    public static void closePage(WebDriver driver) {
        driver.quit();
    }
}