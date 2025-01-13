package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


class ParkingFeeCalculatorTest {
    @Test
    void _over_150_mins_then_pay_150() {

        ParkingFeeCalculator sut = new ParkingFeeCalculator();

        long actual = sut.calculate(
                LocalDateTime.parse("2025-01-01T00:00:00"),
                LocalDateTime.parse("2025-01-01T02:30:00")
        );

        Assertions.assertThat(actual).isEqualTo(150L);

    }

    @Test
    void _over_60_mins_then_pay_90() {

        ParkingFeeCalculator sut = new ParkingFeeCalculator();

        long actual = sut.calculate(
                LocalDateTime.parse("2025-01-01T00:00:00"),
                LocalDateTime.parse("2025-01-01T01:00:00")
        );

        Assertions.assertThat(actual).isEqualTo(90L);

    }

    @Test
    void _over_30_mins_then_pay_60() {

        ParkingFeeCalculator sut = new ParkingFeeCalculator();

        long actual = sut.calculate(
                LocalDateTime.parse("2025-01-01T00:00:00"),
                LocalDateTime.parse("2025-01-01T00:30:00")
        );

        Assertions.assertThat(actual).isEqualTo(60L);

    }

    @Test
    void _over_15_mins_NOT_free() {

        ParkingFeeCalculator sut = new ParkingFeeCalculator();

        long actual = sut.calculate(
                LocalDateTime.parse("2025-01-01T00:00:00"),
                LocalDateTime.parse("2025-01-01T00:15:00")
        );

        Assertions.assertThat(actual).isEqualTo(30L);

    }

    @Test
    void _15_mins_free() {

        ParkingFeeCalculator sut = new ParkingFeeCalculator();

        long actual = sut.calculate(
                LocalDateTime.parse("2025-01-01T00:00:00"),
                LocalDateTime.parse("2025-01-01T00:14:59")
        );

        Assertions.assertThat(actual).isEqualTo(0L);

    }
}