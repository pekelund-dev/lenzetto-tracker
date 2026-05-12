package dev.pekelund.lenzetto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "lenzetto.tracker")
public class TrackerProperties {

    private String scheduleCron = "0 0 */2 * * *";
    private boolean emailEnabled = true;
    private String recipient = "pekelund@gmail.com";

    private List<PharmacySource> sources = new ArrayList<>(List.of(
        new PharmacySource("FASS", "https://www.fass.se"),
        new PharmacySource("Apotea", "https://www.apotea.se/lenzetto"),
        new PharmacySource("Apotek Hjärtat", "https://www.apotekhjartat.se/sok/?q=lenzetto")
    ));

    private List<String> productKeywords = new ArrayList<>(List.of("lenzetto"));
    private List<String> skaneKeywords = new ArrayList<>(List.of("skåne", "malmö", "lund", "helsingborg", "kristianstad"));
    private List<String> availabilityKeywords = new ArrayList<>(List.of("i lager", "tillgänglig", "finns", "available"));
    private List<String> unavailabilityKeywords = new ArrayList<>(List.of("slut", "inte i lager", "ej tillgänglig", "out of stock"));

    public String getScheduleCron() {
        return scheduleCron;
    }

    public void setScheduleCron(String scheduleCron) {
        this.scheduleCron = scheduleCron;
    }

    public boolean isEmailEnabled() {
        return emailEnabled;
    }

    public void setEmailEnabled(boolean emailEnabled) {
        this.emailEnabled = emailEnabled;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public List<PharmacySource> getSources() {
        return sources;
    }

    public void setSources(List<PharmacySource> sources) {
        this.sources = sources;
    }

    public List<String> getProductKeywords() {
        return productKeywords;
    }

    public void setProductKeywords(List<String> productKeywords) {
        this.productKeywords = productKeywords;
    }

    public List<String> getSkaneKeywords() {
        return skaneKeywords;
    }

    public void setSkaneKeywords(List<String> skaneKeywords) {
        this.skaneKeywords = skaneKeywords;
    }

    public List<String> getAvailabilityKeywords() {
        return availabilityKeywords;
    }

    public void setAvailabilityKeywords(List<String> availabilityKeywords) {
        this.availabilityKeywords = availabilityKeywords;
    }

    public List<String> getUnavailabilityKeywords() {
        return unavailabilityKeywords;
    }

    public void setUnavailabilityKeywords(List<String> unavailabilityKeywords) {
        this.unavailabilityKeywords = unavailabilityKeywords;
    }
}
