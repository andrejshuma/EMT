-- Audit table for when a book becomes unavailable (available copies reach 0).

CREATE TABLE IF NOT EXISTS book_unavailable_audit (
    id BIGSERIAL PRIMARY KEY,
    book_id BIGINT NOT NULL,
    book_name VARCHAR(255) NOT NULL,
    occurred_at TIMESTAMP NOT NULL,
    message VARCHAR(1024) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_book_unavailable_audit_book_id
    ON book_unavailable_audit (book_id);

