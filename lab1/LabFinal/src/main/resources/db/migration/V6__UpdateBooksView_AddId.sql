-- Update vw_books_info to include a synthetic unique id column so it can be mapped as a JPA entity.
-- Flyway best practice: never modify an already-applied migration (create a new one instead).

DROP VIEW IF EXISTS vw_books_info;

CREATE OR REPLACE VIEW vw_books_info AS
SELECT
    (b.id::text || ':' || a.id::text) AS id,
    b.id               AS book_id,
    b.name             AS book_name,
    b.category         AS category,
    b.state            AS state,
    b.available_copies AS available_copies,
    (a.name || ' ' || a.surname) AS author_full_name,
    c.name             AS country_name
FROM books b
JOIN books_auths ba ON ba.book_id = b.id
JOIN authors a ON a.id = ba.author_id
JOIN countries c ON c.id = a.country_id;


