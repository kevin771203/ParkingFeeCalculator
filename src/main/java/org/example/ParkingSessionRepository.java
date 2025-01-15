package org.example;

public interface ParkingSessionRepository {
    void save(ParkingSession parkingSession);

    ParkingSession find();
}
