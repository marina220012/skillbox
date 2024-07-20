package searchengine.model;

import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity(name = "page")
public class Page {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne (optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @Column(name = "site_id", insertable = false, updatable = false, nullable = false)
    private Site site;

    @Column(name = "path", insertable = false, updatable = false, nullable = false)
    private String path;

    @Column(name = "code", insertable = false, updatable = false, nullable = false)
    private Integer code;

    @Column(name = "content", insertable = false, updatable = false, nullable = false)
    private StringBuilder content;

    public Page(Integer id, Site site, String path, Integer code, StringBuilder content) {
        this.id = id;
        this.site = site;
        this.path = path;
        this.code = code;
        this.content = content;
    }
}


