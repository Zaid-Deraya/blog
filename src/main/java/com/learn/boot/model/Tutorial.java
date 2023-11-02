package com.learn.boot.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tutorials")
@Getter
@Setter
@NoArgsConstructor
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "published")
    private boolean published;



    public Tutorial(String title, String description, String author, boolean published) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.published = published;
    }
    
}