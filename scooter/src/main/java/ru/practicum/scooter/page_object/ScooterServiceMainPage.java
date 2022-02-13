package ru.practicum.scooter.page_object;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/*
Главная страница сервиса "https://qa-scooter.praktikum-services.ru/"
Версия 1.01 после ревью:
* Добавил описание всех элементов главной страницы, не хватало блоков
"Самокат на пару дней" и "Как это работает"
* Элементы FAQ описал как коллекцию
 */

public class ScooterServiceMainPage {
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    //Ссылка на главе
    @FindBy(how = How.XPATH, using = ".//a[@class='Header_LogoScooter__3lsAR']")
    private SelenideElement scooterLogoRedirect;

    @FindBy(how = How.XPATH, using = ".//a[@href='//yandex.ru']")
    private SelenideElement yandexLogoRedirect;

    // Форма информирования о куках на главной странице
    @FindBy(how = How.XPATH, using = ".//div[@class='App_CookieConsent__1yUIN']")
    private SelenideElement cookieForm;

    // Кнопка подтверждения информации о куках
    @FindBy(how = How.ID, using = "rcc-confirm-button")
    private SelenideElement cookieSubmitButton;

    // Кнопка "Заказать" вверху страницы
    @FindBy(how = How.XPATH, using = ".//button[@class='Button_Button__ra12g']")
    private SelenideElement makeOrderButtonUpper;

    // Кнопка "Статус заказа"
    @FindBy(how = How.XPATH, using = ".//button[@class='Header_Link__1TAG7']")
    private SelenideElement statusOrderButton;

    // Блок "Самокат на пару дней"
    // Заголовок
    @FindBy(how = How.XPATH, using = ".//div[@class='Home_Header__iJKdX']")
    private SelenideElement scooterForRentHeader;

    // Подзаголовки в блоке "Самокат на пару дней"
    @FindBy(how = How.XPATH, using = ".//div[@class='Home_Header__iJKdX']/div[@calss='Home_SubHeader__zwi_E']")
    private List<SelenideElement> scooterForRentSubHeaders;

    // Таблица с техническими характеристиками скутера
    @FindBy(how = How.XPATH, using = ".//div[@class='Home_Table__2kPxP']")
    private SelenideElement scooterSpecificationTable;

    // Изображение скутера
    @FindBy(how = How.XPATH, using = ".//img[@alt='Scooter blueprint']")
    private SelenideElement scooterBluePrint;

    // Стрелка указывающая вниз для прокрутки блока
    @FindBy(how = How.XPATH, using = ".//img[@alt='Scroll down']")
    private SelenideElement arrowScrollDown;

    // Заголовок "Как это работает"
    @FindBy(how = How.XPATH, using = ".//div[text()=Как это работает]")
    private SelenideElement howItWorksHeader;

    // Элементы Блока роудмэп "Как это работает"
    // Номера в кружках
    @FindBy(how = How.XPATH, using = ".//div[@class='Home_StatusCircle__3_aTp']")
    private List<SelenideElement> roadMapNumberCircles;

    // Пункт в роад мапе работы сервиса
    @FindBy(how = How.XPATH, using = ".//div[@class='Home_Status__YkfmH']")
    private List<SelenideElement> roadMapPoint;

    // Описание пункта в роад мапе работы сервиса
    @FindBy(how = How.XPATH, using = ".//div[@class='Home_StatusDescription__3WGl5']")
    private List<SelenideElement> roadMapPointDescription;

    // Кнопка "Заказать" вверху после блока "Как это работает"
    @FindBy(how = How.XPATH, using = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private SelenideElement makeOrderButtonMid;

    // Заголовок "Вопросы о важном"
    @FindBy(how = How.XPATH, using = (".//div[text()='Вопросы о важном']"))
    private SelenideElement FAQHeader;

    // Коллекции элементы FAQ
    @FindBy(how = How.ID, using = "accordion__heading-0")
    private SelenideElement question1;

    @FindBy(how = How.ID, using = "accordion__heading-1")
    private SelenideElement question2;

    @FindBy(how = How.ID, using = "accordion__heading-2")
    private SelenideElement question3;

    @FindBy(how = How.ID, using = "accordion__heading-3")
    private SelenideElement question4;

    @FindBy(how = How.ID, using = "accordion__heading-4")
    private SelenideElement question5;

    @FindBy(how = How.ID, using = "accordion__heading-5")
    private SelenideElement question6;

    @FindBy(how = How.ID, using = "accordion__heading-6")
    private SelenideElement question7;

    @FindBy(how = How.ID, using = "accordion__heading-7")
    private SelenideElement question8;

    @FindBy(how = How.ID, using = "accordion__panel-0")
    private SelenideElement hiddenAnswerDiv1;

    @FindBy(how = How.ID, using = "accordion__panel-1")
    private SelenideElement hiddenAnswerDiv2;

    @FindBy(how = How.ID, using = "accordion__panel-2")
    private SelenideElement hiddenAnswerDiv3;

    @FindBy(how = How.ID, using = "accordion__panel-3")
    private SelenideElement hiddenAnswerDiv4;

    @FindBy(how = How.ID, using = "accordion__panel-4")
    private SelenideElement hiddenAnswerDiv5;

    @FindBy(how = How.ID, using = "accordion__panel-5")
    private SelenideElement hiddenAnswerDiv6;

    @FindBy(how = How.ID, using = "accordion__panel-6")
    private SelenideElement hiddenAnswerDiv7;

    @FindBy(how = How.ID, using = "accordion__panel-7")
    private SelenideElement hiddenAnswerDiv8;




    public ScooterServiceMainPage submitCookiesOnStart() {
        if (cookieForm.isDisplayed()) {
            cookieSubmitButton.click();
        }
        return this;
    }

    public YandexMainPage goToYandex() {
        yandexLogoRedirect.click();
        switchTo().window("Яндекс");
        return page(YandexMainPage.class);
    }

    public ScooterServiceMainPage goToMainScooterService() {
        scooterLogoRedirect.click();
        return this;
    }

    public OrderCreatePage createOrderByUpperButton() {
        makeOrderButtonUpper.click();
        return page(OrderCreatePage.class);
    }

    public OrderCreatePage createOrderByMidButton() {
        makeOrderButtonMid.scrollTo();
        makeOrderButtonMid.click();
        return page(OrderCreatePage.class);
    }

    public String openHiddenAnswer1() {
        question1.scrollTo();
        question1.click();
        hiddenAnswerDiv1.shouldBe(Condition.visible);
        return hiddenAnswerDiv1.getText();
    }

    public String openHiddenAnswer2() {
        question2.scrollTo();
        question2.click();
        hiddenAnswerDiv2.shouldBe(Condition.visible);
        return hiddenAnswerDiv2.getText();
    }

    public String openHiddenAnswer3() {
        question3.scrollTo();
        question3.click();
        hiddenAnswerDiv3.shouldBe(Condition.visible);
        return hiddenAnswerDiv3.getText();
    }

    public String openHiddenAnswer4() {
        question4.scrollTo();
        question4.click();
        hiddenAnswerDiv4.shouldBe(Condition.visible);
        return hiddenAnswerDiv4.getText();
    }

    public String openHiddenAnswer5() {
        question5.scrollTo();
        question5.click();
        hiddenAnswerDiv5.should(Condition.visible);
        return hiddenAnswerDiv5.getText();
    }

    public String openHiddenAnswer6() {
        question6.scrollTo();
        question6.click();
        hiddenAnswerDiv6.shouldBe(Condition.visible);
        return hiddenAnswerDiv6.getText();
    }

    public String openHiddenAnswer7() {
        question7.scrollTo();
        question7.click();
        hiddenAnswerDiv7.shouldBe(Condition.visible);
        return hiddenAnswerDiv7.getText();
    }

    public String openHiddenAnswer8() {
        question8.scrollTo();
        question8.click();
        hiddenAnswerDiv8.shouldBe(Condition.visible);
        return hiddenAnswerDiv8.getText();
    }
}
