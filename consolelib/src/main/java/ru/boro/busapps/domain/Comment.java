package ru.boro.busapps.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@NamedEntityGraph(name = "comment-entity-graph", attributeNodes = {@NamedAttributeNode("book")})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private long id;

    @Column(name = "COMMENTTEXT")
    @ToString.Include
    private String commentText;

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY
    )
    @JoinColumn(name = "bookid", referencedColumnName = "ID")
    private Book book;

    public Comment(String commentText, Book book) {
        this.commentText = commentText;
        this.book = book;
    }

    public Comment(long id, String commentText) {
        this.id = id;
        this.commentText = commentText;
    }
}
