package ru.itis.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "sheet_intruments",
            joinColumns = {@JoinColumn(name = "sheet_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "instrument_id", referencedColumnName = "id")})
    private List<Instrument> instruments;

    private String title;
    private String composerName;

    private String src;
    private String previewSrc;
}
