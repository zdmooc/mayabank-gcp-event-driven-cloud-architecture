package com.maya.finance.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ResidualValueCalculator {
    public record Result(String vehicleId, BigDecimal estimatedResidualValue, String currency, String model) {}

    public Result calculate(Vehicle vehicle, int vehicleAgeYears, double syntheticAnnualDepreciation) {
        if (vehicleAgeYears < 0) throw new IllegalArgumentException("vehicle age cannot be negative");
        if (syntheticAnnualDepreciation < 0 || syntheticAnnualDepreciation > 0.5) throw new IllegalArgumentException("synthetic depreciation must be between 0 and 0.5");
        double ageFactor = Math.max(0.20, 1.0 - syntheticAnnualDepreciation * vehicleAgeYears);
        double mileageFactor = Math.max(0.70, 1.0 - (vehicle.mileage() / 500_000.0));
        var estimate = vehicle.initialValue().multiply(BigDecimal.valueOf(ageFactor)).multiply(BigDecimal.valueOf(mileageFactor)).setScale(2, RoundingMode.HALF_UP);
        return new Result(vehicle.id(), estimate.max(BigDecimal.ZERO), "EUR", "SYNTHETIC_V1");
    }
}
