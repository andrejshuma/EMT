package finki.labfinal.service.domain.impl;

import finki.labfinal.model.domain.Book;
import finki.labfinal.model.event.BookBecameUnavailableEvent;
import finki.labfinal.model.event.BookRentedEvent;
import finki.labfinal.model.exception.ResourceNotFoundException;
import finki.labfinal.repository.BookRepository;
import finki.labfinal.repository.spec.BookSpecifications;
import finki.labfinal.service.domain.BookService;
import finki.labfinal.web.dto.BookSearchRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByIdWithAuthorsAndCountry(Long id) {
        return bookRepository.findWithAuthorsAndCountryById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> search(BookSearchRequest request, Pageable pageable) {
        BookSearchRequest safe = request == null ? new BookSearchRequest(null, null, null, null) : request;

        Specification<Book> spec = Specification.where(BookSpecifications.withCategory(safe.category()))
                .and(BookSpecifications.withState(safe.state()))
                .and(BookSpecifications.withAuthorId(safe.authorId()))
                .and(BookSpecifications.withAvailability(safe.availableOnly()));

        return bookRepository.findAll(spec, pageable);
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
    public Optional<Book> rent(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));

        int current = book.getAvailableCopies() == null ? 0 : book.getAvailableCopies();
        if (current <= 0) {
            return Optional.empty();
        }

        book.setAvailableCopies(current - 1);
        Book saved = bookRepository.save(book);

        int remainingCopies = saved.getAvailableCopies() == null ? 0 : saved.getAvailableCopies();
        Instant occurredAt = Instant.now();

        applicationEventPublisher.publishEvent(new BookRentedEvent(
                saved.getId(),
                saved.getName(),
                remainingCopies,
                occurredAt
        ));

        if (remainingCopies == 0) {
            applicationEventPublisher.publishEvent(new BookBecameUnavailableEvent(
                    saved.getId(),
                    saved.getName(),
                    occurredAt
            ));
        }

        return Optional.of(saved);
    }

    @Override
    public Optional<Book> deleteById(Long id) {
        Optional<Book> byId = findById(id);
        byId.ifPresent(bookRepository::delete);
        return byId;
    }
}
