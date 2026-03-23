-- Database view for simplified book + author + country information
-- Note: because a book can have multiple authors, the view will contain
-- one row per (book, author) pair.

CREATE OR REPLACE VIEW vw_books_info AS
SELECT
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



