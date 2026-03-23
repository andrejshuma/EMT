package finki.labfinal.web.controller;

import finki.labfinal.model.projection.CategoryStatsProjection;
import finki.labfinal.repository.CategoryStatsViewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category-stats")
public class CategoryStatsViewController {

    private final CategoryStatsViewRepository categoryStatsViewRepository;

    public CategoryStatsViewController(CategoryStatsViewRepository categoryStatsViewRepository) {
        this.categoryStatsViewRepository = categoryStatsViewRepository;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryStatsProjection>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(
                Math.max(page, 0),
                Math.max(size, 1),
                Sort.by(Sort.Direction.ASC, "category")
        );
        return ResponseEntity.ok(categoryStatsViewRepository.findAllProjectedBy(pageable));
    }
}

