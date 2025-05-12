package pl.kosieradzki.siitask.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(precision = 19, scale = 2)
    private BigDecimal boxAmount = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
