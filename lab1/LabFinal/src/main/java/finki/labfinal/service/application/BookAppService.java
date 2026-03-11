package finki.labfinal.service.application;

import finki.labfinal.model.dto.CreateBookDTO;
import finki.labfinal.model.dto.DisplayBookDTO;

import java.util.List;
import java.util.Optional;

public interface BookAppService {

    Optional<DisplayBookDTO> findById(Long id);

    List<DisplayBookDTO> findAll();

    DisplayBookDTO create(CreateBookDTO book);

    Optional<DisplayBookDTO> update(Long id, CreateBookDTO book);

    Optional<DisplayBookDTO> deleteById(Long id);
}
