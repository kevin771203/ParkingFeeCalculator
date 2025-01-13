package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


class ParkingFeeCalculatorTest {

    private LocalDateTime start;
    private LocalDateTime end;
    private long actual;
    private ParkingFeeCalculator sut;

    @BeforeEach
    void setUp() {
        sut = new ParkingFeeCalculator();
    }

    @Test
    void _over_150_mins_then_pay_150() {

        extracted("2025-01-01T00:00:00", "2025-01-01T02:30:00", 150L);

    }

    private void extracted(String startText, String endText, long expected) {

        given_parking_starts_at(startText);
        give_parking_ends_at(endText);

        when_calculator();

        then_should_have(expected);
    }

    private void then_should_have(long expected) {
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    private void give_parking_ends_at(String endText) {
        end = LocalDateTime.parse(endText);
    }

    private void given_parking_starts_at(String startText) {
        start = LocalDateTime.parse(startText);
    }

    private void when_calculator() {
        actual = sut.calculate(
                start,
                end
        );
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