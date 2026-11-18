package model;

import java.time.LocalDateTime;

public record Order (
    LocalDateTime orderTime,
    String companyName,
    double weightKg
    ) {}
