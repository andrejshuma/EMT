package finki.labfinal.web.controller;

import finki.labfinal.model.dto.CreateCountryDTO;
import finki.labfinal.model.dto.DisplayCountryDTO;
import finki.labfinal.model.exception.ResourceNotFoundException;
import finki.labfinal.service.application.CountryAppService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryAppService countryAppService;

    public CountryController(CountryAppService countryAppService) {
        this.countryAppService = countryAppService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDTO> getCountryById(@PathVariable Long id) {
        return countryAppService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Country with id " + id + " not found"));
    }

    @GetMapping
    public ResponseEntity<List<DisplayCountryDTO>> getAllCountries() {
        return ResponseEntity.ok(countryAppService.findAll());
    }

    @PostMapping
    public ResponseEntity<DisplayCountryDTO> createCountry(@Valid @RequestBody CreateCountryDTO countryDTO) {
        DisplayCountryDTO created = countryAppService.create(countryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisplayCountryDTO> updateCountry(
            @PathVariable Long id,
            @Valid @RequestBody CreateCountryDTO countryDTO) {
        return countryAppService.update(id, countryDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Country with id " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DisplayCountryDTO> deleteCountry(@PathVariable Long id) {
        return countryAppService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Country with id " + id + " not found"));
    }
}

