package finki.labfinal.web.controller;

import finki.labfinal.model.dto.CreateBookDTO;
import finki.labfinal.model.dto.DisplayBookDTO;
import finki.labfinal.model.exception.ResourceNotFoundException;
import finki.labfinal.service.application.BookAppService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookAppService bookAppService;

    public BookController(BookAppService bookAppService) {
        this.bookAppService = bookAppService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDTO> getBookById(@PathVariable Long id) {
        return bookAppService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
    }

    @GetMapping
    public ResponseEntity<List<DisplayBookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookAppService.findAll());
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

    @DeleteMapping("/{id}")
    public ResponseEntity<DisplayBookDTO> deleteBook(@PathVariable Long id) {
        return bookAppService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
    }
}

