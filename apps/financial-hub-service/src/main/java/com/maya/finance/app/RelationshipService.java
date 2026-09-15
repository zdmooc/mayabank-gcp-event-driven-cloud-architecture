package com.maya.finance.app;

import com.maya.finance.domain.GoldenMomentDetector;
import com.maya.finance.domain.RecommendationEngine;
import com.maya.finance.domain.RelationshipView;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class RelationshipService {
    private final DemoStore store;
    private final GoldenMomentDetector detector = new GoldenMomentDetector();
    private final RecommendationEngine engine = new RecommendationEngine();
    public RelationshipService(DemoStore store) { this.store = store; }
    public RelationshipView relationship(String customerId, LocalDate asOf) {
        var customer = store.customer(customerId);
        var vehicles = store.vehiclesForCustomer(customerId);
        var contracts = store.contractsForCustomer(customerId);
        var recs = new ArrayList<RecommendationEngine.Recommendation>();
        for (var contract : contracts) recs.addAll(engine.generate(customerId, detector.detect(asOf, contract, store.findVehicle(contract.vehicleId()))));
        return new RelationshipView(customer, vehicles, contracts, recs.stream().distinct().toList());
    }
}
