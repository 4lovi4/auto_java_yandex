package ru.practicum.scooter;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;
import static com.codeborne.selenide.Selenide.open;
import ru.practicum.scooter.page_object.ScooterServiceMainPage;
import java.util.Collection;
import java.util.Arrays;

/*
1-й тестовый сценарий: проверка выпадающих ответов в FAQ на главной странице
 */

@RunWith(Parameterized.class)
public class TestImportantQuestionsPopUp {
    /*  Для ревью - убрал весь хардкод с бинарниками хром драйверов.
        А также метод с аннотацией @Before, где настраивался текущий путь до драйвера.
        По умолчанию в Selenide используется chromedriver.
        Сделал параметризованный тест и использовал коллекции для вопросов FAQ, как в рекомендации ревьювера.
    */

    @Parameter(1)
    public int questionIndex;
    @Parameter(0)
    public String answerExpected;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0 },
                { "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1 },
                { "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2 },
                { "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3 },
                { "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4 },
                { "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5 },
                { "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6 },
                { "Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7}
        });
    }

    @Test
    public void testImportantQuestions() {
        ScooterServiceMainPage scooterMainPage = open(ScooterServiceMainPage.URL,
                ScooterServiceMainPage.class);
        String answerActual = scooterMainPage.submitCookiesOnStart()
                .openHiddenAnswer(questionIndex);
        assertEquals(String.format("Ответ на %d-й вопрос не соответствует ожидаемому",
                (questionIndex + 1)), answerExpected, answerActual);
    }
}
