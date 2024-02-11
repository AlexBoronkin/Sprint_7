import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTests extends CourierAccountMethods {

      @Before
    public void setUp() {
        createCourierAccount();
    }

    @DisplayName("Создание уникального курьера. Должен возвращать код 201 и сообщение ok:true")
    @Test
    public void testRandomCourierAccountCreation() {
        CourierAndOrderSteps courierAndOrderSteps = new CourierAndOrderSteps();
        ValidatableResponse courierLogin = courierAndOrderSteps.createCourier(
                Courier.getRandomCourier());
        courierLogin
                .statusCode(201)
                .assertThat()
                .body("ok",equalTo(true));
    }


    @DisplayName("Создание уже существующего курьера. Должен вернуть код 409 и сообщение 'Этот логин уже используется. Попробуйте другой.'")
    @Test
    public void testErrorMessageForDuplicatedCourierCreation() {
        CourierAndOrderSteps courierAndOrderSteps = new CourierAndOrderSteps();
        ValidatableResponse duplicateClientLogin = courierAndOrderSteps.createCourier(
                new Courier(TYPICAL_LOGIN, TYPICAL_PASSWORD, TYPICAL_FIRSTNAME));
        duplicateClientLogin
                .statusCode(409)
                .assertThat()
                .body("message",equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @DisplayName("Создание курьера без ввода логина. Должен вернуть код 400 и сообщение 'Недостаточно данных для создания учетной записи'")
    @Test
    public void testErrorForCreationCourierWithoutLogin() {
        CourierAndOrderSteps courierAndOrderSteps = new CourierAndOrderSteps();
        ValidatableResponse emptyLoginField = courierAndOrderSteps.createCourier(
                new Courier(null, TYPICAL_PASSWORD, TYPICAL_FIRSTNAME));
        emptyLoginField
                .statusCode(400)
                .assertThat()
                .body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }

    @DisplayName("Создание курьера без ввода пароля. Должен вернуть код 400 и сообщение 'Недостаточно данных для создания учетной записи'")
    @Test
    public void testErrorForCreationCourierWithoutPassword() {
        CourierAndOrderSteps courierAndOrderSteps = new CourierAndOrderSteps();
        ValidatableResponse emptyPasswordField = courierAndOrderSteps.createCourier(
                new Courier(TYPICAL_LOGIN, null, TYPICAL_FIRSTNAME));
        emptyPasswordField
                .statusCode(400)
                .assertThat()
                .body("message",equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void tearDown() {
        deleteCourierAccount();
    }
}
