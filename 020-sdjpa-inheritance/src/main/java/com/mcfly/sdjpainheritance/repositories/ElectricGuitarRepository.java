package com.mcfly.sdjpainheritance.repositories;

import com.mcfly.sdjpainheritance.domain.joined.ElectricGuitar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectricGuitarRepository extends JpaRepository<ElectricGuitar, Long> {
}
