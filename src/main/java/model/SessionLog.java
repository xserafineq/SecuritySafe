package model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "login_logs",schema = "safe")
public class SessionLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_time")
    private LocalDate loginTime;
    @Column(name = "ip_address")
    private String ipAdress;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLoginTime(LocalDate loginTime) {
        this.loginTime = loginTime;
    }
}
