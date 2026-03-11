CREATE INDEX idx_authors_country_id ON authors (country_id);
CREATE INDEX idx_books_category ON books (category);
CREATE INDEX idx_books_state ON books (state);
CREATE INDEX idx_books_auths_book_id ON books_auths (book_id);
CREATE INDEX idx_books_auths_author_id ON books_auths (author_id);

CREATE UNIQUE INDEX uq_books_auths_book_author ON books_auths (book_id, author_id);

