import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ShouldCardDeliveryTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldCardDelivery () throws InterruptedException {
        final RegistrationByCardInfo registrationByCardInfo=DataGenerator.Registration.generateByCard("ru");
        String planningDate = DataGenerator.generateDate(4);
        String planningDate2 = DataGenerator.generateDate(10);
        holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id= city] input").setValue(registrationByCardInfo.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $(byName("name")).setValue(registrationByCardInfo.getName());
        $( "[data-test-id=phone] input").setValue(registrationByCardInfo.getPhone());

        $("[data-test-id=agreement]").click();
        $(byClassName("button")).click();
        $(byText("Успешно!")).shouldBe(Condition.appear, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate), Duration.ofSeconds(15));

        //$(byClassName("icon-button icon-button_size_m icon-button_theme_alfa-on-white notification__closer ")).click();

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate2);
        $(byClassName("button")).click();
        $(byText("Перепланировать")).shouldBe(Condition.appear, Duration.ofSeconds(15)).click();

       $(byText("Успешно!")).shouldBe(Condition.appear, Duration.ofSeconds(15));
       $(".notification__content")
               .shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate2), Duration.ofSeconds(15));
    }
}
