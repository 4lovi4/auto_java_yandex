package ru.practicum.scooter.page_object;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byId;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;

/*
Главная страница сервиса "https://qa-scooter.praktikum-services.ru/"
Версия после ревью:
* Добавил описание всех элементов главной страницы, не хватало блоков
"Самокат на пару дней" и "Как это работает"
* Элементы FAQ описал как коллекцию
 */

public class ScooterServiceMainPage {
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    //Ссылка на главую страницу Самокат
    @FindBy(how = How.XPATH, using = ".//a[@class='Header_LogoScooter__3lsAR']")
    private SelenideElement scooterLogoRedirect;

    // Логотип ссылка Яндекс
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

    // Коллекция вопросы FAQ
    @FindBy(how = How.XPATH, using = ".//div[@class='accordion__button']")
    private List<SelenideElement> importantQuestions;

    // Коллекция ответы FAQ
    @FindBy(how = How.XPATH, using = ".//div[@class='accordion__panel']")
    private List<SelenideElement> importantAnswers;


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

    public String openHiddenAnswer(int questionButtonIndex) {
        SelenideElement questionButton = this.importantQuestions.get(questionButtonIndex);
        questionButton.scrollTo();
        questionButton.click();

        SelenideElement answerPanel = Selenide.$(byId("accordion__panel-" + questionButtonIndex));
        answerPanel.shouldBe(Condition.visible);
        return answerPanel.getText();
    }

}
