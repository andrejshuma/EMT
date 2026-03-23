-- Simple activity log table (populated via event listeners).

CREATE TABLE IF NOT EXISTS activity_log (
    id BIGSERIAL PRIMARY KEY,
    book_id BIGINT,
    book_name VARCHAR(255) NOT NULL,
    occurred_at TIMESTAMP NOT NULL,
    event_type VARCHAR(64) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_activity_log_occurred_at
    ON activity_log (occurred_at);

CREATE INDEX IF NOT EXISTS idx_activity_log_event_type
    ON activity_log (event_type);

