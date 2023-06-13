package com.mcfly.hibernate_dao.domain;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(name = "author_find_all", query = "from Author"),
        @NamedQuery(name = "author_find_by_last_name", query = "from Author a where a.lastName = :last_name")
})
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
