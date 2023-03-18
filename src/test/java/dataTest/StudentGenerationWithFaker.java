package dataTest;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class StudentGenerationWithFaker {
    private final static String[] GENDERS = {"Male", "Female", "Other"};
    private final static String[] SUBJECTS = {"English", "Maths", "Biology"};
    private final static String[] HOBBIES = {"Sports", "Reading", "Music"};
    private final static String[] STATES = {"NCR", "Uttar Pradesh", "Haryana"};
    private final static Map<String, String[]> stateCityMap = Map.of(
            "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
            "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
            "Haryana", new String[]{"Karnal", "Panipat"},
            "Rajasthan", new String[]{"Jaipur", "Jaiselmer"});

    public static Student generationNewStudent() {
        Student student = new Student();
        Faker ruFaker = new Faker(new Locale("ru"));
        Faker enFaker = new Faker(new Locale("en"));
        student.setFirstName(ruFaker.name().firstName());
        student.setLastName(ruFaker.name().lastName());
        student.setEmail(enFaker.internet().emailAddress());
        student.setGender(pickingRandomValue(GENDERS));
        student.setPhoneNumber(7 + ruFaker.phoneNumber().subscriberNumber(9));
        LocalDate birthday = ruFaker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        student.setDayOfBirth(birthday.format(DateTimeFormatter.ofPattern("dd")));
        student.setMonthOfBirth(StringUtils.capitalize(birthday.getMonth().name().toLowerCase()));
        student.setYearOfBirth(String.valueOf(birthday.getYear()));
        student.setSubject(pickingRandomValue(SUBJECTS));
        student.setHobby(pickingRandomValue(HOBBIES));
        student.setAddress(ruFaker.address().fullAddress());
        String randomState = pickingRandomValue(STATES);
        student.setState(randomState);
        String randomCity = pickingRandomValue(generateRandomCities(randomState));
        student.setCity(randomCity);
        return student;
    }

    public static String pickingRandomValue(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public static String[] generateRandomCities(String state) {
        switch (state) {
            case "NCR":
                return new String[]{"Delhi", "Gurgaon", "Noida"};
            case "Uttar Pradesh":
                return new String[]{"Agra", "Lucknow", "Merrut"};
            case "Haryana":
                return new String[]{"Karnal", "Panipat"};
            default:
                return new String[]{""};
        }
    }

    public static Student generationNewStudentOnlyFaker() {
        Student student = new Student();
        Faker faker = new Faker(new Locale("en"));
        student.setFirstName(faker.name().firstName());
        student.setLastName(faker.name().lastName());
        student.setEmail(faker.internet().emailAddress());
        student.setGender(faker.options().nextElement(GENDERS));
        student.setPhoneNumber(7 + faker.phoneNumber().subscriberNumber(9));
        Date fakerDate = faker.date().birthday();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        student.setDayOfBirth(dayFormat.format(fakerDate));
        student.setMonthOfBirth(StringUtils.capitalize(monthFormat.format(fakerDate))); // StringUtils.capitalize - чтобы название месяца было с большой буквы
        student.setYearOfBirth(yearFormat.format(fakerDate));
        student.setSubject(faker.options().option(SUBJECTS));
        student.setHobby(faker.options().option(HOBBIES));
        student.setAddress(faker.address().fullAddress());


        var  stateCityMap1 = Map.of(
                "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
                "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
                "Haryana", new String[]{"Karnal", "Panipat"},
                "Rajasthan", new String[]{"Jaipur", "Jaiselmer"});

        String state = faker.options().nextElement(stateCityMap.keySet().toArray(new String[0]));


        student.setState(state);

        student.setCity(faker.options().nextElement(stateCityMap.get(state))); //получаем по ранее выбранному штату список городов и выбираем случайный элемент;

        return student;
    }

}