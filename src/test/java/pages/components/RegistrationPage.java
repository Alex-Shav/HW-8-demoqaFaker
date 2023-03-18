package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.DatePicker;
import pages.components.RegistrationRfModal;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    DatePicker datePicker = new DatePicker();

    RegistrationRfModal resultsModal = new RegistrationRfModal();

    private final SelenideElement
            FIRSTNAMEINPUT = $("#firstName"),
            LASTNAMEINPUT = $("#lastName"),
            EMAILINPUT = $("#userEmail"),
            PHONEINPUT = $x("//input[@id='userNumber']"),
            DATEOFBIRTHINPUT = $x("//input[@id='dateOfBirthInput']"),
            SUBJECTSAUTOCOMPLETE = $x("//input[@id='subjectsInput']"),
            UPLOADPICTURE = $x("//input[@id='uploadPicture']"),
            CURRENTADDRESS = $x("//textarea[@placeholder='Current Address']"),
            SELECTSTATE = $x("//div[text()='Select State']"),
            SELECTCITY = $x("//div[text()='Select City']"),
            SUBMITBUTTON = $x("//button[@id='submit']");


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage removeTheBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        FIRSTNAMEINPUT.setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        LASTNAMEINPUT.setValue(value);
        return this;
    }

    public RegistrationPage setEmail(String value) {
        EMAILINPUT.setValue(value);
        return this;
    }

    public RegistrationPage chooseGender(String gender) {
        $x("//label[text()='" + gender + "']").click();
        return this;
    }

    public RegistrationPage setPhone(String value) {
        PHONEINPUT.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        DATEOFBIRTHINPUT.click();
        datePicker.setDay(day, month, year);
        return this;
    }

    public RegistrationPage chooseSubjects(String subject) {
        SUBJECTSAUTOCOMPLETE.setValue(subject);
        $(".subjects-auto-complete__menu-list").$(byText(subject)).click();
        return this;
    }

    public RegistrationPage chooseHobbies(String hobby) {
        $x("//label[text()='" + hobby + "']").click();
        return this;
    }

    public RegistrationPage uploadPicture(File file) {
        UPLOADPICTURE.uploadFile(file);
        return this;
    }

    public RegistrationPage setCurrentAdress(String value) {
        CURRENTADDRESS.setValue(value);
        return this;
    }

    public RegistrationPage selectState(String state) {
        SELECTSTATE.click();
        $x("//div[text()='" + state + "']").click();
        return this;
    }

    public RegistrationPage selectCity(String city) {
        SELECTCITY.click();
        $x("//div[text()='" + city + "']").click();
        return this;
    }

    public void submitButtonClick() {
        SUBMITBUTTON.click();
    }

    public RegistrationPage verifyModalAppears() {
        resultsModal.verifyModalAppears();
        return this;
    }

    public RegistrationPage verifyResult(String value, String result) {
        resultsModal.verifyResult(value, result);
        return this;
    }
}