package org.example;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class ParkingSessionPO {

    private String plate;
    private Long start;
    private Long end;

    @NotNull
    static ParkingSessionPO of(ParkingSession parkingSession) {
        ParkingSessionPO parkingSessionPO = new ParkingSessionPO();
        parkingSessionPO.setPlate(parkingSession.getPlate());
        parkingSessionPO.setStart(parkingSession.getStart().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        parkingSessionPO.setEnd(parkingSession.getEnd() == null
                ? null
                : parkingSession.getEnd().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        return parkingSessionPO;
    }

    @NotNull
    static ParkingSession toEntity(ParkingSessionPO parkingSessionPO) {
        ParkingSession parkingSession = new ParkingSession(
                parkingSessionPO.getPlate(),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(parkingSessionPO.getStart()), ZoneId.systemDefault()),
                parkingSessionPO.getEnd() == null
                        ? null
                        : LocalDateTime.ofInstant(Instant.ofEpochMilli(parkingSessionPO.getEnd()), ZoneId.systemDefault())
        );
        return parkingSession;
    }
}
