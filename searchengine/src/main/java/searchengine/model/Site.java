package searchengine.model;

import lombok.*;
import searchengine.model.status.StatusType;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Getter
@ToString
@NoArgsConstructor

@Entity(name = "site")
public class Site implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Page> pages;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('INDEXING', 'INDEXED', 'FAILED') NOT NULL")
    private StatusType status;

    @Column(name = "status_time", updatable = false, nullable = false)
    private Date statusTime;

    @Column(name = "last_error", columnDefinition = "TEXT", nullable = false)
    private String lastError;



    @Column(name = "url", columnDefinition = "VARCHAR(100)", nullable = false)
    private String url;

    @Column(name = "name",columnDefinition = "VARCHAR(100)", nullable = false)
    private String name;

    public Site(String name, String url, StatusType status, Date statusTime, String lastError) {
        this.status = status;
        this.statusTime = statusTime;
        this.lastError = lastError;
        this.url = url;
        this.name = name;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }
    private ArrayList<Site> children = new ArrayList<>();

    public ArrayList<Site> getChildren(){return children;}

    public void addChildren(ArrayList<Site> newChildren) {
        for (int i = 0; i < newChildren.size(); i++){
            children.add(newChildren.get(i));
        }
    }
    public void addChild(Site child){
        children.add(child);
    }





}

