package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderPositiveTest {
    @Test
    void shouldSurnameWithSpaceTest() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[type=text]").setValue("Ларина Татьяна");
        form.$("[type=tel]").setValue("+79161232323");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void shouldSurnameWithHyphenTest() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[type=text]").setValue("Ларина-Иванова Татьяна");
        form.$("[type=tel]").setValue("+79161232323");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
