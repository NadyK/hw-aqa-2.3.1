import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class RegistrationByCardInfo {

    private final String city;
    private final String name;
    private final String phone;
}



