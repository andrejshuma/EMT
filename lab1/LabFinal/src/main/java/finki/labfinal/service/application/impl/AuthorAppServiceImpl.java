package finki.labfinal.service.application.impl;

import finki.labfinal.model.domain.Country;
import finki.labfinal.model.dto.CreateAuthorDTO;
import finki.labfinal.model.dto.DisplayAuthorDTO;
import finki.labfinal.model.exception.ResourceNotFoundException;
import finki.labfinal.service.application.AuthorAppService;
import finki.labfinal.service.domain.AuthorService;
import finki.labfinal.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorAppServiceImpl implements AuthorAppService {

    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorAppServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayAuthorDTO> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDTO::from);
    }

    @Override
    public List<DisplayAuthorDTO> findAll() {
        return authorService.findAll()
                .stream()
                .map(DisplayAuthorDTO::from)
                .toList();
    }

    @Override
    public DisplayAuthorDTO create(CreateAuthorDTO authordto) {

        Country country = countryService.findById(authordto.countryId()).orElseThrow(
                () -> new ResourceNotFoundException("Country not found")
        );

        return DisplayAuthorDTO.from(authorService.create(authordto.toAuthor(country)));
    }

    @Override
    public Optional<DisplayAuthorDTO> update(Long id, CreateAuthorDTO author) {

        Country country = countryService.findById(author.countryId()).orElseThrow(
                () -> new ResourceNotFoundException("Country not found")
        );

        return authorService.update(id, author.toAuthor(country)).map(DisplayAuthorDTO::from);
    }

    @Override
    public Optional<DisplayAuthorDTO> deleteById(Long id) {
        return authorService.deleteById(id).map(DisplayAuthorDTO::from);
    }
}
