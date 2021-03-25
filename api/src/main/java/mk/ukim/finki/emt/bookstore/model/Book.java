package mk.ukim.finki.emt.bookstore.model;

import lombok.Data;
import mk.ukim.finki.emt.bookstore.model.enums.Category;

import javax.persistence.*;

@Data
@Table
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id", nullable=false)
    private Author author;
}
