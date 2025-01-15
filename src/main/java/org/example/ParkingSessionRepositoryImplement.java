package org.example;

import java.util.HashMap;
import java.util.Map;

public class ParkingSessionRepositoryImplement implements ParkingSessionRepository {

    private Map<String, ParkingSessionPO> parkingSessions = new HashMap<>();


    @Override
    public void save(ParkingSession parkingSession) {

        ParkingSessionPO parkingSessionPO = ParkingSessionPO.of(parkingSession);

        this.parkingSessions.put(parkingSession.getPlate(), parkingSessionPO);
    }

    @Override
    public ParkingSession find(String plate) {

        ParkingSessionPO parkingSessionPO = this.parkingSessions.get(plate);

        if (parkingSessionPO == null) {
            return null;
        }

        return ParkingSession.restore(parkingSessionPO);



    }

}
