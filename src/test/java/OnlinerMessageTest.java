import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 7*) Написать авто тест который
 - логинится на onliner
 - отсылает сообщение другому пользователю
 - логинится  другим пользователем
 - проверяет что сообщение пришло и оно соответствует отправленному первым пользователем
 */

public class OnlinerMessageTest extends TestCase {
    @Test
    public void testLogin() throws Exception {

        WebDriver driver = new ChromeDriver();

        //переводим браузер в полноэкранный режим
        driver.manage().window().maximize();

        //переходим по URL
        driver.get("https://www.onliner.by");

        //нажимаем кнопку логина
        WebElement loginButton = driver.findElement(By.xpath("//*[@id='userbar']/div[2]/div[1]"));
        loginButton.click();

        //ждём 10 секунд пока выражение By.xpath("//*[@id='auth-container__forms']/div/div[2]/form/div[1]/div[1]/input" не станет - true
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//*[@id='auth-container__forms']/div/div[2]/form/div[1]/div[1]/input")).isDisplayed();
            }
        });

        //вводим логин, пароль и нажимаем кнопку логина
        WebElement loginInput = driver.findElement(By.xpath("//*[@id='auth-container__forms']/div/div[2]/form/div[1]/div[1]/input"));
        loginInput.sendKeys("testtesttest123");

        WebElement passwordInput = driver.findElement(By.xpath("//*[@id='auth-container__forms']/div/div[2]/form/div[1]/div[2]/input"));
        passwordInput.sendKeys("testtesttest");

        loginButton = driver.findElement(By.xpath("//*[@id=\"auth-container__forms\"]/div/div[2]/form/div[4]/div/button"));
        loginButton.click();

        //ждём 10 секунд пока выражение By.xpath("//*[@id=\"userbar\"]/div[1]/p/a" не станет - true
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//*[@id=\"userbar\"]/div[1]/p/a")).isDisplayed();
            }
        });

        WebElement userName = driver.findElement(By.xpath("//*[@id='userbar']/div[1]/p/a"));

        //сравниваем значения поля "имя"
        assertEquals(userName.getText(), "testtesttest123");

        //нажимаем на кнопку "сообщения"
        WebElement message = driver.findElement(By.xpath("//*[@id=\"userbar\"]/div[1]/ul/li[1]/a"));
        message.click();

        //нажимаем на кнопку "написать"
        WebElement send = driver.findElement(By.xpath("//*[@id=\"l_compose\"]/a"));
        send.click();

        //ждём 10 секунд пока выражение By.xpath("//*[@id=\"compose_uname\"]" не станет - true
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//*[@id=\"compose_uname\"]")).isDisplayed();
            }
        });

        //вводим имя, тему, текст сообщения и нажимаем кнопку "отправить"
        WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"compose_uname\"]"));
        usernameInput.sendKeys("testtesttest1234");

        WebElement topic = driver.findElement(By.xpath("//*[@id=\"compose_subject\"]"));
        topic.sendKeys("Первый текст");

        WebElement textInput = driver.findElement(By.xpath("//*[@id=\"compose_text\"]"));
        textInput.sendKeys("Привет, как дела?");

        WebElement sendText = driver.findElement(By.xpath("//*[@id=\"privateMessageForm\"]/div/div[5]/div/button"));
        sendText.click();

        WebElement exit = driver.findElement(By.xpath("//*[@id=\"userbar\"]/div[1]/ul/li[3]/a"));

        //ждём 10 секунд пока выражение By.xpath("//*[@id=\"userbar\"]/div[1]/ul/li[3]/a" не станет - true
       (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//*[@id=\"userbar\"]/div[1]/ul/li[3]/a")).isDisplayed();
            }
        });

       //нажимаем кнопку "выйти" , первый раз не хочет нажимать и переходит к следующему действию и тест проваливается????
        exit.click();
        exit.click();

        //ждём 10 секунд пока выражение By.xpath("//*[@id=\"auth-container__forms\"]/div/div[2]/form/div[1]/div[1]/input" не станет - true
        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath("//*[@id=\"auth-container__forms\"]/div/div[2]/form/div[1]/div[1]/input")).isDisplayed();
            }
        });

        //вводим логин, пароль и нажимаем кнопку логина
        loginInput = driver.findElement(By.xpath("//*[@id=\"auth-container__forms\"]/div/div[2]/form/div[1]/div[1]/input"));
        loginInput.sendKeys("testtesttest1234");

        passwordInput = driver.findElement(By.xpath("//*[@id=\"auth-container__forms\"]/div/div[2]/form/div[1]/div[2]/input"));
        passwordInput.sendKeys("testtesttest");

        loginButton = driver.findElement(By.xpath("//*[@id=\"auth-container__forms\"]/div/div[2]/form/div[4]/div/button"));
        loginButton.click();

        //ждём 10 секунд пока выражение By.linkText("Первый текст" не станет - true
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.linkText("Первый текст")).isDisplayed();
            }
        });

        //нажимаем на сообщение от другого пользователя
        WebElement readMessage = driver.findElement(By.linkText("Первый текст"));
        readMessage.click();

        //ждём 10 секунд пока выражение By.className("b-msgpost-txt" не станет - true
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.className("b-msgpost-txt")).isDisplayed();
            }
        });

        //проверяем правильности текста
        WebElement text = driver.findElement(By.className("b-msgpost-txt"));
        String strng = text.getText();
        System.out.println(strng);
        Assert.assertEquals("Привет, как дела?", strng);

        //выходим их профиля. Не находит элемент exit, приходится искать новый элемент и присваивать ему имя exit1???
        WebElement exit1 = driver.findElement(By.xpath("//*[@id=\"userbar\"]/div[1]/ul/li[3]/a"));
        exit1.click();

        //закрываем браузер
        driver.quit();
    }
}

