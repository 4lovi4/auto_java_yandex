package ru.practicum.scooter.page_object;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class YandexMainPage {
    public static final String URL = "https://yandex.ru/";

    @FindBy(how = How.XPATH, using = "$.//div[text()='Найдётся всё']")
    private SelenideElement yandexLogo;

    public boolean checkYandexLoaded() {
        yandexLogo.shouldBe(Condition.appear);
        return yandexLogo.isDisplayed();
    }
}
