package ru.practicum.scooter;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.scooter.page_object.ScooterServiceMainPage;

public class TestCreateOrder {

    /*  bin файл webdriver:
        - для Яндекс Браузера yandexdriver.exe
        - для простого Chrome chromedriver.exe
    */
    public static final String driverProperty = "webdriver.chrome.driver";
    public static final String browserName = "chrome";
    public static final String driverPath = "driver/linux/chromedriver";

    @Before
    public void setUp() {
        Configuration.browser = browserName;
        System.setProperty(driverProperty, driverPath);
    }

    @Test
    public void testCreateOrderByUpperButton() {
        ScooterServiceMainPage scooterMainPage = open(ScooterServiceMainPage.URL, ScooterServiceMainPage.class);
        scooterMainPage.submitCookiesOnStart();
    }
}
