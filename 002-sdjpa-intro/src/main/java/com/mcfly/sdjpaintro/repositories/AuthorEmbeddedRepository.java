package com.mcfly.sdjpaintro.repositories;

import com.mcfly.sdjpaintro.domain.composite.AuthorEmbedded;
import com.mcfly.sdjpaintro.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId> {
}
