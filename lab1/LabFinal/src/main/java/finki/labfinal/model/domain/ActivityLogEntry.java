package finki.labfinal.model.domain;

import finki.labfinal.model.enums.ActivityEventType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.Instant;

@Entity
@Table(name = "activity_log")
@Getter
public class ActivityLogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "occurred_at", nullable = false)
    private Instant occurredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false, length = 64)
    private ActivityEventType eventType;

    protected ActivityLogEntry() {
    }

    public ActivityLogEntry(Long bookId, String bookName, Instant occurredAt, ActivityEventType eventType) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.occurredAt = occurredAt;
        this.eventType = eventType;
    }
}

