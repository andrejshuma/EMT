package finki.labfinal.repository;

import finki.labfinal.model.domain.CategoryStatsView;
import finki.labfinal.model.enums.Category;
import finki.labfinal.model.projection.CategoryStatsProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Read-only repository over materialized view mv_category_stats.
 */
public interface CategoryStatsViewRepository extends JpaRepository<CategoryStatsView, Category> {

    Page<CategoryStatsProjection> findAllProjectedBy(Pageable pageable);
}

