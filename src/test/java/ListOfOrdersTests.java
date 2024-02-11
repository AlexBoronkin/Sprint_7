import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.notNullValue;


public class ListOfOrdersTests extends TestData {

    @DisplayName("Получение списка заказов без заполнения полей. Смотрим что тело не пустое")
    @Test
    public void CheckListOfOrdersIsNotEmpty() {
        given().spec(BaseHttpClient.baseRequestSpec())
                .get(API_ORDER);
        MatcherAssert.assertThat("orders", notNullValue());

    }
}
