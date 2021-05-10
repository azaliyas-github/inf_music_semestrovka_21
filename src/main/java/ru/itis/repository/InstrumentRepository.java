package ru.itis.repository;

import org.springframework.data.jpa.repository.*;
import ru.itis.model.*;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    Instrument findByName(String name);
}
