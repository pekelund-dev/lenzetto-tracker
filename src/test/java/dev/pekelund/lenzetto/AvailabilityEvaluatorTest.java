package dev.pekelund.lenzetto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class AvailabilityEvaluatorTest {

    private final AvailabilityEvaluator evaluator = new AvailabilityEvaluator();

    @Test
    void returnsTrueWhenLenzettoIsAvailableInSkane() {
        boolean result = evaluator.isAvailableInSkane(
            "Lenzetto finns i lager i Malmö, Skåne.",
            List.of("lenzetto"),
            List.of("skåne", "malmö"),
            List.of("i lager", "finns"),
            List.of("inte i lager")
        );

        assertThat(result).isTrue();
    }

    @Test
    void returnsFalseWhenSkaneIsNotMentioned() {
        boolean result = evaluator.isAvailableInSkane(
            "Lenzetto finns i lager i Stockholm.",
            List.of("lenzetto"),
            List.of("skåne", "malmö"),
            List.of("i lager", "finns"),
            List.of("inte i lager")
        );

        assertThat(result).isFalse();
    }

    @Test
    void returnsFalseWhenUnavailabilityKeywordIsPresent() {
        boolean result = evaluator.isAvailableInSkane(
            "Lenzetto i Malmö Skåne men inte i lager.",
            List.of("lenzetto"),
            List.of("skåne", "malmö"),
            List.of("i lager", "finns"),
            List.of("inte i lager", "slut")
        );

        assertThat(result).isFalse();
    }
}
