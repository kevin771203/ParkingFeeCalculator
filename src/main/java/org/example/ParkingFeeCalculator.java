package org.example;

import java.time.Duration;
import java.util.List;

public class ParkingFeeCalculator {

    private PriceBookRepository priceBookRepository;
    private Duration FIFTY_MINUTES = Duration.ofMinutes(15L);

    public ParkingFeeCalculator() {
        this(new PriceBookRepository(new PriceBook()));
    }

    public ParkingFeeCalculator(PriceBookRepository priceBookRepository) {

        this.priceBookRepository = priceBookRepository;

    }

    public long calculate(ParkingSession parkingSession) {

        PriceBook priceBook = priceBookRepository.getPriceBook();

        Duration duration = parkingSession.getTotalDuration();

        if (isShort(duration)) {
            return 0L;
        }

        List<DailySession> dailySessions = parkingSession.getDailySessions();

        return dailySessions.stream().mapToLong(priceBook::getDailyFee).sum();

    }

    private boolean isShort(Duration duration) {
        return duration.compareTo(FIFTY_MINUTES) <= 0;
    }

}