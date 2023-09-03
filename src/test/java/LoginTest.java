

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellaburgers.api.register.AuthRegisterClientResponse;
import stellaburgers.api.register.model.RegisterRequest;

import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static stellaburgers.api.help.BaseClient.validateResponseError;
import static stellaburgers.api.help.Constants.EMAIL_OR_PASSWORD_INCORRECT;
import static stellaburgers.api.login.LoginClient.sendLoginRequest;
import static stellaburgers.api.register.AuthRegisterClientRequest.registerUser;
import static stellaburgers.api.register.UserGenerator.generateUser;
import static stellaburgers.api.user.UserClientRequest.deleteUser;


@DisplayName("Авторизация юзера")
public class LoginTest extends BaseTest {
    private RegisterRequest body;
    private String accessToken;

    @Before
    public void createTestData() {
        body = generateUser();
    }

    @After
    public void deleteTestData() {
        if (accessToken != null) {
            deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Авторизация существующего юзера")
    public void shouldLoginExistingUser() {
        accessToken = registerUser(body).getAccessToken();
        Response response = sendLoginRequest(body);

        AuthRegisterClientResponse.checkUserIsCreatedOrLoggedIn(response, body);
    }

    @Test
    @DisplayName("Неуспешная авторизация юзера с некорректным паролем и именем")
    public void shouldNotLoginWithNonCorrectPasswordAndUsername() {
        Response response = sendLoginRequest(body);

        validateResponseError(response, SC_UNAUTHORIZED, EMAIL_OR_PASSWORD_INCORRECT);
    }
}
