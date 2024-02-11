
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CourierAccountMethods extends TestData {

    public void createCourierAccount() {
        Courier newCourierCreation = new Courier(TYPICAL_LOGIN, TYPICAL_PASSWORD, TYPICAL_FIRSTNAME);
        given().spec(BaseHttpClient.baseRequestSpec())
                .and()
                .body(newCourierCreation)
                .when()
                .post(API_COURIER);
    }

    public void deleteCourierAccount() {
        Login login = new Login(TYPICAL_LOGIN, TYPICAL_PASSWORD);
        Response response =
                given().spec(BaseHttpClient.baseRequestSpec())
                        .and()
                        .body(login)
                        .when()
                        .post(API_LOGIN);
        String id = response.jsonPath().getString("id");
        given().spec(BaseHttpClient.baseRequestSpec())
                .when()
                .delete(API_LOGIN + id);
    }

}
