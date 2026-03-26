ALTER TABLE books
    ADD COLUMN IF NOT EXISTS date_published DATE;

UPDATE books
SET date_published = COALESCE(date_published, DATE '2000-01-01' + ((id - 1) * INTERVAL '30 day'));

ALTER TABLE books
    ALTER COLUMN date_published SET NOT NULL;

