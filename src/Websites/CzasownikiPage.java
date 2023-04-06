package Websites;

import Instances.Czasownik;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CzasownikiPage extends WikiPage{
    private WebDriver driver;
    private Czasownik czasownik;
    private String url;

    public CzasownikiPage(){
        this.driver = new ChromeDriver();
        this.czasownik = new Czasownik();
        this.url = "https://pl.wiktionary.org/wiki/Kategoria:J%C4%99zyk_polski_-_czasowniki";

        readFromPage();
    }

    public Czasownik getWord(){
        return this.czasownik;
    }

    public void readFromPage() {
        boolean hasNextPage = true;
        while (hasNextPage) {
            driver.get(url);
            List<WebElement> bigDiv = driver.findElements(By.xpath("//*[@id=\"mw-pages\"]/div/div"));
            for (WebElement verbLink : bigDiv) {
                List<WebElement> smallDiv = driver.findElements(By.xpath("//*[@id=\"mw-pages\"]/div/div/div"));
                for (WebElement smallLink : smallDiv){
                    List<WebElement> ul = driver.findElements(By.xpath("//*[@id=\"mw-pages\"]/div/div/div/ul"));
                    for (WebElement li: ul) {
                        czasownik.setWords(li.getText());
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
        System.out.println(czasownik);
        driver.quit();
    }
}
