import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


    /**
     * @author jklouda
     * @since 28.03.2020
     */

    public class PageTest {
        public static void main (String[] args) {}
        WebDriver driver=new FirefoxDriver();

        @Test
        public void openAndSearchTextonWikiPage() {
            final String wikiPage = "https://en.wikipedia.org/wiki/Philosophical_theory";
            final String word = "Philosophy";

            openWikiPage(wikiPage);
            searchWordOnPage(word);
        }

        public void openWikiPage(final String wikiPage) {
            driver.get(wikiPage);
        }

        public void searchWordOnPage(final String word) {
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
                else
                {
                    System.out.println("Link does not contains word " + word + ".");
                    System.out.println(cnt++);
                }
            }
        }
    }
