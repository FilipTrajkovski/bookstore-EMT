package mk.ukim.finki.emt.bookstore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Table
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;

    @JoinColumn(name = "author_id")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Book> books;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

}
