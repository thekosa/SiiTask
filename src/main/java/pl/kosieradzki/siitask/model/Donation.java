package pl.kosieradzki.siitask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(precision = 19, scale = 2)
    private BigDecimal amount = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "box_id")
    @JsonIgnore
    private Box box;
}
