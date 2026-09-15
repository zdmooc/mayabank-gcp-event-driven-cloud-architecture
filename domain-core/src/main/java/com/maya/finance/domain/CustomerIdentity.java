package com.maya.finance.domain;

import java.time.LocalDate;
import java.util.Locale;

public record CustomerIdentity(String externalCustomerId, String firstName, String lastName, LocalDate birthDate,
                               String postalCode, String email, String phone) {
    String normalizedEmail() { return normalize(email); }
    String normalizedPhone() { return phone == null ? "" : phone.replaceAll("[^0-9+]", ""); }
    String normalizedName() { return normalize(firstName) + "|" + normalize(lastName); }
    String normalizedPostalCode() { return normalize(postalCode).replace(" ", ""); }
    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " ");
    }
}
