package com.maya.finance.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class RecommendationEngine {
    public enum RecommendationType { VEHICLE_RENEWAL, NEW_FINANCE_OFFER, LEASE_REVIEW, SERVICE_CONTRACT }
    public record Recommendation(String customerId, RecommendationType type, double score, String reason) {}

    public List<Recommendation> generate(String customerId, Set<GoldenMomentDetector.GoldenMoment> moments) {
        var result = new ArrayList<Recommendation>();
        if (moments.contains(GoldenMomentDetector.GoldenMoment.LEASE_END_APPROACHING)) result.add(new Recommendation(customerId, RecommendationType.VEHICLE_RENEWAL, 0.87, "LEASE_END_APPROACHING"));
        if (moments.contains(GoldenMomentDetector.GoldenMoment.LOAN_END_APPROACHING)) result.add(new Recommendation(customerId, RecommendationType.NEW_FINANCE_OFFER, 0.80, "LOAN_END_APPROACHING"));
        if (moments.contains(GoldenMomentDetector.GoldenMoment.HIGH_MILEAGE)) result.add(new Recommendation(customerId, RecommendationType.LEASE_REVIEW, 0.72, "HIGH_MILEAGE"));
        return List.copyOf(result);
    }
}
