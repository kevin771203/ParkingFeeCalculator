package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


class ParkingFeeCalculatorTest {
    @Test
    void _over_150_mins_then_pay_150() {

        extracted("2025-01-01T00:00:00", "2025-01-01T02:30:00", 150L);

    }

    private void extracted(String startText, String endText, long expected) {
        ParkingFeeCalculator sut = new ParkingFeeCalculator();

        long actual = sut.calculate(
                LocalDateTime.parse(startText),
                LocalDateTime.parse(endText)
        );

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void _over_60_mins_then_pay_90() {

        extracted("2025-01-01T00:00:00", "2025-01-01T01:00:00", 90L);

    }

    @Test
    void _over_30_mins_then_pay_60() {

        extracted("2025-01-01T00:00:00", "2025-01-01T00:30:00", 60L);

    }

    @Test
    void _over_15_mins_NOT_free() {

        extracted("2025-01-01T00:00:00", "2025-01-01T00:15:00", 30L);

    }

    @Test
    void _15_mins_free() {

        extracted("2025-01-01T00:00:00","2025-01-01T00:14:59", 0L);

    }
}