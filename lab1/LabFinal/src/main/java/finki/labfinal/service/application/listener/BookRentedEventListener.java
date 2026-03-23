package finki.labfinal.service.application.listener;

import finki.labfinal.model.domain.BookRentAudit;
import finki.labfinal.model.event.BookRentedEvent;
import finki.labfinal.repository.BookRentAuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookRentedEventListener {

    private static final Logger log = LoggerFactory.getLogger(BookRentedEventListener.class);

    private final BookRentAuditRepository bookRentAuditRepository;

    public BookRentedEventListener(BookRentAuditRepository bookRentAuditRepository) {
        this.bookRentAuditRepository = bookRentAuditRepository;
    }

    @EventListener
    public void onBookRented(BookRentedEvent event) {
        String message;
        if (event.remainingCopies() == 0) {
            message = "Book rented: '" + event.bookName() + "' (id=" + event.bookId() + ") - NO copies left";
            log.warn(message);
        } else {
            message = "Book rented: '" + event.bookName() + "' (id=" + event.bookId() + "), remainingCopies=" + event.remainingCopies();
            log.info(message);
        }

        bookRentAuditRepository.save(new BookRentAudit(
                event.bookId(),
                event.bookName(),
                event.remainingCopies(),
                event.occurredAt(),
                message
        ));
    }
}

