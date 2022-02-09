package ru.practicum.scooter;

import org.junit.Assert;
import org.junit.Test;
import ru.practicum.scooter.page_object.OrderCreatePage;
import ru.practicum.scooter.page_object.OrderTrackPage;
import ru.practicum.scooter.page_object.ScooterServiceMainPage;
import ru.practicum.scooter.page_object.YandexMainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.url;

/*
Тесты переходов по ссылкам на главные страницы Яндекса и сервиса qa-scooter
 */

public class TestScooterServiceRedirection {

    @Test
    public void checkYandexRedirectionFromMainPage() {
        ScooterServiceMainPage scooterMainPage = open(ScooterServiceMainPage.URL,
                ScooterServiceMainPage.class);
        YandexMainPage yaRu = scooterMainPage.goToYandex();
        String actualUrl = url();
        String actualTitle = title();
        Assert.assertEquals("Не было перехода на главную страницу Яндекса",
                actualUrl, "https://yandex.ru/");
        Assert.assertEquals("Название страницы не Яндекс",
                actualTitle, "Яндекс");
        Assert.assertTrue("Элементы главной страницы Яндекс не отобразились",
                yaRu.checkYandexLoaded());
    }

    @Test
    public void checkMainRedirectionFromOrderCreation() {
        OrderCreatePage scooterOrderCreate = open(ScooterServiceMainPage.URL + OrderCreatePage.PATH,
                OrderCreatePage.class);
        ScooterServiceMainPage mainPage = scooterOrderCreate.goToMainScooterService();
        String actualUrl = url();
        String actualTitle = title();
        Assert.assertEquals("Не было перехода на главную страницу qa-scooter",
                actualUrl, ScooterServiceMainPage.URL);
        Assert.assertEquals("Название страницы не соответствует ожидаемому",
                actualTitle, "undefined");
    }


    @Test
    public void checkMainRedirectionFromOrderTracking() {
        OrderTrackPage scooterOrderTrack = open(ScooterServiceMainPage.URL + OrderTrackPage.PATH,
                OrderTrackPage.class);
        ScooterServiceMainPage mainPage = scooterOrderTrack.goToMainScooterService();
        String actualUrl = url();
        String actualTitle = title();
        Assert.assertEquals("Не было перехода на главную страницу qa-scooter",
                actualUrl, ScooterServiceMainPage.URL);
        Assert.assertEquals("Название страницы не соответствует ожидаемому",
                actualTitle, "undefined");
    }
}
