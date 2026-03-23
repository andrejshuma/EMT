package finki.labfinal.web.controller;

import finki.labfinal.model.projection.BooksInfoViewProjection;
import finki.labfinal.repository.BooksInfoViewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books-view")
public class BooksInfoViewController {

    private final BooksInfoViewRepository booksInfoViewRepository;

    public BooksInfoViewController(BooksInfoViewRepository booksInfoViewRepository) {
        this.booksInfoViewRepository = booksInfoViewRepository;
    }

    @GetMapping
    public ResponseEntity<Page<BooksInfoViewProjection>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.max(size, 1));
        return ResponseEntity.ok(booksInfoViewRepository.findAllProjectedBy(pageable));
    }
}


