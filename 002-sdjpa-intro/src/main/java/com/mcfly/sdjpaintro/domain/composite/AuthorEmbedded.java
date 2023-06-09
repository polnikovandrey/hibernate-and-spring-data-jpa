package com.mcfly.sdjpaintro.domain.composite;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "author_composite")
public class AuthorEmbedded {

    @EmbeddedId
    private NameId nameId;
    private String country;

    public AuthorEmbedded() {
    }

    public AuthorEmbedded(NameId nameId, String country) {
        this.nameId = nameId;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorEmbedded that = (AuthorEmbedded) o;

        return Objects.equals(nameId, that.nameId);
    }

    @Override
    public int hashCode() {
        return nameId != null ? nameId.hashCode() : 0;
    }

    public NameId getNameId() {
        return nameId;
    }

    public void setNameId(NameId nameId) {
        this.nameId = nameId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
