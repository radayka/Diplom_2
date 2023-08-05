package stellaburgers.api.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import stellaburgers.api.register.model.RegisterRequest;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.Matchers.equalTo;
import static stellaburgers.api.help.ApiUrls.AUTH_USER;
import static stellaburgers.api.help.Constants.USER_SUCCESSFULLY_REMOVED;


public class UserClientRequest {
    @Step("Отправка запроса на изменение юзера")
    public static Response sendRequestChangeUser(RegisterRequest user) {
        return given()
                .body(user)
                .when()
                .patch(AUTH_USER);
    }

    @Step("Проверка обвновления данных у юзера")
    public static Response sendRequestChangeUserWithAccessToken(RegisterRequest user, String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .body(user)
                .when()
                .patch(AUTH_USER);
    }

    @Step("Удаление юзера")
    public static void deleteUser(String accessToken) {
        given()
                .header("authorization", accessToken)
                .when()
                .delete(AUTH_USER)
                .then().assertThat().statusCode(SC_ACCEPTED)
                .body("message", equalTo(USER_SUCCESSFULLY_REMOVED));
    }
}
