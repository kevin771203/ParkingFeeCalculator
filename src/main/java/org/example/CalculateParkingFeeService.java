package org.example;

import java.time.Duration;
import java.util.List;

public class CalculateParkingFeeService {

    private final ParkingSessionRepositoryImplement parkingSessionRepository;
//    private final ParkingSessionRepository parkingSessionRepository = this.parkingSessionRepository;
    private PriceBookRepository priceBookRepository;
    private Duration FIFTY_MINUTES = Duration.ofMinutes(15L);

    public CalculateParkingFeeService() {
        this(new PriceBookRepositoryImplement(new PriceBook()), new ParkingSessionRepositoryImplement());
    }

    public CalculateParkingFeeService(PriceBookRepository priceBookRepository, ParkingSessionRepositoryImplement parkingSessionRepository) {

        this.priceBookRepository = priceBookRepository;

        this.parkingSessionRepository = parkingSessionRepository;

    }

    public long calculate(ParkingSession parkingSession1) {

        parkingSessionRepository.save(parkingSession1);

        ParkingSession parkingSession = parkingSessionRepository.find();

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