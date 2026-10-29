import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

    public class SeleniumTests {

        private WebDriver driver;
        private WebDriverWait wait;
        private final String baseUrl = "http://localhost:8080";

        @BeforeEach
        void setUp() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        @AfterEach
        void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }

        // 1.1. Добавить товар через админку, выйти на витрину и проверить, что товар отображается
        @Test
        void addProductViaAdminAndCheckOnStorefront() {
            // 1) Вход в админку
            driver.get(baseUrl + "/admin");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("secret123");
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            // 2) Добавление товара
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("n-name")));
            driver.findElement(By.id("n-name")).sendKeys("Lion");
            driver.findElement(By.id("n-price")).sendKeys("150");
            driver.findElement(By.id("add-btn")).click();

            // 3) Переход на витрину и проверка отображения
            driver.get(baseUrl);
            WebElement product = wait.until(d -> d.findElement(By.xpath("//div[@data-name=\"Lion\"]")));
            Assertions.assertTrue(product.isDisplayed(), "Товар не отображается на витрине");
        }

        // 1.2. Добавить товар в корзину и проверить, что он отображается
        @Test
        void addToCartAndVerify() {
            // 1) Вход в админку
            driver.get(baseUrl + "/admin");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("secret123");
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            // 2) Добавление товара
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("n-name")));
            driver.findElement(By.id("n-name")).sendKeys("Lion");
            driver.findElement(By.id("n-price")).sendKeys("150");
            driver.findElement(By.id("add-btn")).click();
            driver.get(baseUrl);
            // Добавление товара "В корзину"
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-name='Lion']")));
            btn.click();

            // Переход в корзину
            driver.findElement(By.xpath("//button[@id=\"open-cart-btn\"]")).click();
            WebElement cart = wait.until(d -> d.findElement(By.xpath("//*[@id=\"cartModal\"]")));
            WebElement cartItem = wait.until(d -> d.findElement(By.xpath("//div//b[normalize-space()='Lion']")));
            Assertions.assertTrue(cartItem.isDisplayed(), "Товар не найден в корзине");
        }

        // 1.3. Попытка входа в админку с неверными данными
        @Test
        void loginWithWrongCredentials() {
            driver.get(baseUrl + "/admin");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("secret12345");
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            WebElement errorMsg = wait.until(d -> d.findElement(By.xpath("//div[contains(@class, 'alert alert-danger')]")));
            Assertions.assertEquals("Неверные учетные данные пользователя", errorMsg.getText());
        }

        // 1.4. Проверка сохранения товаров в корзине после обновления страницы
        @Test
        void cartPersistsAfterRefresh() {
            // 1) Вход в админку
            driver.get(baseUrl + "/admin");
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("secret123");
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            // 2) Добавление товара
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("n-name")));
            driver.findElement(By.id("n-name")).sendKeys("Lion");
            driver.findElement(By.id("n-price")).sendKeys("150");
            driver.findElement(By.id("add-btn")).click();
            driver.get(baseUrl);
            // Добавление товара "В корзину"
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-name='Lion']")));
            btn.click();

            // Переход в корзину
            driver.findElement(By.xpath("//button[@id=\"open-cart-btn\"]")).click();
            WebElement cart = wait.until(d -> d.findElement(By.xpath("//*[@id=\"cartModal\"]")));
            WebElement cartItem = wait.until(d -> d.findElement(By.xpath("//div//b[normalize-space()='Lion']")));
            Assertions.assertTrue(cartItem.isDisplayed(), "Товар не найден в корзине");

            // Рефреш и повторный переход в корзину
            driver.navigate().refresh();
            driver.findElement(By.xpath("//button[@id=\"open-cart-btn\"]")).click();

            WebElement cartSecondClick = wait.until(d -> d.findElement(By.xpath("//*[@id=\"cartModal\"]")));
            try {
                WebElement cartItemSecondClick = wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//div//b[normalize-space()='Lion']")
                        )
                );

            } catch (TimeoutException e) {
                Assertions.fail("Товар 'Lion' исчез из корзины после обновления страницы!");
            }
        }
    }

