import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class MrBuggy2Test1 {
    public void MethodVerifyProject() throws IOException {

        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();

        String url = "http://demo.mrbuggy2.testarena.pl/zaloguj";
         String adminEmailAddress = "j.kowalska@gmail.pl";
        String adminPassword = "Abcd1234!";
        String user1Email = "johncena@gmail.com";
        String user1Password = "John2345!";
        String user2Email = " Elvisjanusz@gmail.com";
        String user2Password = "Elvis2022@1";

        driver.get(url);
        driver.manage().window().maximize();


        //1. Log in as an administrator.

        driver.findElement(By.id("email")).sendKeys(adminEmailAddress);
        driver.findElement(By.id("password")).sendKeys(adminPassword);
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);

        //2. Go to "Administracja" tab and click it.

        driver.findElement(By.linkText("Administracja")).click();

        //3. Find project called "ZQAJoanna K" and click it.

        driver.findElement(By.linkText("ZQAJoanna K")).click();

        //4. Verify title of the site.

        String actualTitle = driver.getTitle();
        String expectedTitle = "Właściwości projektu";

        File screenshotTitle = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshotTitle,new File("C:\\Selenium testy\\screen_Title"+".png"));

        System.out.println(actualTitle);

        if (actualTitle.equals(expectedTitle))
        {
            System.out.println("Title is correct:" + actualTitle);

        } else
        {
            System.out.println("Title is not correct:"+ actualTitle);
        }

        driver.quit();

       //5. Log in as user1.

        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        driver.findElement(By.id("email")).sendKeys(user1Email);
        driver.findElement(By.id("password")).sendKeys(user1Password);
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);

        //6. Click on name and surname (upper right corner).

        driver.findElement(By.xpath("//*[@id=\"head-top\"]/div[2]/div[2]/a")).click();

        //7. Verify current title of website.

        String actualTitleUser1 = driver.getTitle();
        System.out.println(actualTitleUser1);

        //8. Verify assigned project and take screenshot.

        String expectedProject = "ZQAJoanna K";

        String actualProject =  driver.findElement(By.xpath("//*[@id=\"content\"]/article/div[2]/div[2]/table/tbody/tr/td[1]/a")).getAccessibleName();

        Assert.assertEquals(actualProject,expectedProject);
        System.out.println(" User 1 - name of project is:" + actualProject);




        File screenshotUser1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileHandler.copy(screenshotUser1, new File("C:\\Selenium testy\\screenUser1_project" + ".png"));


        //9. Log out.

        driver.findElement(By.partialLinkText("Wyloguj")).click();

        //10. Log in as user2.

        driver.findElement(By.id("email")).sendKeys(user2Email);
        driver.findElement(By.id("password")).sendKeys(user2Password);
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);

        //11. Repeat steps from no. 6 to 8.

        driver.findElement(By.xpath("//*[@id=\"head-top\"]/div[2]/div[2]/a")).click();

        String actualTitleUser2 = driver.getTitle();

        System.out.println(actualTitleUser2);

        Assert.assertEquals(actualProject,expectedProject);

        File screenshotUser2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshotUser2, new File("C:\\Selenium testy\\screenUser2_project" + ".png"));

        System.out.println(" User 2 - name of project is:" + actualProject);



        driver.quit();

    }

}
