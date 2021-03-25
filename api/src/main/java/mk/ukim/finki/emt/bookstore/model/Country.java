package mk.ukim.finki.emt.bookstore.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @Fetch(FetchMode.JOIN)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="country_id")
    private Set<Author> authors;

}
