package finki.labfinal.service.domain.impl;

import finki.labfinal.model.domain.Country;
import finki.labfinal.repository.CountryRepository;
import finki.labfinal.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country create(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countryRepository.findById(id)
                .map(c -> {
                    c.setName(country.getName());
                    c.setContinent(country.getContinent());
                    return countryRepository.save(c);
                });
    }

    @Override
    public Optional<Country> deleteById(Long id) {
        Optional<Country> byId = countryRepository.findById(id);
        byId.ifPresent(countryRepository::delete);
        return byId;
    }
}

