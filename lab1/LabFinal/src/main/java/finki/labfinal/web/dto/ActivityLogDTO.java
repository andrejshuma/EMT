package finki.labfinal.web.dto;

import finki.labfinal.model.domain.ActivityLogEntry;
import finki.labfinal.model.enums.ActivityEventType;

import java.time.Instant;

public record ActivityLogDTO(
        Long id,
        String bookName,
        Instant occurredAt,
        ActivityEventType eventType
) {
    public static ActivityLogDTO from(ActivityLogEntry entry) {
        return new ActivityLogDTO(
                entry.getId(),
                entry.getBookName(),
                entry.getOccurredAt(),
                entry.getEventType()
        );
    }
}

