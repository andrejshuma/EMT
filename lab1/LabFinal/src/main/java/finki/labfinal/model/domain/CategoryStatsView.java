package finki.labfinal.model.domain;

import finki.labfinal.model.enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * Read-only entity mapped to the materialized view mv_category_stats.
 */
@Entity
@Table(name = "mv_category_stats")
@Getter
public class CategoryStatsView {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "total_books")
    private Long totalBooks;

    @Column(name = "total_available_copies")
    private Long totalAvailableCopies;

    @Column(name = "not_good_state_books")
    private Long notGoodStateBooks;

    protected CategoryStatsView() {
    }
}

