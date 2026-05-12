package dev.pekelund.lenzetto;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class AvailabilityEvaluator {

    public boolean isAvailableInSkane(String text,
                                      List<String> productKeywords,
                                      List<String> skaneKeywords,
                                      List<String> availabilityKeywords,
                                      List<String> unavailabilityKeywords) {
        String normalized = text.toLowerCase(Locale.ROOT);
        boolean mentionsProduct = containsAny(normalized, productKeywords);
        boolean mentionsSkane = containsAny(normalized, skaneKeywords);
        boolean mentionsAvailability = containsAny(normalized, availabilityKeywords);
        boolean mentionsUnavailability = containsAny(normalized, unavailabilityKeywords);

        return mentionsProduct && mentionsSkane && mentionsAvailability && !mentionsUnavailability;
    }

    private boolean containsAny(String text, List<String> keywords) {
        return keywords.stream()
            .map(keyword -> keyword.toLowerCase(Locale.ROOT))
            .anyMatch(text::contains);
    }
}
