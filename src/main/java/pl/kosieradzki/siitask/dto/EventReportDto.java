package pl.kosieradzki.siitask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class EventReportDto {
    private String eventName;
    private BigDecimal amount;
    private String currency;
}
