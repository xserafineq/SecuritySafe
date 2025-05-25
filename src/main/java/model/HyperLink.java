package model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hyperlinks",schema = "safe")
public class HyperLink extends SafeItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hyperlink_id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name="url")
    private String url;
    @Column(name = "last_modified")
    private LocalDate lastModified;

    @ManyToOne
    @JoinColumn(name = "safe_id")
    private Safe safe;

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public void setSafe(Safe safe) {
        this.safe = safe;
    }
}
