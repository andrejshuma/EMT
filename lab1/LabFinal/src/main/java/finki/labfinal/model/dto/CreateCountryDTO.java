package finki.labfinal.model.dto;

import finki.labfinal.model.domain.Country;
import jakarta.validation.constraints.NotBlank;

public record CreateCountryDTO(
        @NotBlank(message = "Country name is required")
        String name,

        @NotBlank(message = "Continent is required")
        String continent
) {
    public Country toCountry() {
        return new Country(name, continent);
    }
}

