-- Audit table for book rent activities.

CREATE TABLE IF NOT EXISTS book_rent_audit (
    id BIGSERIAL PRIMARY KEY,
    book_id BIGINT NOT NULL,
    book_name VARCHAR(255) NOT NULL,
    remaining_copies INTEGER NOT NULL,
    occurred_at TIMESTAMP NOT NULL,
    message VARCHAR(1024) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_book_rent_audit_book_id
    ON book_rent_audit (book_id);

