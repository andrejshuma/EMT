package finki.labfinal.web.controller;

import finki.labfinal.model.dto.CreateAuthorDTO;
import finki.labfinal.model.dto.DisplayAuthorDTO;
import finki.labfinal.model.exception.ResourceNotFoundException;
import finki.labfinal.service.application.AuthorAppService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorAppService authorAppService;

    public AuthorController(AuthorAppService authorAppService) {
        this.authorAppService = authorAppService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDTO> getAuthorById(@PathVariable Long id) {
        return authorAppService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
    }

    @GetMapping
    public ResponseEntity<List<DisplayAuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorAppService.findAll());
    }

    @PostMapping
    public ResponseEntity<DisplayAuthorDTO> createAuthor(@Valid @RequestBody CreateAuthorDTO authorDTO) {
        DisplayAuthorDTO created = authorAppService.create(authorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisplayAuthorDTO> updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody CreateAuthorDTO authorDTO) {
        return authorAppService.update(id, authorDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DisplayAuthorDTO> deleteAuthor(@PathVariable Long id) {
        return authorAppService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
    }
}

