package finki.labfinal.model.dto;

import finki.labfinal.model.domain.Author;
import finki.labfinal.model.domain.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAuthorDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Surname is required")
        String surname,

        @NotNull(message = "Country ID is required")
        Long countryId
) {

    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
}
