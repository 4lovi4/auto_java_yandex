package ru.practicum.scooter.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.LocalDate;


public class OrderCreatePage {
    public static final String PATH = "order";

    @FindBy(how = How.XPATH, using = ".//a[@class='Header_LogoScooter__3lsAR']")
    private SelenideElement scooterLogoRedirect;

    @FindBy(how = How.XPATH, using = ".//a[@href='//yandex.ru']")
    private SelenideElement yandexLogoRedirect;

    @FindBy(how = How.XPATH, using = ".//input[@placeholder='* Имя']")
    private SelenideElement nameInput;

    @FindBy(how = How.XPATH, using = ".//input[@placeholder='* Фамилия']")
    private SelenideElement surnameInput;

    @FindBy(how = How.XPATH, using = ".//input[@placeholder='* Адрес: куда привезти заказ']")
    private SelenideElement addressInput;

    @FindBy(how = How.XPATH, using = ".//input[@placeholder='* Станция метро']")
    private SelenideElement stationInput;

    @FindBy(how = How.XPATH, using = ".//input[@placeholder='* Телефон: на него позвонит курьер']")
    private SelenideElement phoneInput;

    @FindBy(how = How.XPATH, using = ".//button[text()='Далее']")
    private SelenideElement nextOrderButton;

    @FindBy(how = How.XPATH, using = ".//div[@class='select-search__select']")
    private SelenideElement stationSelector;

    private SelenideElement stationName(String searchStationName) {
        return $(byXpath(String.format(".//div[text()='%s']", searchStationName)));
    }

    @FindBy(how = How.XPATH, using = ".//div[text()='Про аренду']")
    private SelenideElement aboutRent;

    @FindBy(how = How.XPATH, using = ".//div[text()='Для кого самокат']")
    private SelenideElement forWhomScooter;

    @FindBy(how = How.XPATH, using = ".//input[@placeholder='* Когда привезти самокат']")
    private SelenideElement pickDateField;

    @FindBy(how = How.XPATH, using = ".//div[@class='react-datepicker']")
    private SelenideElement datePicker;

    private SelenideElement dayOfMonth(int today) {
        return $$(byXpath(String.format(".//div[@class='react-datepicker__week']/div[text()='%d']", today))).first();
    }

    @FindBy(how = How.XPATH, using = ".//div[@class='Dropdown-placeholder']")
    private SelenideElement rentTermField;

    @FindBy(how = How.XPATH, using = ".//div[@class='Dropdown-placeholder is-selected']")
    private SelenideElement rentTermFieldSelected;

    @FindBy(how = How.XPATH, using = ".//div[@class='Dropdown-menu']")
    private SelenideElement rentTermDropDownForm;

    private SelenideElement rentTermOption(String term) {
        return $(byXpath(String.format(".//div[@class='Dropdown-menu']/div[text()='%s']", term)));
    }

    private SelenideElement colorScooterCheckBox (String colorId) {
        return $(byXpath(String.format(".//input[@id='%s']", colorId)));
    }

    @FindBy(how = How.XPATH, using = ".//div[@class='Order_Checkboxes__3lWSI Order_FilledContainer__2MKAk']")
    private SelenideElement colorCheckboxesFilled;

    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Комментарий для курьера']")
    private SelenideElement commentField;

    @FindBy(how = How.XPATH, using = ".//div[@class='Order_Buttons__1xGrp']/button[text()='Назад']")
    private SelenideElement backwardOrderButton;

    @FindBy(how = How.XPATH, using = ".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']")
    private SelenideElement makeOrderButton;

    @FindBy(how = How.XPATH, using = ".//div[text()='Хотите оформить заказ?']")
    private SelenideElement orderConfirmForm;

    @FindBy(how = How.XPATH, using = ".//div[@class='Order_Modal__YZ-d3']/div[@class='Order_Buttons__1xGrp']/button[text()='Да']")
    private SelenideElement orderConfirmButton;

    @FindBy(how = How.XPATH, using = ".//div[@class='Order_Modal__YZ-d3']/div[@class='Order_Buttons__1xGrp']/button[text()='Нет']")
    private SelenideElement orderNotConfirmButton;

    @FindBy(how = How.XPATH, using = ".//div[text()='Заказ оформлен']")
    private SelenideElement orderCreatedForm;

    @FindBy(how = How.XPATH, using = ".//div[@class='Order_Modal__YZ-d3']/div[@class='Order_ModalHeader__3FDaJ']/div[@class='Order_Text__2broi']")
    private SelenideElement orderCreatedMessage;

    @FindBy(how = How.XPATH, using = ".//button[text()='Посмотреть статус']")
    private SelenideElement showOrderStatusButton;

    public YandexMainPage goToYandex() {
        yandexLogoRedirect.click();
        switchTo().window("Яндекс");
        return page(YandexMainPage.class);
    }

    public ScooterServiceMainPage goToMainScooterService() {
        scooterLogoRedirect.click();
        return page(ScooterServiceMainPage.class);
    }

    public void enterCustomerName(String name) {
        nameInput.sendKeys(name);
    }

    public void enterCustomerSurname(String surname) {
        surnameInput.sendKeys(surname);
    }

    public void enterCustomerAddress(String address) {
        addressInput.sendKeys(address);
    }

    public void chooseMetroStation(String station) {
        stationInput.click();
        stationSelector.should(Condition.visible);
        SelenideElement pickedStation = stationName(station);
        pickedStation.scrollTo();
        pickedStation.click();
    }

    public void enterCustomerPhoneNumber(String phoneNumber) {
        phoneInput.sendKeys(phoneNumber);
    }

    public OrderCreatePage clickNextOrderForm() {
        nextOrderButton.click();
        aboutRent.should(Condition.visible);
        return this;
    }

    public OrderCreatePage fillForWhomOrderForm(String name, String surname, String address, String station, String phoneNumber) {
        enterCustomerName(name);
        enterCustomerSurname(surname);
        enterCustomerAddress(address);
        chooseMetroStation(station);
        enterCustomerPhoneNumber(phoneNumber);
        return clickNextOrderForm();
    }

    public void pickRentDate() {
        int currentDay = LocalDate.now().getDayOfMonth();
        pickDateField.click();
        datePicker.should(Condition.visible);
        dayOfMonth(currentDay).click();
    }

    public void enterRentTerm(String term) {
        rentTermField.click();
        rentTermDropDownForm.should(Condition.visible);
        rentTermOption(term).scrollTo().click();
        rentTermFieldSelected.should(Condition.visible);
    }

    public void pickColorForScooter(String colorId) {
        colorScooterCheckBox(colorId).click();
        colorCheckboxesFilled.should(Condition.appear);
    }

    public void enterComment(String commentText) {
        commentField.sendKeys(commentText);
    }

    public void clickMakeOrderButton() {
        makeOrderButton.click();
        orderConfirmForm.should(Condition.visible);
    }

    public void clickConfirmOrderButton() {
        orderConfirmButton.click();
    }

    public OrderCreatePage fillAboutRentForm(String rentTerm, String color, String commentText) {
        pickRentDate();
        enterRentTerm(rentTerm);
        if (color.equals("")) {
            pickColorForScooter("black");
            pickColorForScooter("grey");
        }
        else pickColorForScooter(color);
        enterComment(commentText);
        clickMakeOrderButton();
        orderCreatedForm.should(Condition.visible);
        return this;
    }

    public String getOrderCreatedMessage() {
        return orderCreatedMessage.getText();
    }
}
