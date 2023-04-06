package Websites;

import Instances.Czasownik;
import Instances.Przymiotnik;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class PrzymiotnikPage extends WikiPage {
    private WebDriver driver;
    private Przymiotnik przymiotnik;
    private String url;

    public PrzymiotnikPage() {
        this.driver = new ChromeDriver();
        this.przymiotnik = new Przymiotnik();
        this.url = "https://pl.wiktionary.org/wiki/Kategoria:J%C4%99zyk_polski_-_przymiotniki";
        readFromPage();
    }

    public Przymiotnik getWord(){
        return this.przymiotnik;
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
                        przymiotnik.setWords(li.getText());
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
        System.out.println(przymiotnik);
        driver.quit();
    }
}