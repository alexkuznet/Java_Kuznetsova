import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
public class RestAssuredTask2 {
    public record Request(String name, Integer price) {
    }

    public String expname = "Tiger";
    public Double expprice = 54.0;
    public Integer hours = 5;

    public record RequestWrong(String name, Integer price, Integer hours) {
    }

    @Test
    @Tag("api")
    public void post200() {
        given()
                .spec(RestApiBuilder.postrequestSpecification)
                .body(new RestAssuredTask2.Request("Tiger", 54))
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
        assertThat(lastItem.get("name")).isEqualTo("Tiger");
        assertThat(((Number) lastItem.get("price")).doubleValue()).isEqualTo(54.0);

    }

    @Test
    @Tag("api")
    public void post401() {
        Response response = given()
                .spec(RestApiBuilder.postrequest401Specification)
                .body(new RestAssuredTask2.Request("Tiger", 54))
                .post("/goods/add")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.getStatusCode()).isEqualTo(401);


    }
    @Test
    @Tag("api")
    public void post404() {
        Response response = given()
                .spec(RestApiBuilder.postrequest404Specification)
                .body(new RestAssuredTask2.Request("Tiger", 54))
                .post("/users/add")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.getStatusCode()).isEqualTo(404);


    }

    @Test
    @Tag("api")
    public void post400() {
        Response response = given()
                .spec(RestApiBuilder.postrequestSpecification)
                .body(new RestAssuredTask2.RequestWrong("Tiger", 54, 5))
                .post("/goods/add")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.getStatusCode()).isEqualTo(400);



    }

    @Test
    @Tag("api")
    public void getGoodsList200(){
        Response response =  given()
                .spec(RestApiBuilder.getrequestSpecification)
                .queryParam("page", 0)
                .queryParam("size", 100)
                .get("/goods/list")
                .then()
                .log().all()
                .extract().response();
        List<?> goodsList = response.jsonPath().getList("goods");
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(goodsList.size()).isGreaterThan(0);

    }

    @Test
    @Tag("api")
    public void getGoodsList404(){
        Response response = given()
                .spec(RestApiBuilder.getrequestSpecification)
                .queryParam("page", 0)
                .queryParam("size", 100)
                .get("/goods/lists")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.getStatusCode()).isEqualTo(404);


    }

    @Test
    @Tag("api")
    public void getGoodsList400(){
        Response response = given()
                .spec(RestApiBuilder.getrequestSpecification)
               .queryParam("page", -1)
               .queryParam("size", 0)
                .get("/goods/list")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.getStatusCode()).isEqualTo(400);


    }

    @Test
    @Tag("api")
    public void delete404(){
        Response response = given()
                .spec(RestApiBuilder.deleterequestSpecification)
                .delete("/goods/1000050000")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.getStatusCode()).isEqualTo(404);


    }
    @Test
    @Tag("api")
    public void delete400(){
        Response response = given()
                .spec(RestApiBuilder.deleterequestSpecification)
                .delete("/goods/delete")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.getStatusCode()).isEqualTo(400);


    }
    @Test
    @Tag("api")
    public void delete200(){
        Response response = given()
                .spec(RestApiBuilder.postrequestSpecification)
                .body(new RestAssuredTask1.Request3("Frog",748))
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        Integer createdId = response.jsonPath().getInt("data.id");
        Response deleteResponse = given()
                .spec(RestApiBuilder.deleterequestSpecification)
                .pathParam("id", createdId)
                .delete("/goods/{id}")
                .then()
                .log().all()
                .extract().response();
        assertThat(deleteResponse.getStatusCode())
                .as("Товар с ID " + createdId + " должен быть успешно удалён")
                .isEqualTo(200);


    }

    @Test
    @Tag("api")
    public void patch404(){
        Response response = given()
                .spec(RestApiBuilder.patchrequestSpecification)
                .body(new RestAssuredTask1.Request3("Frog",748))
                .patch("/goods/1000050000")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.getStatusCode()).isEqualTo(404);


    }

    @Test
    @Tag("api")
    public void patch400(){
        Response response = given()
                .spec(RestApiBuilder.postrequestSpecification)
                .body(new RestAssuredTask1.Request3("Frog",748))
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        Integer createdId = response.jsonPath().getInt("data.id");
        Response patchresponse = given()
                .spec(RestApiBuilder.patchrequestSpecification)
                .body(new RestAssuredTask2.RequestWrong("Tiger", 54, 5))
                .pathParam("id", createdId)
                .patch("/goods/{id}")
                .then()
                .log().all()
                .extract().response();
        assertThat(patchresponse.getStatusCode()).isEqualTo(400);


    }

    @Test
    @Tag("api")
    public void patch401(){
        Response response = given()
                .spec(RestApiBuilder.postrequest401Specification)
                .body(new RestAssuredTask1.Request3("Frog",748))
                .patch("/goods/1")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.getStatusCode()).isEqualTo(401);


    }

    @Test
    @Tag("api")
    public void patch200(){
        Response response = given()
                .spec(RestApiBuilder.postrequestSpecification)
                .body(new RestAssuredTask1.Request3("Frog",748))
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        Integer createdId = response.jsonPath().getInt("data.id");
        Response patchresponse = given()
                .spec(RestApiBuilder.patchrequestSpecification)
                .body(new RestAssuredTask1.Request3("Lizard",1500))
                .pathParam("id", createdId)
                .patch("/goods/{id}")
                .then()
                .log().all()
                .extract().response();
        assertThat(patchresponse.getStatusCode())
        .as("Товар с ID " + createdId + " должен быть успешно обновлен")
                .isEqualTo(200);


    }

    @Test
    @Tag("api")
    public void getId404(){
        Response response = given()
                .spec(RestApiBuilder.getIdrequestSpecification)
                .delete("/goods/46346457586345325")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.getStatusCode()).isEqualTo(404);


    }

    @Test
    @Tag("api")
    public void getId401(){
        Response response = given()
                .spec(RestApiBuilder.getrequestSpecification)
                .delete("/goods/46346457586345325")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.getStatusCode()).isEqualTo(401);


    }

    @Test
    @Tag("api")
    public void getId400(){
        Response response = given()
                .spec(RestApiBuilder.getIdrequestSpecification)
                .delete("/goods/get")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.getStatusCode()).isEqualTo(400);


    }

    @Test
    @Tag("api")
    public void getId200(){
        Response response = given()
                .spec(RestApiBuilder.postrequestSpecification)
                .body(new RestAssuredTask1.Request3("Frog",748))
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
        Integer createdId = response.jsonPath().getInt("data.id");
        Response getResponse = given()
                .spec(RestApiBuilder.getIdrequestSpecification)
                .pathParam("id", createdId)
                .get("/goods/{id}")
                .then()
                .log().all()
                .extract().response();
        assertThat(getResponse.getStatusCode())
                .as("Товар с ID " + createdId + " успешно найден")
                .isEqualTo(200);
        String name = getResponse.jsonPath().getString("name");
        assertThat(name)
                .as("Имя товара должно совпадать")
                .isEqualTo("Frog");


    }

}