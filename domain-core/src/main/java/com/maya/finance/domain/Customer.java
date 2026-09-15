package com.maya.finance.domain;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;

public record Customer(String id, String firstName, String lastName, LocalDate birthDate, String postalCode,
                       String email, String phone, Map<String, String> externalReferences) {
    public Customer {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("customer id is required");
        externalReferences = externalReferences == null ? Map.of() : Map.copyOf(externalReferences);
    }
    public String normalizedEmail() { return email == null ? "" : email.trim().toLowerCase(Locale.ROOT); }
    public String normalizedPhone() { return phone == null ? "" : phone.replaceAll("[^0-9+]", ""); }
    public String normalizedName() { return normalize(firstName) + "|" + normalize(lastName); }
    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " ");
    }
}
