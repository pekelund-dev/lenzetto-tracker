package dev.pekelund.lenzetto;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledAvailabilityChecker {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledAvailabilityChecker.class);

    private final AvailabilityScanner scanner;
    private final AvailabilityNotificationService notificationService;

    public ScheduledAvailabilityChecker(AvailabilityScanner scanner, AvailabilityNotificationService notificationService) {
        this.scanner = scanner;
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "${lenzetto.tracker.schedule-cron:0 0 */2 * * *}")
    public void checkAndNotify() {
        List<AvailabilityCheckResult> results = scanner.scanAll();
        notificationService.notifyResults(results);
        long availableCount = results.stream().filter(AvailabilityCheckResult::availableInSkane).count();
        LOGGER.info("Checked {} sources, {} indicate availability in Skåne", results.size(), availableCount);
    }
}
