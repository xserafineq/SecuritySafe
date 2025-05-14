package model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "passwords",schema = "safe")
public class Password extends SafeItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_id")
    private int id;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "last_modified")
    private LocalDate last_modified;
    @Column(name = "login")
    private String login;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "safe_id")
    private Safe safe;


    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }
}
