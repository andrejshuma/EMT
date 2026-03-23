package finki.labfinal.repository;

import finki.labfinal.model.domain.Book;
import finki.labfinal.model.projection.BookExtendedProjection;
import finki.labfinal.model.projection.BookShortProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

	Page<BookShortProjection> findAllProjectedBy(Pageable pageable);

	/**
	 * Fetch Book together with BookAuth->Author->Country to avoid N+1 queries.
	 */
	@EntityGraph(attributePaths = {"authors", "authors.author", "authors.author.country"})
	Optional<Book> findWithAuthorsAndCountryById(Long id);

	@Query("""
			select b.id as id,
				   b.name as name,
				   b.category as category,
				   b.state as state,
				   b.availableCopies as availableCopies,
				   concat(a.name, ' ', a.surname) as authorFullName,
				   c.name as authorCountry
			from Book b
			  join b.authors ba
			  join ba.author a
			  join a.country c
			where (:authorId is null or a.id = :authorId)
			""")
	Page<BookExtendedProjection> findExtended(@Param("authorId") Long authorId, Pageable pageable);
}
