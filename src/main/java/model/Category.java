package model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories",schema = "safe")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Note> notes;

}
