package org.example;

import java.time.LocalDateTime;

public record ParkSession(LocalDateTime start, LocalDateTime end) {
}