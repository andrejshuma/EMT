package finki.labfinal.model.domain;

import finki.labfinal.model.enums.Category;
import finki.labfinal.model.enums.State;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * Read-only entity mapped to the database view vw_books_info.
 *
 * Primary key is a synthetic key made from book_id + author_full_name to keep rows unique
 * (since a book can have multiple authors).
 */
@Entity
@Table(name = "vw_books_info")
@Getter
public class BooksInfoView {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_name")
    private String bookName;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @Column(name = "available_copies")
    private Integer availableCopies;

    @Column(name = "author_full_name")
    private String authorFullName;

    @Column(name = "country_name")
    private String countryName;

    protected BooksInfoView() {
    }
}

