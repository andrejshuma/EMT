package finki.labfinal.repository;

import finki.labfinal.model.domain.BooksInfoView;
import finki.labfinal.model.projection.BooksInfoViewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Read-only repository over the database view vw_books_info.
 */
public interface BooksInfoViewRepository extends JpaRepository<BooksInfoView, String> {

    Page<BooksInfoViewProjection> findAllProjectedBy(Pageable pageable);
}


