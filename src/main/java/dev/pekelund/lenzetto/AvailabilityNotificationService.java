package dev.pekelund.lenzetto;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityNotificationService {

    private final JavaMailSender mailSender;
    private final TrackerProperties properties;

    public AvailabilityNotificationService(JavaMailSender mailSender, TrackerProperties properties) {
        this.mailSender = mailSender;
        this.properties = properties;
    }

    public void notifyResults(List<AvailabilityCheckResult> results) {
        if (!properties.isEmailEnabled()) {
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(properties.getRecipient());
        message.setSubject("Lenzetto availability check " + ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        message.setText(buildBody(results));

        mailSender.send(message);
    }

    private String buildBody(List<AvailabilityCheckResult> results) {
        StringBuilder body = new StringBuilder();
        body.append("Lenzetto availability check for Skåne\n\n");

        for (AvailabilityCheckResult result : results) {
            body.append("Source: ").append(result.sourceName()).append('\n');
            body.append("URL: ").append(result.sourceUrl()).append('\n');
            body.append("Available in Skåne: ").append(result.availableInSkane()).append('\n');

            if (result.error() != null) {
                body.append("Error: ").append(result.error()).append('\n');
            } else {
                body.append("Snippet: ").append(result.snippet()).append('\n');
            }

            body.append("\n");
        }

        return body.toString();
    }
}
