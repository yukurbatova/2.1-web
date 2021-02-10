package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderNegativeTest {
    @Test
    void shouldEmptyAllFieldsTest() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[type=text]").setValue("");
        form.$("[type=tel]").setValue("");
        form.$("[type=button]").click();
        $("[data-test-id=name] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyPhoneTest() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[type=text]").setValue("Ларина Тятьяна");
        form.$("[type=tel]").setValue("");
        form.$("[type=button]").click();
        $("[data-test-id=phone] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyAgreementTest() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[type=text]").setValue("Ларина Тятьяна");
        form.$("[type=tel]").setValue("+79161232323");
        form.$("[type=button]").click();
        $(".checkbox__text").shouldHave(Condition.cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void shouldNotRussianSurnameTest() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[type=text]").setValue("Larina Tatyana");
        form.$("[type=tel]").setValue("+79161232323");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $(".input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldWrongPhoneFormatTest() {
        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[type=text]").setValue("Ларина Татьяна");
        form.$("[type=tel]").setValue("+7(916)-123-23-23");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $("[data-test-id=phone] .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
