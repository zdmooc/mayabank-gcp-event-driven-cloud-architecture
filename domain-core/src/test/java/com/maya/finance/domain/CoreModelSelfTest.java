package com.maya.finance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public final class CoreModelSelfTest {
    public static void main(String[] args) {
        var customer = new Customer("CUS-1", "Alex", "Martin", LocalDate.of(1980, 1, 2), "75001",
                "alex@example.test", "+33123456789", Map.of("CRM", "C-1"));
        var vehicle = new Vehicle("VEH-1", customer.id(), "Brand A", "Model X", LocalDate.of(2024, 1, 1),
                1000, new BigDecimal("30000"));
        var contract = new Contract("CON-1", customer.id(), vehicle.id(), Contract.ContractType.LEASE,
                LocalDate.of(2024, 1, 1), LocalDate.of(2027, 1, 1), Contract.ContractStatus.ACTIVE,
                new BigDecimal("450"));
        check(contract.customerId().equals(customer.id()), "customer link");
        check(contract.vehicleId().equals(vehicle.id()), "vehicle link");
        check(customer.normalizedEmail().equals("alex@example.test"), "email normalization");
        System.out.println("CORE_MODEL_SELF_TEST=PASS");
    }
    private static void check(boolean condition, String message) { if (!condition) throw new AssertionError(message); }
}
