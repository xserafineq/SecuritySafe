package model;

import jakarta.persistence.*;
import model.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "safes", schema = "safe")
public class Safe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "safe_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "safe", cascade = CascadeType.ALL)
    private List<Note> notes;

    @OneToMany(mappedBy = "safe", cascade = CascadeType.ALL)
    private List<Password> passwords;

    @OneToMany(mappedBy = "safe", cascade = CascadeType.ALL)
    private List<HyperLink> hyperlinks;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
