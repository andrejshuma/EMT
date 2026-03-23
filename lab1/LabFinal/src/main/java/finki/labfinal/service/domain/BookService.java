package finki.labfinal.service.domain;

import finki.labfinal.model.domain.Book;
import finki.labfinal.web.dto.BookSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findById(Long id);

    Optional<Book> findByIdWithAuthorsAndCountry(Long id);

    List<Book> findAll();

    Page<Book> search(BookSearchRequest request, Pageable pageable);

    Book create(Book book);

    Optional<Book> update(Long id, Book book);

    /**
     * Rent a book (decrease available copies by 1).
     *
     * @return updated book
     */
    Optional<Book> rent(Long id);

    Optional<Book> deleteById(Long id);
}
