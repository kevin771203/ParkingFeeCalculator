package org.example;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class ParkingSessionRepositoryImplement implements ParkingSessionRepository {

    private Map<String, ParkingSession> parkingSessionsOld = new HashMap<>();
    private Map<String, ParkingSessionPO> parkingSessions = new HashMap<>();


    @Override
    public void save(ParkingSession parkingSession) {

        ParkingSessionPO parkingSessionPO = new ParkingSessionPO();
        parkingSessionPO.setPlate(parkingSession.getPlate());
        parkingSessionPO.setStart(parkingSession.getStart().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        parkingSessionPO.setEnd(parkingSession.getEnd() == null
                ? null
                :parkingSession.getEnd().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
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
