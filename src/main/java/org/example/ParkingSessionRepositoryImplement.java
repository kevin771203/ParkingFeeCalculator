package org.example;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class ParkingSessionRepositoryImplement implements ParkingSessionRepository {

    private Map<String, ParkingSession> parkingSessionsOld = new HashMap<>();
    private Map<String, ParkingSessionPO> parkingSessions = new HashMap<>();


    @Override
    public void save(ParkingSession parkingSession) {

        ParkingSessionPO parkingSessionPO = ParkingSessionPO.of(parkingSession);

        this.parkingSessions.put(parkingSession.getPlate(), parkingSessionPO);
    }

    @Override
    public ParkingSession find(String plate) {

        ParkingSessionPO parkingSessionPO = this.parkingSessions.get(plate);

        return parkingSessionPO == null
                ? null
                : new ParkingSession(
                parkingSessionPO.getPlate(),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(parkingSessionPO.getStart()), ZoneId.systemDefault()),
                parkingSessionPO.getEnd() == null
                        ? null
                        : LocalDateTime.ofInstant(Instant.ofEpochMilli(parkingSessionPO.getEnd()), ZoneId.systemDefault())
        );


    }
}
