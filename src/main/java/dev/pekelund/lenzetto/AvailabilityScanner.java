package dev.pekelund.lenzetto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityScanner {

    private final TrackerProperties properties;
    private final AvailabilityEvaluator evaluator;

    public AvailabilityScanner(TrackerProperties properties, AvailabilityEvaluator evaluator) {
        this.properties = properties;
        this.evaluator = evaluator;
    }

    public List<AvailabilityCheckResult> scanAll() {
        List<AvailabilityCheckResult> results = new ArrayList<>();

        for (PharmacySource source : properties.getSources()) {
            try {
                String text = Jsoup.connect(source.url())
                    .userAgent("Mozilla/5.0 (compatible; lenzetto-tracker/1.0)")
                    .timeout(15_000)
                    .get()
                    .text();

                boolean availableInSkane = evaluator.isAvailableInSkane(
                    text,
                    properties.getProductKeywords(),
                    properties.getSkaneKeywords(),
                    properties.getAvailabilityKeywords(),
                    properties.getUnavailabilityKeywords()
                );

                results.add(new AvailabilityCheckResult(
                    source.name(),
                    source.url(),
                    availableInSkane,
                    buildSnippet(text),
                    null
                ));
            } catch (IOException ex) {
                results.add(new AvailabilityCheckResult(
                    source.name(),
                    source.url(),
                    false,
                    null,
                    ex.getMessage()
                ));
            }
        }

        return results;
    }

    private String buildSnippet(String text) {
        String compact = text.replaceAll("\\s+", " ").trim();
        int maxLength = 320;
        if (compact.length() <= maxLength) {
            return compact;
        }

        String lower = compact.toLowerCase(Locale.ROOT);
        int index = lower.indexOf("lenzetto");
        if (index < 0) {
            return compact.substring(0, maxLength) + "...";
        }

        int start = Math.max(0, index - 120);
        int end = Math.min(compact.length(), start + maxLength);
        return compact.substring(start, end) + "...";
    }
}
