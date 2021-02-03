package spec;

import config.Config;
import filter.CustomFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Request {
    private static final RequestSpecification SPEC = new RequestSpecBuilder()
            .setContentType("application/x-www-form-urlencoded; charset=UTF-8")
            .setBaseUri(Config.baseUrl())
            .setContentType(Config.contentType())
            .addFilter(CustomFilter.CUSTOM_FILTER)
            .build();

    public static RequestSpecification spec() {
        return SPEC;
    }
}
