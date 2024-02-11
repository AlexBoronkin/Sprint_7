import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierAndOrderSteps extends TestData {

    @Step ("Создание курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .spec(BaseHttpClient.baseRequestSpec())
                .and()
                .body(courier)
                .when()
                .post(API_COURIER)
                .then();
    }

    @Step ("Логин курьера")
    public ValidatableResponse loginCourier(Login login) {
        return given()
                .spec(BaseHttpClient.baseRequestSpec())
                .and()
                .body(login)
                .when()
                .post(API_LOGIN)
                .then();
    }

    @Step ("Создание заказа")
    public ValidatableResponse createOrder (Order order) {
        return given()
                .spec(BaseHttpClient.baseRequestSpec())
                .and()
                .body(order)
                .when()
                .post(API_ORDER)
                .then();
    }

}
