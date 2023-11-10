import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MailRuTest {

    private WebDriver driver;
    WebElement createButton;

    private void init(){
        System.setProperty("webdriver.chrome.driver", "/home/whitefox/dev/work/selenium/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 1025));
    }

    private void go(){
        driver.get("https://account.mail.ru/");
    }

    private void delay(int seconds){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    private WebElement find(String xpath){
        return driver.findElement(By.xpath(xpath));
    }

    private void inputEmail(String email){
        WebElement inputField = find("//input[@name='username']");
        inputField.clear();
        inputField.sendKeys(email);
        WebElement nextButton = find("//*[@data-test-id='next-button']");
        nextButton.click();
    }

    private void inputPassword(String password){
        WebElement inputField = find("//input[@name='password']");
        inputField.clear();
        inputField.sendKeys(password);
        WebElement nextButton = find("//button[@data-test-id='submit-button']");
        nextButton.click();
    }

    private void openProfileMenu(){
        WebElement profile = find("//*[@class='ph-project__user-icon-mask svelte-1osmzf1']");
        profile.click();
    }

    private void checkProfileName(String name){
        String nameField = find("//*[@class='ph-name svelte-1popff4']").getText();
        Assert.assertEquals(name, nameField);
    }

    private void quit(){
        WebElement quitButton = find("//*[@data-testid='whiteline-account-exit']");
        quitButton.click();
    }

    private void checkQuit(){
        createButton = find("//*[@data-testid='mailbox-create-link']");
        if(!createButton.isDisplayed()) Assert.fail();
    }
    
    @Test
    public void test(){
        init();
        go();
        delay(10);
        inputEmail("professionaltester");
        delay(10);
        inputPassword("toptester123");
        delay(10);
        openProfileMenu();
        checkProfileName("Тестовый Тестич");
        delay(10);
        quit();
        checkQuit();
    }
}
