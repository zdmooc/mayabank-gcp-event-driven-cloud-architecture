package com.maya.finance.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashSet;
import java.util.Set;

public final class GoldenMomentDetector {
    public enum GoldenMoment { CONTRACT_ANNIVERSARY, LEASE_END_APPROACHING, LOAN_END_APPROACHING, HIGH_MILEAGE, NEW_VEHICLE_ELIGIBILITY, SERVICE_RENEWAL }

    public Set<GoldenMoment> detect(LocalDate today, Contract contract, Vehicle vehicle) {
        var result = new LinkedHashSet<GoldenMoment>();
        long daysToEnd = ChronoUnit.DAYS.between(today, contract.endDate());
        if (daysToEnd >= 0 && daysToEnd <= 120) {
            if (contract.type() == Contract.ContractType.LEASE) result.add(GoldenMoment.LEASE_END_APPROACHING);
            if (contract.type() == Contract.ContractType.LOAN || contract.type() == Contract.ContractType.FINANCE) result.add(GoldenMoment.LOAN_END_APPROACHING);
            if (contract.type() == Contract.ContractType.SERVICE) result.add(GoldenMoment.SERVICE_RENEWAL);
        }
        if (vehicle != null && vehicle.mileage() >= 100_000) result.add(GoldenMoment.HIGH_MILEAGE);
        if (contract.startDate().getMonth().equals(today.getMonth()) && contract.startDate().getDayOfMonth() == today.getDayOfMonth()) {
            result.add(GoldenMoment.CONTRACT_ANNIVERSARY);
        }
        return Set.copyOf(result);
    }
}
