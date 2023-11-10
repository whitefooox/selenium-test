

import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class HabrTest {
    
    @Test
    public void firstTest(){
        System.setProperty("webdriver.chrome.driver", "/home/whitefox/dev/work/selenium/drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 1025));

        System.out.println("test");
        driver.get("https://habr.com/ru/all/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("timeout");
        driver.findElement(By.xpath("//*[@class='tm-svg-img tm-header-user-menu__icon tm-header-user-menu__icon_search tm-header-user-menu__icon_dark']")).click();
        
        WebElement input = driver.findElement(By.xpath("//input[@name='q']"));
        Assert.assertEquals(input, driver.switchTo().activeElement());
        String searchQuery = "Selenium WebDriver";
        input.sendKeys(searchQuery);

        WebElement magnifyingGlass = driver.findElement(By.xpath("//*[@class='tm-svg-img tm-svg-icon']"));
        magnifyingGlass.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.findElement(By.linkText("Что такое Selenium?")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Assert.assertEquals("28 сен 2012 в 16:14", driver.findElement(By.xpath("//*[@title='2012-09-28, 16:14']")).getText());
        
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) driver);
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.xpath("//a[@href='/ru/' and @class='footer-menu__item-link router-link-active']")).click();

        driver.quit();
    }
}
