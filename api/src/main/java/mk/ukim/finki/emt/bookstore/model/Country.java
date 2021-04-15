package mk.ukim.finki.emt.bookstore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Table
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String continent;

    @JoinColumn(name = "country_id")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Author> authors;

}
