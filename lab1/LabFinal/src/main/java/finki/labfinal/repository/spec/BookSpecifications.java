package finki.labfinal.repository.spec;

import finki.labfinal.model.domain.Book;
import finki.labfinal.model.enums.Category;
import finki.labfinal.model.enums.State;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

/**
 * Helper Specifications for dynamic filtering of {@link Book}s.
 */
public final class BookSpecifications {

    private BookSpecifications() {
    }

    public static Specification<Book> withCategory(Category category) {
        return (root, query, cb) -> category == null ? null : cb.equal(root.get("category"), category);
    }

    public static Specification<Book> withState(State state) {
        return (root, query, cb) -> state == null ? null : cb.equal(root.get("state"), state);
    }

    /**
     * Filters books that have an author with the provided id.
     */
    public static Specification<Book> withAuthorId(Long authorId) {
        return (root, query, cb) -> {
            if (authorId == null) {
                return null;
            }
            // avoid duplicates when joining
            query.distinct(true);
            Join<Object, Object> bookAuthJoin = root.join("authors", JoinType.INNER);
            Join<Object, Object> authorJoin = bookAuthJoin.join("author", JoinType.INNER);
            return cb.equal(authorJoin.get("id"), authorId);
        };
    }

    /**
     * If availableOnly == true: availableCopies > 0
     * If availableOnly == false: availableCopies == 0
     */
    public static Specification<Book> withAvailability(Boolean availableOnly) {
        return (root, query, cb) -> {
            if (availableOnly == null) {
                return null;
            }
            if (Boolean.TRUE.equals(availableOnly)) {
                return cb.greaterThan(root.get("availableCopies"), 0);
            }
            return cb.equal(root.get("availableCopies"), 0);
        };
    }
}

