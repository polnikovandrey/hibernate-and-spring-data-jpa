package com.mcfly.sdjpawp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "wp_commentmeta",
indexes = {
        @Index(name = "comment_id", columnList = "comment_id"),
        @Index(name = "meta_key", columnList = "meta_key")
})
public class CommentMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_id")
    private Long id;

    @Size(max = 255)
    @Column(name = "meta_key")
    private String key;

    @Lob
    @Column(name = "meta_value", columnDefinition = "LONGTEXT")
    private String value;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
