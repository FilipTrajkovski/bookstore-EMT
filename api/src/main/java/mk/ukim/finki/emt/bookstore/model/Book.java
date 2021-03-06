package mk.ukim.finki.emt.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.bookstore.model.enums.Category;

import javax.persistence.*;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer numCopies;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}
