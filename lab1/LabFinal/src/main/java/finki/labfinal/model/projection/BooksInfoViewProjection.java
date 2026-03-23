package finki.labfinal.model.projection;

import finki.labfinal.model.enums.Category;
import finki.labfinal.model.enums.State;

/**
 * Projection over the database view vw_books_info.
 */
public interface BooksInfoViewProjection {

    Long getBookId();

    String getBookName();

    Category getCategory();

    State getState();

    Integer getAvailableCopies();

    String getAuthorFullName();

    String getCountryName();
}

