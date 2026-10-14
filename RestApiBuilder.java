import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.basic;

public class RestApiBuilder {
//    private final String baseURL = "43534534";
    public static final RequestSpecification getrequestSpecification;
    public static final RequestSpecification postrequestSpecification;
    static {
         getrequestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                 .log(LogDetail.ALL)
                .build();
        postrequestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setContentType("application/json")
                .log(LogDetail.ALL)
                .setAuth(basic("admin","secret123"))
                .build();
    }
}
