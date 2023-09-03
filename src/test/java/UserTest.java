
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellaburgers.api.register.model.RegisterRequest;
import stellaburgers.api.user.UserClientResponse;


import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static stellaburgers.api.help.BaseClient.validateResponseError;
import static stellaburgers.api.help.Constants.SHOULD_BE_AUTHORIZED;
import static stellaburgers.api.register.AuthRegisterClientRequest.registerUser;
import static stellaburgers.api.register.UserGenerator.*;
import static stellaburgers.api.user.UserClientRequest.*;


@DisplayName("Изменение юзера")
public class UserTest extends BaseTest {
    private RegisterRequest body;
    private String accessToken;

    @Before
    public void createTestData() {
        body = generateUser();
        accessToken = registerUser(body).getAccessToken();
    }

    @After
    public void deleteTestData() {
        if (accessToken != null) {
            deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Изменение email и username для юзера")
    public void shouldChangeUserEmailAndUsername() {
        body.setEmail(generateEmail());
        body.setName(generateUsername());
        body.setPassword(null);
        Response response = sendRequestChangeUserWithAccessToken(body, accessToken);

        UserClientResponse.checkUserIsUpdated(response, body);
    }

    @Test
    @DisplayName("Нельзя изменить поля юзеру без токена")
    public void shouldNotChangeUserWithoutAuthorizationToken() {
        body.setEmail(generateEmail());
        body.setName(generateUsername());
        body.setPassword(null);
        Response response = sendRequestChangeUser(body);

        validateResponseError(response, SC_UNAUTHORIZED, SHOULD_BE_AUTHORIZED);
    }


}
