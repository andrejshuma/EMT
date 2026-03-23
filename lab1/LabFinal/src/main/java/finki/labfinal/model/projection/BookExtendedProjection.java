package finki.labfinal.model.projection;

import finki.labfinal.model.enums.Category;
import finki.labfinal.model.enums.State;

/**
 * Extended view for Book data including author and country details.
 */
public interface BookExtendedProjection {

    Long getId();

    String getName();

    Category getCategory();

    State getState();

    Integer getAvailableCopies();

    String getAuthorFullName();

    String getAuthorCountry();
}

