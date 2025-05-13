package pl.kosieradzki.siitask.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(precision = 19, scale = 2)
    private BigDecimal accountAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Box> boxes = new ArrayList<>();
}
