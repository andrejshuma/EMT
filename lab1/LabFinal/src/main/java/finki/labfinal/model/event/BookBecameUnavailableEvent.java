package finki.labfinal.model.event;

import java.time.Instant;

/**
 * Published when a book becomes unavailable (available copies reached 0).
 */
public record BookBecameUnavailableEvent(
        Long bookId,
        String bookName,
        Instant occurredAt
) {
}

