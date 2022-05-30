import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {}
    public static class Registration {
        private Registration() {}

        public static RegistrationByCardInfo generateByCard(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationByCardInfo(faker.address().city(),
                    faker.name().fullName(), faker.numerify("+7##########")
            );
        }
    }
}
