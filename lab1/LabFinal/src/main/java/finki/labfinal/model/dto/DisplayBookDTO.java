package finki.labfinal.model.dto;

import finki.labfinal.model.domain.Book;
import finki.labfinal.model.enums.Category;
import finki.labfinal.model.enums.State;

import java.time.LocalDate;
import java.util.List;

public record DisplayBookDTO(
        Long id,
        String name,
        Category category,
        State state,
        Integer availableCopies,
        LocalDate datePublished
) {
    public static DisplayBookDTO from(Book book) {
        return new DisplayBookDTO(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getState(),
                book.getAvailableCopies(),
                book.getDatePublished()
        );
    }

    public static List<DisplayBookDTO> from(List<Book> books) {
        return books.stream().map(DisplayBookDTO::from).toList();
    }
}
