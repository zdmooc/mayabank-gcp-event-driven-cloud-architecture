package com.maya.finance.domain;

import java.util.List;

public record MatchingDecision(String requestId, String candidateCustomerId, double score, Decision decision,
                               List<String> rules) {
    public enum Decision { MATCHED, REVIEW_REQUIRED, NO_MATCH }
    public MatchingDecision { rules = rules == null ? List.of() : List.copyOf(rules); }
}
