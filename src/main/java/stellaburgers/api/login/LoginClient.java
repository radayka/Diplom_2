package stellaburgers.api.login;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import stellaburgers.api.register.model.RegisterRequest;

import static io.restassured.RestAssured.given;
import static stellaburgers.api.help.ApiUrls.AUTH_LOGIN;


public class LoginClient {
    @Step("Отправка запроса на авторизацию юзера")
    public static Response sendLoginRequest(RegisterRequest body){
        return given()
                .body(body)
                .when()
                .post(AUTH_LOGIN);
    }
}
