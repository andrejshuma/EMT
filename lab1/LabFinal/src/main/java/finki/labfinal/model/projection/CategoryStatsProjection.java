package finki.labfinal.model.projection;

import finki.labfinal.model.enums.Category;

public interface CategoryStatsProjection {

    Category getCategory();

    Long getTotalBooks();

    Long getTotalAvailableCopies();

    Long getNotGoodStateBooks();
}

