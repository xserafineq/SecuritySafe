package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "uzytkownicy",schema = "safe")
public class User {

    @Id
    @Column(name = "id_uzytkownik")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "haslo")
    private String password;

    @Column(name = "data_dolaczenia")
    private String joinedDate;

    @Override
    public String toString() {
        return id + "\t" + email + "\t" + password + "\t" + joinedDate;
    }
}
