package Websites;

import Instances.Czasownik;
import Instances.Przymiotnik;
import Instances.Przyslowek;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class PrzyslowekPage extends WikiPage {
    private WebDriver driver;
    private Przyslowek przyslowek;
    private String url;

    public PrzyslowekPage() {
        this.driver = new ChromeDriver();
        this.przyslowek = new Przyslowek();
        this.url = "https://pl.wiktionary.org/wiki/Kategoria:J%C4%99zyk_polski_-_przys%C5%82%C3%B3wki";

        readFromPage();
    }

    public Przyslowek getWord(){
        return this.przyslowek;
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
                        przyslowek.setWords(li.getText());
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
        System.out.println(przyslowek);
        driver.quit();
    }
}
