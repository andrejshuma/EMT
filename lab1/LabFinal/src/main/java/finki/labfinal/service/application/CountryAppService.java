package finki.labfinal.service.application;

import finki.labfinal.model.dto.CreateCountryDTO;
import finki.labfinal.model.dto.DisplayCountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryAppService {

    Optional<DisplayCountryDTO> findById(Long id);

    List<DisplayCountryDTO> findAll();

    DisplayCountryDTO create(CreateCountryDTO country);

    Optional<DisplayCountryDTO> update(Long id, CreateCountryDTO country);

    Optional<DisplayCountryDTO> deleteById(Long id);

}

