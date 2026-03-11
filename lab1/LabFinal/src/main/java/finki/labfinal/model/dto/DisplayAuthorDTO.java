package finki.labfinal.model.dto;

import finki.labfinal.model.domain.Author;

import java.util.List;

public record DisplayAuthorDTO(
        Long id,
        String name,
        String surname,
        String country
) {

    public static DisplayAuthorDTO from(Author author) {
        return new DisplayAuthorDTO(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getCountry() != null ? author.getCountry().getName() : null
        );
    }

    public static List<DisplayAuthorDTO> from(List<Author> authors) {
        return authors.stream().map(DisplayAuthorDTO::from).toList();
    }

}
