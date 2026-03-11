package finki.labfinal.model.domain;


import finki.labfinal.model.enums.Category;
import finki.labfinal.model.enums.State;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public Book(String name, Category category, State state, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.state = state;
        this.availableCopies = availableCopies;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public State getState() {
        return state;
    }

    public List<BookAuth> getAuthors() {
        return authors;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setAuthors(List<BookAuth> authors) {
        this.authors = authors;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
