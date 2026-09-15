package com.maya.finance.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class CustomerMatcher {
    public MatchingDecision evaluate(String requestId, CustomerIdentity incoming, List<Customer> candidates) {
        return candidates.stream().map(c -> score(requestId, incoming, c))
                .max(Comparator.comparingDouble(MatchingDecision::score))
                .orElse(new MatchingDecision(requestId, null, 0.0, MatchingDecision.Decision.NO_MATCH, List.of()));
    }

    private MatchingDecision score(String requestId, CustomerIdentity in, Customer c) {
        var rules = new ArrayList<String>();
        double score = 0.0;
        if (!in.normalizedEmail().isBlank() && in.normalizedEmail().equals(c.normalizedEmail())) { score += 0.55; rules.add("EMAIL_EXACT"); }
        if (!in.normalizedPhone().isBlank() && in.normalizedPhone().equals(c.normalizedPhone())) { score += 0.30; rules.add("PHONE_EXACT"); }
        if (in.externalCustomerId() != null && c.externalReferences().containsValue(in.externalCustomerId())) { score += 1.0; rules.add("EXTERNAL_ID_EXACT"); }
        if (in.normalizedName().equals(c.normalizedName())) { score += 0.12; rules.add("NAME_NORMALIZED"); }
        if (Objects.equals(in.birthDate(), c.birthDate()) && in.birthDate() != null) { score += 0.16; rules.add("BIRTHDATE_EXACT"); }
        if (in.normalizedPostalCode().equals(normalizePostalCode(c.postalCode())) && !in.normalizedPostalCode().isBlank()) { score += 0.07; rules.add("POSTAL_CODE_EXACT"); }
        score = Math.min(score, 1.0);
        var decision = score >= 0.80 ? MatchingDecision.Decision.MATCHED
                : score >= 0.55 ? MatchingDecision.Decision.REVIEW_REQUIRED
                : MatchingDecision.Decision.NO_MATCH;
        return new MatchingDecision(requestId, c.id(), round(score), decision, rules);
    }

    private static String normalizePostalCode(String value) {
        return value == null ? "" : value.trim().toLowerCase().replace(" ", "");
    }
    private static double round(double value) { return Math.round(value * 100.0) / 100.0; }
}
