package org.example;

public class ParkingSessionRepositoryImplement implements ParkingSessionRepository {

    private ParkingSession parkingSession;
    @Override
    public void save(ParkingSession parkingSession1) {
        this.parkingSession = parkingSession1;
    }

    @Override
    public ParkingSession find() {
        return parkingSession;
    }
}
