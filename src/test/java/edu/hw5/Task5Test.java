package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    void validLicensePlateCheck() {
        final String licensePlate = "Р235СМ96";

        var isLicensePlateValid = Task5.isLicensePlateValid(licensePlate);

        assertThat(isLicensePlateValid).isTrue();
    }

    @Test
    void invalidLicensePlateWithDigitsFirstCheck() {
        final String licensePlate = "123АВЕ777";

        var isLicensePlateValid = Task5.isLicensePlateValid(licensePlate);

        assertThat(isLicensePlateValid).isFalse();
    }

    @Test
    void invalidLicensePlateWithInvalidLettersCheck() {
        final String licensePlate = "А123ВГ77";

        var isLicensePlateValid = Task5.isLicensePlateValid(licensePlate);

        assertThat(isLicensePlateValid).isFalse();
    }
}
