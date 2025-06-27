package searchengine.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;


import jakarta.persistence.*;

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
   // @JoinColumn(name = "id")
    @JoinColumn(name = "site_id", nullable = false)
    private Site siteId;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "code",  nullable = false)
    private Integer code;

    @Column(name = "content", nullable = false)
    private StringBuilder content;

    public Page(Integer id, Site siteId, String path, Integer code, StringBuilder content) {
        this.id = id;
        this.siteId = siteId;
        this.path = path;
        this.code = code;
        this.content = content;
    }
}


