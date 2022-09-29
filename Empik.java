import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class Empik {

    WebDriver driver;

    @Before
        public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        // Go to "https://www.empik.com/" site and maximize window.
        driver.get("https://www.empik.com/");
        driver.manage().window().maximize();
    }

    @org.junit.Test
        public void addProductsToCart() throws IOException {

            String titleOfBook = "Testowanie oprogramowania";

            //1. Enter "Testowanie oprogramowania" in searching field.
            driver.findElement(By.xpath("//input[@class='css-1rwl265-input-1']")).sendKeys(titleOfBook);
            driver.findElement(By.xpath("//input[@class='css-1rwl265-input-1']")).sendKeys(Keys.ENTER);

            //2. Check and display amount of products founded.
            String amountProductsOnSite = driver.findElement(By.xpath("//div[@class='switcher__found']")).getText();
            System.out.println("Na stronie " + amountProductsOnSite.toLowerCase());

            //3. Sort items by price from the lower.
            Select dropdown_sortBy = new Select(driver.findElement(By.id("search-sort-select")));
            dropdown_sortBy.selectByValue("priceAsc");

            //4. Add "Ciągłe dostarczanie oprogramowania. Automatyzacja kompilacji, testowania i wdrażania" book to the cart.
            WebDriverWait wait = new WebDriverWait(driver,30);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[2]/div/div/div/div[2]/div[2]/div[4]/div[11]/div/a/img")));
            driver.findElement(By.xpath("/html/body/main/div[2]/div/div/div/div[2]/div[2]/div[4]/div[11]/div/a/img")).click();

            //5. Verify URL address. User should be moved on "https://www.empik.com/ciagle-dostarczanie-oprogramowania-automatyzacja-kompilacji-testowania-i-wdrazania-opracowanie-zbiorowe,p1262147755,ebooki-i-mp3-p"
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = "https://www.empik.com/ciagle-dostarczanie-oprogramowania-automatyzacja-kompilacji-testowania-i-wdrazania-opracowanie-zbiorowe,p1262147755,ebooki-i-mp3-p";

            Assert.assertEquals(currentUrl,expectedUrl);
            System.out.println(currentUrl);

            //6. Add product to the cart.
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-id='p1262147755']")));
            driver.findElement(By.xpath("//button[@data-id='p1262147755']")).click();

            //7. Click on "Mój koszyk" icon (right upper corner).
            driver.findElement(By.id("simple-dropdown4")).click();

            //8. Display value of the cart.
            String cartValue = driver.findElement(By.xpath("//div[@class='css-1ps19is-container-CartSummaryWithPremium']")).getText();
            System.out.println(cartValue.substring(0,21));

            //9. Take a screenshot.
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("src/main/resources/screen" + ".png"));
    }

    @After
        public void tearDown() {

            // Close browser.
            driver.quit();

            }
        }
