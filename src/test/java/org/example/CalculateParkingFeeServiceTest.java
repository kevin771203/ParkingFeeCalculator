package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


class CalculateParkingFeeServiceTest {

    private LocalDateTime start;
    private LocalDateTime end;
    private long actual;
    private CalculateParkingFeeService sut;
    private ParkingSessionRepository parkingSessionRepository = new ParkingSessionRepositoryImplement();

    @BeforeEach
    void setUp() {
        sut = new CalculateParkingFeeService(
                new PriceBookRepositoryImplement(new PriceBook()),
                parkingSessionRepository);
    }

    @Test
    void _over_150_mins_then_pay_150() {

        given_parking_starts_at("2025-01-02T00:00:00");

        give_parking_ends_at("2025-01-02T02:30:01");

        when_calculator();

        then_should_pay(150L);

    }

    private void then_should_pay(long expected) {
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    private void given_parking_starts_at(String startText) {
        parkingSessionRepository.save(
                new ParkingSession(LocalDateTime.parse(startText), null)
        );
    }
    private void give_parking_ends_at(String endText) {

        ParkingSession parkingSession = parkingSessionRepository.find("ABC-1234");
        parkingSession.setEnd(LocalDateTime.parse(endText));


        parkingSessionRepository.save(
                parkingSession
        );
    }



    private void when_calculator() {
        actual = sut.calculate(
        );
    }

    @Test
    void _over_60_mins_then_pay_90() {

        given_parking_starts_at("2025-01-02T00:00:00");

        give_parking_ends_at("2025-01-02T01:00:01");

        when_calculator();

        then_should_pay(90L);

    }

    @Test
    void _partial_day_then_whole_day() {

        given_parking_starts_at("2025-01-02T23:50:00");

        give_parking_ends_at("2025-01-04T00:00:00");

        when_calculator();

        then_should_pay(30L + 150L);

    }

    @Test
    void _whole_day_then_partial_day() {

        given_parking_starts_at("2025-01-02T00:00:00");

        give_parking_ends_at("2025-01-03T00:10:00");

        when_calculator();

        then_should_pay(150L + 30L);

    }

    @Test
    void _two_whole_days() {

        given_parking_starts_at("2025-01-02T00:00:00");

        give_parking_ends_at("2025-01-04T00:00:00");

        when_calculator();

        then_should_pay(150L + 150L);

    }

    @Test
    void _over_30_mins_then_pay_60() {

        given_parking_starts_at("2025-01-02T00:01:00");

        give_parking_ends_at("2025-01-02T00:31:01");

        when_calculator();

        then_should_pay(60L);

    }

    @Test
    void _over_15_mins_NOT_free() {

        given_parking_starts_at("2025-01-02T00:00:00");

        give_parking_ends_at("2025-01-02T00:15:01");

        when_calculator();

        then_should_pay(30L);

    }
    @Test
    void _sunday_pay_50_half_hour() {

        given_parking_starts_at("2025-01-05T00:00:00");

        give_parking_ends_at("2025-01-05T00:15:01");

        when_calculator();

        then_should_pay(50L);

    }

    @Test
    void _saturday_daily_limit_is_2400() {

        given_parking_starts_at("2025-01-04T00:00:00");

        give_parking_ends_at("2025-01-05T00:00:00");

        when_calculator();

        then_should_pay(2400L);

    }

    @Test
    void _national_holiday_pay_50_half_hour() {

        given_parking_starts_at("2025-01-01T00:00:00");

        give_parking_ends_at("2025-01-01T00:15:01");

        when_calculator();

        then_should_pay(50L);

    }

    @Test
    void _saturday_pay_50_half_hour() {

        given_parking_starts_at("2025-01-04T00:00:00");

        give_parking_ends_at("2025-01-04T00:15:01");

        when_calculator();

        then_should_pay(50L);

    }

    @Test
    void _15_mins_free() {

        given_parking_starts_at("2025-01-02T00:00:00");

        give_parking_ends_at("2025-01-02T00:15:00");

        when_calculator();

        then_should_pay(0L);

    }
}