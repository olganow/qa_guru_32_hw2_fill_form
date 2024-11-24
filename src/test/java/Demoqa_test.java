import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Demoqa_test {

    private String firstNameUser = "Firstname",
            lastNameUser = "Secondname",
            email = "useremail@mail.ma",
            gender = "Female",
            phone = "1234567890",
            yearOfBirth = "1998",
            monthOfbirth = "May",
            dayOfbirth = "16",
            subjectOne = "Economics",
            subjectSecond = "Biology",
            hobby = "Sports",
            picture = "mypict.png",
            address = "ABC avenue, 000",
            state = "Uttar Pradesh",
            city = "Lucknow";


    private File file = new File("src/test/resources/" + picture);

    public final static String URL = "https://demoqa.com/automation-practice-form",
            REGISTRATION_FORM = "Student Registration Form",
            THANKS_SUBMITTING_FORM = "Thanks for submitting the form";


    @BeforeAll
    static void setUp() {
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void beforeEach() {
        open(URL);
    }

    @Test
    void successfulSubmitFormTest() {
        $(".practice-form-wrapper").shouldHave(text(REGISTRATION_FORM));
        $("#firstName").setValue(firstNameUser);
        $("#lastName").setValue(lastNameUser);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfbirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day--0" + dayOfbirth).click();

        $("#subjectsInput").setValue(subjectOne).pressEnter();
        $("#subjectsInput").setValue(subjectSecond).scrollTo().pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        //  $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").scrollIntoView(true).setValue(address);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").scrollIntoView(true).click();

        $("#example-modal-sizes-title-lg").shouldHave(text(THANKS_SUBMITTING_FORM));
        $x("//td[text()='Student Name']").parent().shouldHave(text(firstNameUser + " " + lastNameUser));
        $x("//td[text()='Student Email']").parent().shouldHave(text(email));
        $x("//td[text()='Gender']").parent().shouldHave(text(gender));
        $x("//td[text()='Mobile']").parent().shouldHave(text(phone));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfbirth + " " + monthOfbirth + "," + yearOfBirth));
        $x("//td[text()='Subjects']").parent().shouldHave(text(subjectOne + ", " + subjectSecond));
        $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby));
        $x("//td[text()='Picture']").parent().shouldHave(text(picture));
        $x("//td[text()='Address']").parent().shouldHave(text(address));
        $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));
    }

}
