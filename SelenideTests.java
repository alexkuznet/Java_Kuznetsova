import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelenideTests {
    private static final String BASE_URL = "http://localhost:8080";

    @BeforeEach
    void beforeEach() {
        Configuration.baseUrl = BASE_URL;
        Configuration.browser = "chrome";
        Configuration.timeout = 10000; // 10 секунд на ожидание элементов
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    // 2.1. Добавить товар через админку и проверить на витрине
    @Test
    void selenideAddProductAndCheck() {
        open("/admin");
        $("#username").setValue("admin");
        $("#password").setValue("secret123");
        $("button[type='submit']").click();

        $("#n-name").setValue("Lion");
        $("#n-price").setValue("150");
        $("#add-btn").click();

        open("/");
        $("[data-name='Lion']").shouldHave(text("Lion"));
    }

    // 2.2. Добавить в корзину и проверить
    @Test
    void selenideAddToCart() {
        open("/admin");
        $("#username").setValue("admin");
        $("#password").setValue("secret123");
        $("button[type='submit']").click();

        $("#n-name").setValue("Lion");
        $("#n-price").setValue("150");
        $("#add-btn").click();


        open("/");
        $("button[data-name='Lion']").click();
        $("#open-cart-btn").click();
        $(".cart-item").shouldBe(visible);
    }

    // 2.3. Неверный логин/пароль
    @Test
    void selenideWrongLogin() {
        open("/admin");
        $("#username").setValue("WAdmin");
        $("#password").setValue("secret123");
        $("button[type='submit']").click();

        $(".alert.alert-danger").shouldHave(text("Неверные учетные данные пользователя"));
    }

    // 2.4. Корзина после обновления
    @Test
    void selenideCartPersists() {
        open("/admin");
        $("#username").setValue("admin");
        $("#password").setValue("secret123");
        $("button[type='submit']").click();

        $("#n-name").setValue("Lion");
        $("#n-price").setValue("150");
        $("#add-btn").click();


        open("/");
        $("button[data-name='Lion']").click();
        Selenide.refresh();
        $("#open-cart-btn").click();
        $(".cart-item").shouldBe(visible);
    }

    // 2.5. Сумма > 300 руб, оформление заказа, проверка JS Alert
    @Test
    void selenideCheckOrderAlert() {
        open("/admin");
        $("#username").setValue("admin");
        $("#password").setValue("secret123");
        $("button[type='submit']").click();

        $("#n-name").setValue("Cat");
        $("#n-price").setValue("170");
        $("#add-btn").click();
        $("#n-name").setValue("Dog");
        $("#n-price").setValue("165");
        $("#add-btn").click();

        open("/");
        $("button[data-name='Cat']").click();
        $("button[data-name='Dog']").click();
        $("#open-cart-btn").click();
        $("#makeOrder").click();
        var alert = Selenide.switchTo().alert();
        String alertText = alert.getText();
        assertTrue(alertText.contains("Денег не хватает"),
                "Ожидается сообщение 'Денег не хватает', но получено: '" + alertText + "'");
        alert.accept();

        //$(".alert.alert-danger").shouldHave(text(" [SmartShop]: Денег не хватает! Сумма 335 ₽ превышает лимит 300 ₽."));
        //Selenide.switchTo().alert().accept();

    }
}
