package finki.labfinal.service.application.listener;

import finki.labfinal.model.domain.BookUnavailableAudit;
import finki.labfinal.model.event.BookBecameUnavailableEvent;
import finki.labfinal.repository.BookUnavailableAuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookBecameUnavailableEventListener {

    private static final Logger log = LoggerFactory.getLogger(BookBecameUnavailableEventListener.class);

    private final BookUnavailableAuditRepository bookUnavailableAuditRepository;

    public BookBecameUnavailableEventListener(BookUnavailableAuditRepository bookUnavailableAuditRepository) {
        this.bookUnavailableAuditRepository = bookUnavailableAuditRepository;
    }

    @EventListener
    public void onBookBecameUnavailable(BookBecameUnavailableEvent event) {
        String message = "Book became unavailable: '" + event.bookName() + "' (id=" + event.bookId() + ")";

        log.warn(message);
        bookUnavailableAuditRepository.save(new BookUnavailableAudit(
                event.bookId(),
                event.bookName(),
                event.occurredAt(),
                message
        ));
    }
}

