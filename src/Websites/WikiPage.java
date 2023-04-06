package Websites;

import Instances.Czasownik;
import Instances.Word;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class WikiPage {
    private WebDriver driver;
    private Word word;
    private String url;

    public Word getWord(){
        return this.word;
    }

    public void readFromPage() {
        boolean hasNextPage = true;
        while (hasNextPage) {
            driver.get(url);
            List<WebElement> bigDiv = driver.findElements(By.xpath("//*[@id=\"mw-pages\"]/div/div"));
            for (WebElement verbLink : bigDiv) {
                List<WebElement> smallDiv = driver.findElements(By.xpath("//*[@id=\"mw-pages\"]/div/div/div"));
                for (WebElement smallLink : smallDiv) {
                    List<WebElement> ul = driver.findElements(By.xpath("//*[@id=\"mw-pages\"]/div/div/div/ul"));
                    for (WebElement li : ul) {
                        word.setWords(li.getText());
                    }
                }
            }
            List<WebElement> currentPageLinks = driver.findElements(By.cssSelector("div#mw-pages > a"));
            WebElement nextPageLink = currentPageLinks.get(currentPageLinks.size() - 1);
            if (nextPageLink.getText().contains("następna strona")) {
                url = nextPageLink.getAttribute("href");
            } else {
                hasNextPage = false;
            }
        }
        System.out.println(this);
        driver.quit();
    }
}
