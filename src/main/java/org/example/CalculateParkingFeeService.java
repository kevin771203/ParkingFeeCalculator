package org.example;

import java.time.Duration;
import java.util.List;

public class CalculateParkingFeeService {

    private final ParkingSessionRepository parkingSessionRepository;
    private PriceBookRepository priceBookRepository;
    private Duration FIFTY_MINUTES = Duration.ofMinutes(15L);



    public CalculateParkingFeeService(PriceBookRepository priceBookRepository, ParkingSessionRepository parkingSessionRepository) {

        this.priceBookRepository = priceBookRepository;

        this.parkingSessionRepository = parkingSessionRepository;

    }

    public long calculate(String plate) {


        ParkingSession parkingSession = parkingSessionRepository.find(plate);
        if (parkingSession == null) {
            return 0L;
        }
        
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