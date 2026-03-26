package finki.labfinal.model.domain;


import finki.labfinal.model.enums.Category;
import finki.labfinal.model.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book extends BaseAuditableEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "book")
    private List<BookAuth> authors = new ArrayList<>();

    @Column(nullable = false)
    private Integer availableCopies;

    @Column(name = "date_published", columnDefinition = "date")
    private LocalDate datePublished;

    public Book(String name, Category category, State state, Integer availableCopies, LocalDate datePublished) {
        this.name = name;
        this.category = category;
        this.state = state;
        this.availableCopies = availableCopies;
        this.datePublished = datePublished;
    }

}
