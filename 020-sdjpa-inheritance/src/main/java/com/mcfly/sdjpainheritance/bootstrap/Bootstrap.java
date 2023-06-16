package com.mcfly.sdjpainheritance.bootstrap;

import com.mcfly.sdjpainheritance.domain.joined.ElectricGuitar;
import com.mcfly.sdjpainheritance.repositories.ElectricGuitarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final ElectricGuitarRepository electricGuitarRepository;

    public Bootstrap(ElectricGuitarRepository electricGuitarRepository) {
        this.electricGuitarRepository = electricGuitarRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        final ElectricGuitar electricGuitar = new ElectricGuitar();
        electricGuitar.setNumberOfStrings(6);
        electricGuitar.setNumberOfPickups(2);
        electricGuitarRepository.save(electricGuitar);
    }
}
