package searchengine.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import searchengine.model.status.StatusType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity(name = "site")
public class Site {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Page> pages;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('INDEXING', 'INDEXED', 'FAILED') NOT NULL")
    private StatusType status;

    @Column(name = "status_time", insertable = false, updatable = false, nullable = false)
    private LocalDateTime statusTime;

    @Column(name = "last_error", columnDefinition = "TEXT", nullable = false)
    private String lastError;

    @Column(name = "url", columnDefinition = "VARCHAR(100)", nullable = false)
    private String url;

    @Column(name = "name",columnDefinition = "VARCHAR(100)", nullable = false)
    private String name;

    public Site(Integer id, StatusType status, LocalDateTime statusTime, String lastError, String url, String name) {
        this.id = id;
        this.status = status;
        this.statusTime = statusTime;
        this.lastError = lastError;
        this.url = url;
        this.name = name;
    }

}

