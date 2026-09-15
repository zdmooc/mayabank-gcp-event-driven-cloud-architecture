package com.maya.finance.domain;

import java.util.List;

public record RelationshipView(Customer customer, List<Vehicle> vehicles, List<Contract> contracts,
                               List<RecommendationEngine.Recommendation> recommendations) {
    public RelationshipView {
        if (customer == null) throw new IllegalArgumentException("customer is required");
        vehicles = vehicles == null ? List.of() : List.copyOf(vehicles);
        contracts = contracts == null ? List.of() : List.copyOf(contracts);
        recommendations = recommendations == null ? List.of() : List.copyOf(recommendations);
    }
}
