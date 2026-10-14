import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class RestAssuredTask1 {
public record Request3  (String name,Integer price){}
    public String expname = "Frog";
    public Integer expprice = 748;

    @Test
    @Tag("api")
    public void getGoods(){
        given()
                .baseUri("http://localhost:8080/")
//                .contentType("application/json")
                .log().all()
                .queryParam("page", 0)
                .queryParam("size", 100)
                .when()
                .get("goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods.size()", equalTo(0));


    }
    @Test
    @Tag("api")
    public void getGoodsReqSpec(){
        given()
                .spec(RestApiBuilder.getrequestSpecification)
                .queryParam("page", 0)
                .queryParam("size", 100)
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods.size()", equalTo(0));

    }
    @Test
    @Tag("api")
    public void postRestAssured(){
        given()
                .spec(RestApiBuilder.postrequestSpecification)
                .body(new Request3("Frog",748))
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200);
        given()
                .spec(RestApiBuilder.getrequestSpecification)
                .queryParam("page", 0)
                .queryParam("size", 100)
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods.find { it.name == '%s' && it.price == %d }".formatted(expname, expprice), notNullValue());

    }

    @Test
    @Tag("api")
    public void postAssertJ(){
        given()
                .spec(RestApiBuilder.postrequestSpecification)
                .body(new Request3("Frog",748))
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200);
        Response response = given()
                .spec(RestApiBuilder.getrequestSpecification)
                .queryParam("page", 0)
                .queryParam("size", 100)
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        List<?> goodsList = response.jsonPath().getList("goods");


        Map<String, Object> lastItem = (Map<String, Object>) goodsList.get(goodsList.size() - 1);
        assertThat(goodsList.size()).isGreaterThan(0);
        assertThat(lastItem.get("name")).isEqualTo("Frog");
        assertThat(lastItem.get("price")).isEqualTo(748);;




    }
                }




