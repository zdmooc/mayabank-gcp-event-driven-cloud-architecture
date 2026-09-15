package com.maya.finance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Contract(String id, String customerId, String vehicleId, ContractType type, LocalDate startDate,
                       LocalDate endDate, ContractStatus status, BigDecimal monthlyPayment) {
    public enum ContractType { FINANCE, LEASE, LOAN, SERVICE, INSURANCE }
    public enum ContractStatus { DRAFT, ACTIVE, CLOSED }
    public Contract {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("contract id is required");
        if (customerId == null || customerId.isBlank()) throw new IllegalArgumentException("customer id is required");
        if (type == null || status == null) throw new IllegalArgumentException("type and status are required");
        if (startDate == null || endDate == null || endDate.isBefore(startDate)) throw new IllegalArgumentException("invalid contract dates");
        if (monthlyPayment != null && monthlyPayment.signum() < 0) throw new IllegalArgumentException("monthly payment cannot be negative");
    }
}
