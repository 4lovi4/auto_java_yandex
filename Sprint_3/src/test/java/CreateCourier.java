import client.QaScooterClient;
import models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import io.qameta.allure.Step;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CreateCourier {
    private ScooterTestSteps testSteps = new ScooterTestSteps();
    private HashMap<String, String> courierLogin;

    @Before
    void prepareTest() {
        this.courierLogin = testSteps.createCourier(testSteps.genRandomAlfaString(),
                testSteps.genRandomAlfaNumString(), testSteps.genRandomAlfaString());

    }

    @After
    void cleanUp() {

    }

}
