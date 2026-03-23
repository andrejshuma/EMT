package finki.labfinal.model.projection;

import finki.labfinal.model.enums.Category;
import finki.labfinal.model.enums.State;

/**
 * Short view for Book data.
 */
public interface BookShortProjection {

    Long getId();

    String getName();

    Category getCategory();

    State getState();

    Integer getAvailableCopies();
}

