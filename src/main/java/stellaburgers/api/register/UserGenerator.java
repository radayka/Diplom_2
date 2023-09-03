package stellaburgers.api.register;

import io.qameta.allure.Step;
import stellaburgers.api.register.model.RegisterRequest;

import java.util.Random;

public class UserGenerator {
    private static final Random rnd = new Random();
    @Step("Генерация объекта нового юзера")
    public static RegisterRequest generateUser() {
        RegisterRequest user = new RegisterRequest();

        user.setName(generateUsername());
        user.setPassword(generatePassword());
        user.setEmail(generateEmail());

        return user;
    }
    public static String generateUsername(){
        return "Radaev" + rnd.nextInt(999999);
    }
    public static String generatePassword(){
        return String.valueOf(rnd.nextInt(999999));
    }
    public static String generateEmail(){
        return "autotests" + rnd.nextInt(999999) + "@yandex.ru";
    }
}
