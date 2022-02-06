package ru.practicum.scooter.page_object;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class YandexMainPage {
    public static final String URL = "https://yandex.ru/";

    @FindBy(how = How.XPATH, using = "$.//div[text()='Найдётся всё']")
    private SelenideElement yandexSearchInput;

    @FindBy(how = How.XPATH, using = ".//div[@class='search2__button']/button")
    private SelenideElement yandexSearchButton;

    public boolean checkYandexLoaded() {
        yandexSearchInput.shouldBe(Condition.appear);
        return yandexSearchInput.isDisplayed();
    }
}
