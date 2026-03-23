package finki.labfinal.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.Instant;

@Entity
@Table(name = "book_unavailable_audit")
@Getter
public class BookUnavailableAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "occurred_at", nullable = false)
    private Instant occurredAt;

    @Column(name = "message", nullable = false, length = 1024)
    private String message;

    protected BookUnavailableAudit() {
    }

    public BookUnavailableAudit(Long bookId, String bookName, Instant occurredAt, String message) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.occurredAt = occurredAt;
        this.message = message;
    }
}

