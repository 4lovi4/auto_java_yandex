import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Condition.visible;
import static java.time.Duration.ofSeconds;


public class MetroHomePage {
    @FindBy(how = How.CLASS_NAME, using = "select_metro__button")
    private SelenideElement selectCityButton;
    
    @FindBy(how = How.XPATH, using = ".//input[@placeholder=\"Откуда\"]")
    private SelenideElement fieldFrom;

    @FindBy(how = How.XPATH, using = ".//input[@placeholder=\"Куда\"]")
    private SelenideElement fieldTo;

    @FindBy(how = How.CLASS_NAME, using = "route-details-block _init")
    private ElementsCollection routeStationFromTo;

    public void waitForLoadHomePage() {
        $(byText("Театральная")).shouldBe(visible, ofSeconds(8));
    }

    public void chooseCity(String cityName) {
        selectCityButton.click();
        $(byText(cityName)).click();
    }

    public void setStationFrom(String stationFromName) {
        fieldFrom.setValue(stationFromName).sendKeys(Keys.DOWN, Keys.ENTER);
    }

    public void setStationTo(String stationToName) {
        fieldTo .setValue(stationToName).sendKeys(Keys.DOWN, Keys.ENTER);
    }

    public void waitForLoadRoute() {
        $(byText("Получить ссылку на маршрут")).shouldBe(visible);
    }

    public void buildRoute (String stationFrom, String stationTo) {
        setStationFrom(stationFrom);
        setStationTo(stationTo);
        waitForLoadRoute();
    }

    public SelenideElement getRouteStationFrom() {
        return this.routeStationFromTo.get(0);
    }

    public SelenideElement getRouteStationTo() {
        return this.routeStationFromTo.get(1);
    }


}
