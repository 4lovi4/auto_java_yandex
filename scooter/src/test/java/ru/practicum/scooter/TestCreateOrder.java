package ru.practicum.scooter;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.open;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.scooter.page_object.ScooterServiceMainPage;
import ru.practicum.scooter.page_object.OrderCreatePage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestCreateOrder {

    /*  bin файл webdriver:
        - для Яндекс Браузера yandexdriver.exe
        - для простого Chrome chromedriver.exe
    */
    public static final String driverProperty = "webdriver.chrome.driver";
    public static final String browserName = "chrome";
    public static final String driverPath = "driver/linux/chromedriver";

    public static final List<Map<String, String>> customerData = Arrays.asList(
            Map.of("name", "Пётр", "surname", "Первый", "address", "Москва ул. Корректная 1",
                    "station", "Водный стадион", "phone", "+79991234455", "term", "сутки",
                    "color", "black", "comment", ""),
            Map.of("name", "Джон", "surname", "До", "address", "Москва ул. Не туда 0",
                    "station", "Щёлковская", "phone", "89997001234", "term", "семеро суток",
                    "color", "", "comment", "Some comment"));

    @Before
    public void setUp() {
        Configuration.browser = browserName;
        System.setProperty(driverProperty, driverPath);
    }

    @Test
    /*
    Форма заказа открывается при нажатии на верхнюю кнопку "Заказать"
    */
    public void checkCreateOrderByUpperButton() {
        ScooterServiceMainPage scooterMainPage = open(ScooterServiceMainPage.URL, ScooterServiceMainPage.class);
        scooterMainPage.submitCookiesOnStart();
        OrderCreatePage orderCreation = scooterMainPage.createOrderByUpperButton();
        String orderCreatedMessage = orderCreation.
                fillForWhomOrderForm(customerData.get(0).get("name"), customerData.get(0).get("surname"),
                customerData.get(0).get("address"), customerData.get(0).get("station"), customerData.get(0).get("phone"))
                .fillAboutRentForm(customerData.get(0).get("term"), customerData.get(0).get("color"),
                        customerData.get(0).get("comment"))
                .getOrderCreatedMessage();
        Assert.assertTrue("Сообщение о созданном заказе не соответсвует ожидаемому",
                orderCreatedMessage.matches("^Номер заказа\\: .*"));
    }

    @Test
    /*
    Форма заказа открывается при нажатии на кнопку "Заказать" в глубине главной страницы
    */
    public void checkCreateOrderByMidButton() {
        ScooterServiceMainPage scooterMainPage = open(ScooterServiceMainPage.URL, ScooterServiceMainPage.class);
        scooterMainPage.submitCookiesOnStart();
        OrderCreatePage orderCreation = scooterMainPage.createOrderByMidButton();
        String orderCreatedMessage = orderCreation.
                fillForWhomOrderForm(customerData.get(1).get("name"), customerData.get(1).get("surname"),
                        customerData.get(1).get("address"), customerData.get(1).get("station"), customerData.get(1).get("phone"))
                .fillAboutRentForm(customerData.get(1).get("term"), customerData.get(1).get("color"),
                        customerData.get(1).get("comment"))
                .getOrderCreatedMessage();
        Assert.assertTrue("Сообщение о созданном заказе не соответсвует ожидаемому",
                orderCreatedMessage.matches("^Номер заказа\\: .*"));
    }
}
