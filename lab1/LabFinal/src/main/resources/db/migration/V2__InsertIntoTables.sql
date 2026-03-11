INSERT INTO countries (name, continent)
VALUES
    ('North Macedonia', 'Europe'),
    ('Serbia', 'Europe'),
    ('Greece', 'Europe'),
    ('Germany', 'Europe'),
    ('United States', 'North America'),
    ('Canada', 'North America'),
    ('Japan', 'Asia'),
    ('South Korea', 'Asia'),
    ('Brazil', 'South America'),
    ('Australia', 'Oceania');

INSERT INTO authors (name, surname, country_id, created_at, updated_at)
VALUES
    ('Blaze', 'Koneski', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Petre', 'M. Andreevski', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Mesa', 'Selimovic', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Nikos', 'Kazantzakis', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Johann', 'Goethe', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Mark', 'Twain', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Ernest', 'Hemingway', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Margaret', 'Atwood', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Haruki', 'Murakami', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Han', 'Kang', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Paulo', 'Coelho', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Tim', 'Winton', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO books (name, category, state, available_copies, created_at, updated_at)
VALUES
    ('The Stone Bridge', 'NOVEL', 'GOOD', 12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Letters from the Lake', 'CLASSICS', 'GOOD', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('The Silent City', 'DRAMA', 'BAD', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Odyssey of the South', 'HISTORY', 'GOOD', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Learning Spring Boot', 'FANTASY', 'GOOD', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Migrations & Databases', 'THRILER', 'GOOD', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('The River Between', 'NOVEL', 'BAD', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Northern Lights', 'CLASSICS', 'GOOD', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Tokyo Stories', 'NOVEL', 'GOOD', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Seoul Nights', 'DRAMA', 'GOOD', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('The Alchemist (Dummy Edition)', 'NOVEL', 'GOOD', 11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Coastal Winds', 'HISTORY', 'BAD', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO books_auths (book_id, author_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 6),
    (5, 7),
    (6, 7),
    (7, 8),
    (8, 8),
    (9, 9),
    (10, 10),
    (11, 11),
    (12, 12);

