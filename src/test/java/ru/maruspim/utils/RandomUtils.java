package ru.maruspim.utils;

import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.util.Locale;

// Class for fake data generating
public class RandomUtils {
        Faker faker = new Faker(new Locale("en-IND"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyy MMMM dd", Locale.ENGLISH);

        public String createRandomUserName() {

            return faker.name().firstName();
        }
        public String createRandomUserLogin() {

            return faker.name().username();
        }
        public String createRandomUserPassword() {

            return faker.internet().password();
        }
        public String createRandomUserEmail() {

            return faker.internet().emailAddress();
        }

}
