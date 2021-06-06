package ru.itis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.*;
import ru.itis.model.*;

import java.util.*;

public interface SheetRepository extends JpaRepository<Sheet, Long>, QuerydslPredicateExecutor<Sheet> {
    List<Sheet> findAll();
    List<Sheet> findByInstruments(Instrument instrument);
}
