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

    Optional<Book> deleteById(Long id);
}
