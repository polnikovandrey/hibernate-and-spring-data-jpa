package com.mcfly.sdjpawp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.sql.Timestamp;

@Entity
@Table(name = "wp_users",
        indexes = {
                @Index(name = "user_login_key", columnList = "user_login"),
                @Index(name = "user_nicename", columnList = "user_nicename"),
                @Index(name = "user_email", columnList = "user_email")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 60)
    @Column(name = "user_login", length = 60)
    private String login;
    @Size(max = 255)
    @Column(name = "user_pass")
    private String password;
    @Size(max = 50)
    @Column(name = "user_nicename", length = 50)
    private String nicename;
    @Email
    @Size(max = 100)
    @Column(name = "user_email", length = 100)
    private String email;
    @URL
    @Size(max = 100)
    @Column(name = "user_url", length = 100)
    private String url;
    @Column(name = "user_registered")
    private Timestamp registered;
    @Size(max = 255)
    @Column(name = "user_activation_key")
    private String activationKey;
    @Column(name = "user_status")
    private Integer status;
    @Size(max = 250)
    @Column(name = "display_name", nullable = false, length = 250)
    private String displayName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNicename() {
        return nicename;
    }

    public void setNicename(String nicename) {
        this.nicename = nicename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getRegistered() {
        return registered;
    }

    public void setRegistered(Timestamp registered) {
        this.registered = registered;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}