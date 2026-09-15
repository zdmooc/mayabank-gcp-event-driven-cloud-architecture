package com.maya.finance.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public final class MatchingSelfTest {
    public static void main(String[] args) {
        var customer = new Customer("CUS-10045", "Zoé", "Martin", LocalDate.of(1985, 2, 10), "75011",
                "zoe@example.test", "+33 6 10 20 30 40", Map.of("CRM", "CRM-77"));
        var exact = new CustomerIdentity("CRM-77", "Zoé", "Martin", LocalDate.of(1985, 2, 10), "75011",
                "ZOE@EXAMPLE.TEST", "+33610203040");
        var decision = new CustomerMatcher().evaluate("M-298", exact, List.of(customer));
        check(decision.decision() == MatchingDecision.Decision.MATCHED, "expected MATCHED");
        check(decision.score() == 1.0, "expected capped score 1.0");

        var review = new CustomerIdentity(null, "Other", "Name", null, null, "zoe@example.test", null);
        var reviewDecision = new CustomerMatcher().evaluate("M-299", review, List.of(customer));
        check(reviewDecision.decision() == MatchingDecision.Decision.REVIEW_REQUIRED, "expected REVIEW_REQUIRED");

        var none = new CustomerIdentity(null, "Nobody", "Else", LocalDate.of(1999, 1, 1), "99999", "x@y.test", "000");
        var noMatch = new CustomerMatcher().evaluate("M-300", none, List.of(customer));
        check(noMatch.decision() == MatchingDecision.Decision.NO_MATCH, "expected NO_MATCH");
        System.out.println("MATCHING_SELF_TEST=PASS");
    }
    private static void check(boolean condition, String message) { if (!condition) throw new AssertionError(message); }
}
