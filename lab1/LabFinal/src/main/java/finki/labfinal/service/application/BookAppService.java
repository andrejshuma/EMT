package finki.labfinal.service.application;

import finki.labfinal.model.dto.CreateBookDTO;
import finki.labfinal.model.dto.DisplayBookDTO;
import finki.labfinal.web.dto.BookSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookAppService {

    Optional<DisplayBookDTO> findById(Long id);

    Optional<DisplayBookDTO> findByIdWithAuthorsAndCountry(Long id);

    List<DisplayBookDTO> findAll();

    Page<DisplayBookDTO> search(BookSearchRequest request, Pageable pageable);

    DisplayBookDTO create(CreateBookDTO book);

    Optional<DisplayBookDTO> update(Long id, CreateBookDTO book);

    Optional<DisplayBookDTO> deleteById(Long id);
}
