package finki.labfinal.model.event;

import java.time.Instant;

/**
 * Published when a book is successfully rented (available copies decreased).
 */
public record BookRentedEvent(
        Long bookId,
        String bookName,
        int remainingCopies,
        Instant occurredAt
) {
}

