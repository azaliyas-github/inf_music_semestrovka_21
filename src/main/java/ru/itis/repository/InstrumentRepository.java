package ru.itis.repository;

import org.springframework.data.jpa.repository.*;
import ru.itis.model.*;

import java.util.*;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    Instrument findByName(String name);
    List<Instrument> findByNameIn(Collection<String> instrument);
}
