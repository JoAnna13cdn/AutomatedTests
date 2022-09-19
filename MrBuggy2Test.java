import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class MrBuggy2Test {


    public void MethodZapamietajMnie() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe" );
        ChromeDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        String url = "http://demo.mrbuggy2.testarena.pl/zaloguj";
        String emailAddress = "j.kowalska@gmail.pl";
        String password = "Abcd1234!";

        driver.get(url);

        //1.Click on "ADRES E-MAIL" field and type e-mail address.

        driver.findElement(By.id("email")).sendKeys(emailAddress);

        //3.Click on "HASŁO"field and fill in data.

        driver.findElement(By.id("password")).sendKeys(password);

        //4.Mark checkbox "ZAPAMIĘTAJ MNIE".

        driver.findElement(By.xpath("//input[@id='remember']")).click();

        //5.Click on "ZALOGUJ" button.

        driver.findElement(By.xpath("//input[@name='login']")).click();

        //6.Close application without using log out button.

        driver.close();

        //7.Run application.

        driver = new ChromeDriver();
        driver.get(url);

        //8. Verify if checkbox "ZAPAMIĘTAJ MNIE" is selected.

        if (driver.findElement(By.xpath("//input[@id='remember']")).isSelected())

        {
            System.out.println("'ZAPAMIĘTAJ MNIE' checkbox is selected");
        }
        else
        {
            System.out.println("'ZAPAMIĘTAJ MNIE' checkbox does not work.");
        }

        driver.quit();

        }

    }


