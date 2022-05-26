import org.junit.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideMetroTest {
    @Test
    public void checkChooseCityDropdown() {
        MetroHomePage metroPage = open("https://qa-metro.stand-2.praktikum-services.ru/",
                MetroHomePage.class);
        metroPage.waitForLoadHomePage();
        metroPage.chooseCity("Киев");
        $(byText("Крещатик")).shouldBe(visible);
    }

    @Test
    public void checkRouteApproxTimeIsDisplayed() {
        MetroHomePage metroPage = open("https://qa-metro.stand-2.praktikum-services.ru/",
                MetroHomePage.class);
        metroPage.waitForLoadHomePage();
        metroPage.buildRoute("Лубянка", "Красногвардейская");
        $$(byClassName("route-list")).get(0).shouldHave(matchText(".*≈ 36 мин\\..*"));
    }

    @Test
    public void checkRouteStationFromIsCorrect() {
        MetroHomePage metroPage = open("https://qa-metro.stand-2.praktikum-services.ru/",
                MetroHomePage.class);
        metroPage.waitForLoadHomePage();
        metroPage.buildRoute("Лубянка", "Красногвардейская");
        metroPage.getRouteStationFrom().shouldHave(text("Лубянка"));
    }
}
