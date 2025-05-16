package model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "hyperlinks",schema = "safe")
public class HyperLink extends SafeItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hyperlink_id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name="url")
    private String url;
    @Column(name = "last_modified")
    private LocalDate lastModified;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "safe_id")
    private Safe safe;

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
