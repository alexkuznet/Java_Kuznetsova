import com.codeborne.selenide.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.basic;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SelenideTask3 {

    private static final String BASE_URL = "http://localhost:8080";
    private final List<Integer> createdProductIds = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        Configuration.baseUrl = BASE_URL;
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        createdProductIds.clear();
    }


    private int createProductViaApi(String name, int price) {
        Response response = RestAssured.given()
                .auth().basic("admin","secret123")
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body("{\"name\":\"" + name + "\", \"price\":" + price + "}")
                .post("/goods/add");
        assertEquals(200, response.statusCode(), "API должно вернуть статус 200 Created");
        int goodId = response.path("data.id");
        createdProductIds.add(goodId);
        return goodId;
    }

    private void deleteProductViaApi(int productId) {
        RestAssured.given()
                .baseUri(BASE_URL)
                .delete("/goods/{id}", productId);
    }

    @AfterEach
    void afterEach() {
        for (int id : createdProductIds) {
            try {
                deleteProductViaApi(id);
            } catch (Exception e) {
                System.err.println("Не удалось удалить товар с ID " + id + ": " + e.getMessage());
            }
        }
        createdProductIds.clear();
        Selenide.closeWebDriver();
    }

    // 3.1 Добавление в корзину трех товаров и их оплата
    @Test
    void testPlaceOrderWithThreeItems() {

        int goodId = createProductViaApi("AngryBird", 90);

        open("/");

        $("button[data-name='AngryBird']").click();
        $("button[data-name='AngryBird']").click();
        $("button[data-name='AngryBird']").click();
        $("#open-cart-btn").click();
        $("#makeOrder").click();
        String toastText = $$("div.toast").last().getText();
        assertTrue(toastText.contains("Заказ принят"),
                "Ожидается сообщение 'Заказ принят' в тосте, но найдено: '" + toastText + "'");

    }

    // 3.2. Добавить разные товары и проверить корректность суммы в корзине.
    @Test
    void testCartTotalCalculation() {
        // Создаем два разных товара через API
        createProductViaApi("Pig", 40);
        createProductViaApi("Goat", 75);
        open("/");

        $("button[data-name='Pig']").click();
        $("button[data-name='Goat']").click();
        $("#open-cart-btn").click();

        String totalText = $("#total-price").getText();
        int totalValue = Integer.parseInt(totalText.replaceAll("[^\\d]", ""));

        assertEquals(115, totalValue, "Сумма в корзине должна быть 115 рублей (40 + 75)");
    }

    // 3.3. Войти в админку, добавить товар, проверить уведомление после добавления.
    @Test
    void testAdminAddProductWithNotification() {
        open("/admin");
        $("#username").setValue("admin");
        $("#password").setValue("secret123");
        $("button[type='submit']").click();

        String newName = "Monkey";
        String newPrice = "200";

        $("#n-name").setValue(newName);
        $("#n-price").setValue(newPrice);
        $("#add-btn").click();
        $("div.toast").shouldBe(visible);
        String toastText = $$("div.toast").first().getText();
        assertTrue(toastText.contains("Товар успешно добавлен!"),
                "Ожидается сообщение 'Товар успешно добавлен!' в тосте, но найдено: '" + toastText + "'");

    }

    //3.4. Войти в админку, отредактировать товар, проверить изменения.
    @Test
    void testEditProduct() {

        int productId = createProductViaApi("Bear", 100);

        open("/admin");
        $("#username").setValue("admin");
        $("#password").setValue("secret123");
        $("button[type='submit']").click();

        SelenideElement input = $$("input[type='text']").filterBy(value("Bear")).first();
        //input.clear();
        input.setValue("Super Bear");
        input.shouldHave(value("Super Bear"));
        $(".btn btn-upd").click();
        $("input[value='Super Bear']").shouldBe(visible);
        $("input[value='Bear']").shouldNotBe(visible);

    }
}
