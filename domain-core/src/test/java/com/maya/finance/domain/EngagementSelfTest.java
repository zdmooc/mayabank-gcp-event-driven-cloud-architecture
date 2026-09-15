package com.maya.finance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public final class EngagementSelfTest {
    public static void main(String[] args) {
        var customer = new Customer("CUS-10045", "Zoé", "Martin", LocalDate.of(1985,2,10), "75011", "zoe@example.test", "+33610203040", Map.of());
        var vehicle = new Vehicle("VEH-45", customer.id(), "Brand A", "Model X", LocalDate.of(2023,1,10), 105000, new BigDecimal("40000"));
        var lease = new Contract("CON-100", customer.id(), vehicle.id(), Contract.ContractType.LEASE, LocalDate.of(2023,10,1), LocalDate.of(2026,12,15), Contract.ContractStatus.ACTIVE, new BigDecimal("520"));
        var moments = new GoldenMomentDetector().detect(LocalDate.of(2026,9,15), lease, vehicle);
        check(moments.contains(GoldenMomentDetector.GoldenMoment.LEASE_END_APPROACHING), "lease-end moment");
        check(moments.contains(GoldenMomentDetector.GoldenMoment.HIGH_MILEAGE), "high-mileage moment");
        var recs = new RecommendationEngine().generate(customer.id(), moments);
        check(recs.size() == 2, "two recommendations expected");
        var residual = new ResidualValueCalculator().calculate(vehicle, 3, 0.12);
        check(residual.estimatedResidualValue().compareTo(BigDecimal.ZERO) > 0, "positive residual");
        var view = new RelationshipView(customer, java.util.List.of(vehicle), java.util.List.of(lease), recs);
        check(view.contracts().size() == 1 && view.recommendations().size() == 2, "relationship aggregation");
        System.out.println("ENGAGEMENT_SELF_TEST=PASS residual=" + residual.estimatedResidualValue());
    }
    private static void check(boolean condition, String message) { if (!condition) throw new AssertionError(message); }
}
