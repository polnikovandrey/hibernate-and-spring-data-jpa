package com.mcfly.sdjpawp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "wp_comments",
        indexes = {
                @Index(name = "comment_post_ID", columnList = "comment_post_ID"),
                @Index(name = "comment_approved_date_gmt", columnList = "comment_approved, comment_date_gmt"),
                @Index(name = "comment_date_gmt", columnList = "comment_date_gmt"),
                @Index(name = "comment_parent", columnList = "comment_parent"),
                @Index(name = "comment_author_email", columnList = "comment_author_email")
        })
public class Comment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "comment_ID")
        private Long id;

        @NotNull
        @Column(name = "comment_post_ID")
        private Long postId;

        @NotNull
        @Lob
        @Column(name = "comment_author")
        private String author;

        @NotNull
        @Email
        @Size(max = 100)
        @Column(name = "comment_author_email", length = 100)
        private String authorEmail;

        @NotNull
        @URL
        @Size(max = 200)
        @Column(name = "comment_author_url", length = 200)
        private String authorUrl;

        @NotNull
        @Size(max = 100)
        @Column(name = "comment_author_ip", length = 100)
        private String authorIp;

        @NotNull
        @Column(name = "comment_date")
        private Timestamp date;

        @NotNull
        @Column(name = "comment_date_gmt")
        private Timestamp dateGmt;

        @NotNull
        @Lob
        @Column(name = "comment_content", columnDefinition = "TEXT")
        private String content;

        @NotNull
        @Column(name = "comment_karma")
        private Integer karma;

        @NotNull
        @Size(max = 20)
        @Column(name = "comment_approved", length = 20)
        private String approved;

        @NotNull
        @Size(max = 255)
        @Column(name = "comment_agent")
        private String agent;

        @NotNull
        @Size(max = 20)
        @Column(name = "comment_type", length = 20)
        private String type;

        @NotNull
        @Column(name = "comment_parent")
        private Long parent;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @OneToMany(mappedBy = "comment")
        private Set<CommentMeta> commentMetaSet;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getPostId() {
                return postId;
        }

        public void setPostId(Long postId) {
                this.postId = postId;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public String getAuthorEmail() {
                return authorEmail;
        }

        public void setAuthorEmail(String authorEmail) {
                this.authorEmail = authorEmail;
        }

        public String getAuthorUrl() {
                return authorUrl;
        }

        public void setAuthorUrl(String authorUrl) {
                this.authorUrl = authorUrl;
        }

        public String getAuthorIp() {
                return authorIp;
        }

        public void setAuthorIp(String authorIp) {
                this.authorIp = authorIp;
        }

        public Timestamp getDate() {
                return date;
        }

        public void setDate(Timestamp date) {
                this.date = date;
        }

        public Timestamp getDateGmt() {
                return dateGmt;
        }

        public void setDateGmt(Timestamp dateGmt) {
                this.dateGmt = dateGmt;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public Integer getKarma() {
                return karma;
        }

        public void setKarma(Integer karma) {
                this.karma = karma;
        }

        public String getApproved() {
                return approved;
        }

        public void setApproved(String approved) {
                this.approved = approved;
        }

        public String getAgent() {
                return agent;
        }

        public void setAgent(String agent) {
                this.agent = agent;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public Long getParent() {
                return parent;
        }

        public void setParent(Long parent) {
                this.parent = parent;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public Set<CommentMeta> getCommentMetaSet() {
                return commentMetaSet;
        }

        public void setCommentMetaSet(Set<CommentMeta> commentMetaSet) {
                this.commentMetaSet = commentMetaSet;
        }
}
