package finki.labfinal.model.dto;

import finki.labfinal.model.domain.Country;

public record DisplayCountryDTO(
        Long id,
        String name,
        String continent
) {
    public static DisplayCountryDTO from(Country country) {
        return new DisplayCountryDTO(
                country.getId(),
                country.getName(),
                country.getContinent()
        );
    }
}

