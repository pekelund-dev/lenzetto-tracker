package dev.pekelund.lenzetto;

public record AvailabilityCheckResult(String sourceName, String sourceUrl, boolean availableInSkane, String snippet, String error) {
}
