package finki.labfinal.service.domain.impl;

import finki.labfinal.model.domain.Author;
import finki.labfinal.repository.AuthorRepository;
import finki.labfinal.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> update(Long id, Author author) {
        return authorRepository.findById(id)
                .map(a -> {
                    a.setName(author.getName());
                    a.setSurname(author.getSurname());
                    a.setCountry(author.getCountry());
                    return authorRepository.save(a);
                });
    }

    @Override
    public Optional<Author> deleteById(Long id) {
        Optional<Author> byId = authorRepository.findById(id);
        byId.ifPresent(authorRepository::delete);
        return byId;
    }
}
