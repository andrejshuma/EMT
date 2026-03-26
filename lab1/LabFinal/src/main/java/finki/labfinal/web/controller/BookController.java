package finki.labfinal.web.controller;

import finki.labfinal.model.dto.CreateBookDTO;
import finki.labfinal.model.dto.DisplayBookDTO;
import finki.labfinal.model.enums.Category;
import finki.labfinal.model.enums.State;
import finki.labfinal.model.exception.ResourceNotFoundException;
import finki.labfinal.model.projection.BookExtendedProjection;
import finki.labfinal.model.projection.BookShortProjection;
import finki.labfinal.repository.BookRepository;
import finki.labfinal.service.application.BookAppService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import finki.labfinal.web.dto.BookSearchRequest;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookAppService bookAppService;
    private final BookRepository bookRepository;

    public BookController(BookAppService bookAppService, BookRepository bookRepository) {
        this.bookAppService = bookAppService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDTO> getBookById(@PathVariable Long id) {
        return bookAppService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
    }


    @GetMapping("/{id}/with-authors")
    public ResponseEntity<DisplayBookDTO> getBookByIdWithAuthors(@PathVariable Long id) {
        return bookAppService.findByIdWithAuthorsAndCountry(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
    }

    @GetMapping
    public ResponseEntity<List<DisplayBookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookAppService.findAll());
    }

    @GetMapping("/projections/short")
    public ResponseEntity<Page<BookShortProjection>> listShortProjection(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.max(size, 1));
        return ResponseEntity.ok(bookRepository.findAllProjectedBy(pageable));
    }

    @GetMapping("/projections/extended")
    public ResponseEntity<Page<BookExtendedProjection>> listExtendedProjection(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long authorId
    ) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.max(size, 1));
        return ResponseEntity.ok(bookRepository.findExtended(authorId, pageable));
    }


    @GetMapping("/search")
    public ResponseEntity<Page<DisplayBookDTO>> search(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) State state,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Boolean availableOnly
    ) {
        String safeSortBy = switch (sortBy) {
            case "createdAt" -> "createdAt";
            case "name" -> "name";
            default -> "name";
        };

        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(
                Math.max(page, 0),
                Math.max(size, 1),
                Sort.by(direction, safeSortBy)
        );

        BookSearchRequest request = new BookSearchRequest(category, state, authorId, availableOnly);
        return ResponseEntity.ok(bookAppService.search(request, pageable));
    }

    @GetMapping("/findallbydatepublished")
    public ResponseEntity<List<DisplayBookDTO>> findAllByDatePublished(
            @RequestParam("before") LocalDate before
    ) {
        return ResponseEntity.ok(bookAppService.findTop10PublishedBefore(before));
    }

    @PostMapping
    public ResponseEntity<DisplayBookDTO> createBook(@Valid @RequestBody CreateBookDTO bookDTO) {
        DisplayBookDTO created = bookAppService.create(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisplayBookDTO> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody CreateBookDTO bookDTO) {
        return bookAppService.update(id, bookDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
    }


    @PostMapping("/{id}/rent")
    public ResponseEntity<DisplayBookDTO> rentBook(@PathVariable Long id) {
        return bookAppService.rent(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DisplayBookDTO> deleteBook(@PathVariable Long id) {
        return bookAppService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
    }
}

