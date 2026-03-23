package finki.labfinal.web.dto;

import finki.labfinal.model.enums.Category;
import finki.labfinal.model.enums.State;

/**
 * Optional filters for searching books.
 *
 * Note: Author filter is by authorId (via books_auths join table).
 */
public record BookSearchRequest(
        Category category,
        State state,
        Long authorId,
        Boolean availableOnly
) {
}

