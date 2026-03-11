package finki.labfinal.service.application.impl;

import finki.labfinal.model.dto.CreateCountryDTO;
import finki.labfinal.model.dto.DisplayCountryDTO;
import finki.labfinal.service.application.CountryAppService;
import finki.labfinal.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryAppServiceImpl implements CountryAppService {

    private final CountryService countryService;

    public CountryAppServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayCountryDTO> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDTO::from);
    }

    @Override
    public List<DisplayCountryDTO> findAll() {
        return countryService.findAll()
                .stream()
                .map(DisplayCountryDTO::from)
                .toList();
    }

    @Override
    public DisplayCountryDTO create(CreateCountryDTO countrydto) {
        return DisplayCountryDTO.from(countryService.create(countrydto.toCountry()));
    }

    @Override
    public Optional<DisplayCountryDTO> update(Long id, CreateCountryDTO country) {
        return countryService.update(id, country.toCountry()).map(DisplayCountryDTO::from);
    }

    @Override
    public Optional<DisplayCountryDTO> deleteById(Long id) {
        return countryService.deleteById(id).map(DisplayCountryDTO::from);
    }
}

