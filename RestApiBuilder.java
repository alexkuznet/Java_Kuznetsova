import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.basic;

public class RestApiBuilder {
//    private final String baseURL = "43534534";
    public static final RequestSpecification getrequestSpecification;
    public static final RequestSpecification postrequestSpecification;
    public static final RequestSpecification postrequest401Specification;
    public static final RequestSpecification postrequest404Specification;
    public static final RequestSpecification deleterequestSpecification;
    public static final RequestSpecification patchrequestSpecification;
    public static final RequestSpecification getIdrequestSpecification;
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
        postrequest401Specification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setContentType("application/json")
                .log(LogDetail.ALL)
                .build();
        postrequest404Specification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setContentType("application/json")
                .log(LogDetail.ALL)
                .setAuth(basic("admin","secret123"))
                .build();
        deleterequestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setAuth(basic("admin","secret123"))
                .log(LogDetail.ALL)
                .build();
        patchrequestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setAuth(basic("admin","secret123"))
                .setContentType("application/json")
                .log(LogDetail.ALL)
                .build();
        getIdrequestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setAuth(basic("admin","secret123"))
                .log(LogDetail.ALL)
                .build();

    }
}
