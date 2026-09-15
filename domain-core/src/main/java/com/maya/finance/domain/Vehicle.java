package com.maya.finance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Vehicle(String id, String customerId, String brand, String model, LocalDate registrationDate,
                      int mileage, BigDecimal initialValue) {
    public Vehicle {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("vehicle id is required");
        if (customerId == null || customerId.isBlank()) throw new IllegalArgumentException("customer id is required");
        if (mileage < 0) throw new IllegalArgumentException("mileage cannot be negative");
        if (initialValue == null || initialValue.signum() < 0) throw new IllegalArgumentException("initial value must be positive");
    }
}
