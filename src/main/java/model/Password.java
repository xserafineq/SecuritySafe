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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_modified(LocalDate last_modified) {
        this.last_modified = last_modified;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSafe(Safe safe) {
        this.safe = safe;
    }
}
