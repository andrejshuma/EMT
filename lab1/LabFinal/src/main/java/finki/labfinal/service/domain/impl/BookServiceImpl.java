package finki.labfinal.service.domain.impl;

import finki.labfinal.model.domain.Book;
import finki.labfinal.repository.BookRepository;
import finki.labfinal.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id)
                .map(b -> {
                    b.setName(book.getName());
                    b.setState(book.getState());
                    b.setAuthors(book.getAuthors());
                    b.setCategory(book.getCategory());
                    b.setAvailableCopies(book.getAvailableCopies());
                    return bookRepository.save(b);
                });
    }

    @Override
    public Optional<Book> deleteById(Long id) {
        Optional<Book> byId = findById(id);
        byId.ifPresent(bookRepository::delete);
        return byId;
    }
}
