package stellaburgers.api.register;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import stellaburgers.api.register.model.RegisterRequest;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static stellaburgers.api.help.ApiUrls.AUTH_REGISTER;

import stellaburgers.api.register.model.RegisterResponse;

public class AuthRegisterClientRequest {
    @Step("Получение объекта юзера после запроса на регистрацию юзера")
    public static RegisterResponse registerUser(RegisterRequest requestBody) {
        return sendRequestRegisterUser(requestBody).then().assertThat().statusCode(SC_OK).and().extract().response().as(RegisterResponse.class);

    }

    @Step("Отправка запроса на регистрацию юзера")
    public static Response sendRequestRegisterUser(RegisterRequest requestBody) {
        return given().body(requestBody).when().post(AUTH_REGISTER);

    }


}
