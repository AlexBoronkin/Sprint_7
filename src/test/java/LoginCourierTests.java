import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class LoginCourierTests extends CourierAccountMethods {

    @Before
    public void setUp() {
        createCourierAccount();
    }

    @DisplayName("Авторизация курьера. Должен вернуть код 200 и id")
    @Test
    public void successfulLoginTest() {
        CourierAndOrderSteps courierAndOrderSteps = new CourierAndOrderSteps();
        ValidatableResponse courier = courierAndOrderSteps.loginCourier(
                new Login(TYPICAL_LOGIN, TYPICAL_PASSWORD));
        courier.statusCode(200);
        MatcherAssert.assertThat("id", notNullValue());
    }

    @DisplayName("Авторизация без логина. Должен вернуть код 400 и сообщение 'Недостаточно данных для входа'")
    @Test
    public void loginWithoutLoginTest() {
        CourierAndOrderSteps courierAndOrderSteps = new CourierAndOrderSteps();
        ValidatableResponse courier = courierAndOrderSteps.loginCourier(
                new Login(null, TYPICAL_PASSWORD));
        courier
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @DisplayName("Авторизация без пароля. Должен вернуть код 400 и сообщение 'Недостаточно данных для входа'")
    @Test
    public void loginWithoutPasswordTest() {
        CourierAndOrderSteps courierAndOrderSteps = new CourierAndOrderSteps();
        ValidatableResponse responseWithNoPass = courierAndOrderSteps.loginCourier(
                new Login(TYPICAL_LOGIN, ""));
        responseWithNoPass
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    @DisplayName("Авторизация с несуществующим паролем. Должен вернуть код 404 и сообщение 'Учетная запись не найдена'")
    @Test
    public void randomDataLoginTest() {
        CourierAndOrderSteps courierAndOrderSteps = new CourierAndOrderSteps();
        ValidatableResponse response = courierAndOrderSteps.loginCourier(
                Login.getRandomLogin());
        response
                .statusCode(404)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void tearDown() {
        deleteCourierAccount();
    }

}
