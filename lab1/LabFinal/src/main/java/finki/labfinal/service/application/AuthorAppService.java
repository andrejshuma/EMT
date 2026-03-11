package finki.labfinal.service.application;

import finki.labfinal.model.dto.CreateAuthorDTO;
import finki.labfinal.model.dto.DisplayAuthorDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorAppService {

    Optional<DisplayAuthorDTO> findById(Long id);

    List<DisplayAuthorDTO> findAll();

    DisplayAuthorDTO create(CreateAuthorDTO author);

    Optional<DisplayAuthorDTO> update(Long id, CreateAuthorDTO author);

    Optional<DisplayAuthorDTO> deleteById(Long id);

}
