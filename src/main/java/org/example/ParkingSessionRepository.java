package org.example;

public interface ParkingSessionRepository {
    void save(ParkingSession parkingSession1);

    ParkingSession find();
}
