package org.example;

public class ParkingSessionRepositoryImplement implements ParkingSessionRepository {

    private ParkingSession parkingSession;
    @Override
    public void save(ParkingSession parkingSession) {
        this.parkingSession = parkingSession;
    }

    @Override
    public ParkingSession find() {
        return parkingSession;
    }
}
