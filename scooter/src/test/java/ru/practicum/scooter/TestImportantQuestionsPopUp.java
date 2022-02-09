package ru.practicum.scooter;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.open;
import ru.practicum.scooter.page_object.ScooterServiceMainPage;

/*
1-й тестовый сценарий: проверка выпадающих ответов в FAQ на главной странице
 */

public class TestImportantQuestionsPopUp {
    /*  Для ревью - убрал весь хардкод с бинарниками хром драйверов.
        А также метод с анотацией @Before, где настраивался текущий путь до драйвера.
        Путь до драйвера должен храниться в переменной окружения PATH/
    */
    public static final String[] answers = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    @Test
    public void testImportantQuestion1() {
        ScooterServiceMainPage scooterMainPage = open(ScooterServiceMainPage.URL,
                ScooterServiceMainPage.class);
        String answerOn1Question = scooterMainPage.submitCookiesOnStart()
                .openHiddenAnswer1();
        assertEquals("Ответ на 1-й вопрос не соответствует ожидаемому", answerOn1Question, answers[0]);
    }

    @Test
    public void testImportantQuestion2() {
        ScooterServiceMainPage scooterMainPage = open(ScooterServiceMainPage.URL,
                ScooterServiceMainPage.class);
        String answerOn2Question = scooterMainPage.submitCookiesOnStart()
                .openHiddenAnswer2();
        assertEquals("Ответ на 2-й вопрос не соответствует ожидаемому", answerOn2Question, answers[1]);
    }

    @Test
    public void testImportantQuestion3() {
        ScooterServiceMainPage scooterMainPage = open(ScooterServiceMainPage.URL,
                ScooterServiceMainPage.class);
        String answerOn3Question = scooterMainPage.submitCookiesOnStart()
                .openHiddenAnswer3();
        assertEquals("Ответ на 3-й вопрос не соответствует ожидаемому", answerOn3Question, answers[2]);
    }

    @Test
    public void testImportantQuestion4() {
        ScooterServiceMainPage scooterMainPage = open(ScooterServiceMainPage.URL,
                ScooterServiceMainPage.class);
        String answerOn4Question = scooterMainPage.submitCookiesOnStart()
                .openHiddenAnswer4();
        assertEquals("Ответ на 4-й вопрос не соответствует ожидаемому", answerOn4Question, answers[3]);
    }

    @Test
    public void testImportantQuestion5() {
        ScooterServiceMainPage scooterMainPage = open(ScooterServiceMainPage.URL,
                ScooterServiceMainPage.class);
        String answerOn5Question = scooterMainPage.submitCookiesOnStart()
                .openHiddenAnswer5();
        assertEquals("Ответ на 5-й вопрос не соответствует ожидаемому", answerOn5Question, answers[4]);
    }

    @Test
    public void testImportantQuestion6() {
        ScooterServiceMainPage scooterMainPage = open(ScooterServiceMainPage.URL,
                ScooterServiceMainPage.class);
        String answerOn6Question = scooterMainPage.submitCookiesOnStart()
                .openHiddenAnswer6();
        assertEquals("Ответ на 6-й вопрос не соответствует ожидаемому", answerOn6Question, answers[5]);
    }

    @Test
    public void testImportantQuestion7() {
        ScooterServiceMainPage scooterMainPage = open(ScooterServiceMainPage.URL,
                ScooterServiceMainPage.class);
        String answerOn7Question = scooterMainPage.submitCookiesOnStart()
                .openHiddenAnswer7();
        assertEquals("Ответ на 7-й вопрос не соответствует ожидаемому", answerOn7Question, answers[6]);
    }

    @Test
    public void testImportantQuestion8() {
        ScooterServiceMainPage scooterMainPage = open(ScooterServiceMainPage.URL,
                ScooterServiceMainPage.class);
        String answerOn8Question = scooterMainPage.submitCookiesOnStart()
                .openHiddenAnswer8();
        assertEquals("Ответ на 8-й вопрос не соответствует ожидаемому", answerOn8Question, answers[7]);
    }

}
