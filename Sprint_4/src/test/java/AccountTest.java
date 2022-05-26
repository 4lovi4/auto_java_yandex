import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class AccountTest {

    private final String accountName;
    private final boolean expected;

    public AccountTest(String accountName, boolean expected) {
        this.accountName = accountName;
        this.expected = expected;
    }

    @Parameters
    public static Object[][] testAccountData() {
        return new Object[][] {
                {"Таран Татоев", true}, //Правильное имя с длинной в диапазоне от 3 до 19 символов и одним пробелом
                {"G V", true}, // Длина имени = 3 символа
                {"Константин Нанолепс", true}, // Длина имени = 19 символов
                {"АИ", false}, // Длина имени < 3 символов
                {"Пантелеймон Апполинариевич", false}, // Длина имени > 19 символов
                {" Иван Драго", false}, //Пробел в начале
                {"Том Кат ", false}, //Пробел в конце
                {"Бильбо Бэггинс Младший", false}, //Пробелов > 1
                {"ЛараКрофт", false}, //Нет пробела
                {"", false} //Пустая строка
        };
    }

    @Test
    @Description("Тест проверки имени {accountName}")
    @DisplayName("Account метод checkNameToEmboss")
    public void checkNameToEmbossTests() {
        Allure.step(accountName);
        Account account = new Account(accountName);
        assertTrue("Не верная проверка имени",
                account.checkNameToEmboss() == expected);
    }
}
