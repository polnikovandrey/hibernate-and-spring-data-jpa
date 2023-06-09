package com.mcfly.sdjpaintro.repositories;

import com.mcfly.sdjpaintro.domain.composite.AuthorComposite;
import com.mcfly.sdjpaintro.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}
