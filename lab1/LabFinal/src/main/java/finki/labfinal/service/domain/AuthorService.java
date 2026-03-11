package finki.labfinal.service.domain;

import finki.labfinal.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);

    List<Author> findAll();

    Author create(Author author);

    Optional<Author> update(Long id, Author author);

    Optional<Author> deleteById(Long id);

}
