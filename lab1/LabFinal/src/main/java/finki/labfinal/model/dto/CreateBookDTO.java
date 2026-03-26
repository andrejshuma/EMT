package finki.labfinal.model.dto;

import finki.labfinal.model.domain.Book;
import finki.labfinal.model.enums.Category;
import finki.labfinal.model.enums.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
public record CreateBookDTO(
        @NotBlank(message = "Book name is required")
        String name,

        @NotNull(message = "Category is required")
        Category category,

        @NotNull(message = "State is required")
        State state,

        @NotNull(message = "Available copies is required")
        @Positive(message = "Available copies must be positive")
        Integer availableCopies,

        LocalDate datePublished

) {
        public Book toBook() {
                return new Book(name,category,state,availableCopies,datePublished);
        }
}
