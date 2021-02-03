package tests;

import io.restassured.response.Response;
import models.WishlistModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.FileReader.readStringFromFile;

public class DemoWebShopTests extends TestBase {

    @Test
    @DisplayName("Successful added to wish list")
    void successfulAddToWishlistTest() {
        String cookie = readStringFromFile("src/test/resources/files/cookie");
        String body = readStringFromFile("src/test/resources/files/body");

        given()
                .body(body)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(cookie)
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/2/2")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Added to wish list with assert")
    void addToWishListWithSimpleAssertTest() {
        String cookie = readStringFromFile("src/test/resources/files/cookie");
        String body = readStringFromFile("src/test/resources/files/body");

        Response response = given()
                .body(body)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(cookie)
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/2/2")
                .then()
                .statusCode(200)
                .extract().response();

        assertTrue(response.asString().contains("The product has been added to your"));
    }

    @Test
    @DisplayName("Added to wish list with body assert")
    void addedToWishListWithBodyAssertTest() {
        String cookie = readStringFromFile("src/test/resources/files/cookie");
        String body = readStringFromFile("src/test/resources/files/body");

        given()
                .body(body)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(cookie)
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/2/2")
                .then()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"));
    }

    @Test
    @DisplayName("Added to wish list with model")
    void addedToWishListWithModel() {
        String cookie = readStringFromFile("src/test/resources/files/cookie");
        String body = readStringFromFile("src/test/resources/files/body");

        WishlistModel response = given()
                .body(body)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(cookie)
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/2/2")
                .then()
                .statusCode(200)
                .extract().as(WishlistModel.class);
    }

}

