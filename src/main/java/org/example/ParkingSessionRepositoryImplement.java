package org.example;

import java.util.HashMap;
import java.util.Map;

public class ParkingSessionRepositoryImplement implements ParkingSessionRepository {

    private Map<String, ParkingSession> parkingSessions = new HashMap<>();
    @Override
    public void save(ParkingSession parkingSession) {

        this.parkingSessions.put(parkingSession.getPlate(), parkingSession);
    }

    @Override
    public ParkingSession find(String plate) {

        return this.parkingSessions.get(plate);

    }
}
