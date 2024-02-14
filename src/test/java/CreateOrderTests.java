import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTests extends TestData {

   /* @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }*/

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public CreateOrderTests(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters(name = "firstName = {0}, lastName = {1}, address = {2}, metroStation = {3}, phone = {4}, rentTime = {5}, " +
            "deliveryDate = {6}, comment = {7}, color = {8}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Александр", "Боронкин", "Москва, Лестева, 21", "Шаболовская", "+79990574500", 2, "07.03.2024", "Везите быстрее", List.of("BLACK", "GREY")},
                {"Boris", "Borisovich", "Moscow, Taganka, 4", "Zil", "+79095432999", 16, "08.03.2024", "Testiruem Test", List.of("GREY")},
                {"Надежда", "Петровна", "Санкт-Петербург, Восстания, 3", "Электросила", "+79097338723", 8, "06.03.2024", "Буду в белом пальто", null},
        };
    }

    @DisplayName("Проверяем создание заказа. Должен вернуть код 201 и трек-номер")
    @Test
    public void checkOrderCreation() {
        CourierAndOrderSteps courierAndOrderSteps = new CourierAndOrderSteps();
        ValidatableResponse response = courierAndOrderSteps.createOrder(
                new Order(firstName, lastName, address,
                        metroStation, phone, rentTime, deliveryDate, comment, color));
        response
                .statusCode(201);
        MatcherAssert.assertThat("track", notNullValue());

    }

}
