package ru.practicum.scooter.page_object;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class OrderTrackPage {
    public static final String PATH = "track";

    @FindBy(how = How.XPATH, using = ".//input[@class='Input_Input__1iN_Z Track_Input__1g7lq Input_Filled__1rDxs Input_Responsible__1jDKN']")
    private SelenideElement trackOrderInput;

    @FindBy(how = How.XPATH, using = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private SelenideElement trackOrderButton;

    @FindBy(how = How.XPATH, using = ".//a[@class='Header_LogoScooter__3lsAR']")
    private SelenideElement scooterLogoRedirect;

    @FindBy(how = How.XPATH, using = ".//a[@href='//yandex.ru']")
    private SelenideElement yandexLogoRedirect;

    public YandexMainPage goToYandex() {
        yandexLogoRedirect.click();
        switchTo().window("Яндекс");
        return page(YandexMainPage.class);
    }

    public ScooterServiceMainPage goToMainScooterService() {
        scooterLogoRedirect.click();
        return page(ScooterServiceMainPage.class);
    }
}
