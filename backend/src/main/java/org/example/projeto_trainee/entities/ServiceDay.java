package org.example.projeto_trainee.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.projeto_trainee.dto.service_day.ServiceDayDTO;
import org.example.projeto_trainee.enums.PeriodEnum;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "service_day_tb")
public class ServiceDay {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (nullable = false)
    private LocalDate date;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private PeriodEnum period;

    @ManyToOne
    @JoinColumn (name = "service_id", nullable = false)
    private ServiceEntity service;

    public ServiceDay(LocalDate date, PeriodEnum periodEnum) {
        this.date = date;
        this.period = periodEnum;
    }

    public ServiceDay(LocalDate date, PeriodEnum periodEnum, ServiceEntity service) {
        this.date = date;
        this.period = periodEnum;
        this.service = service;
    }

    public ServiceDayDTO convertToServiceDayDTO() {
        return new ServiceDayDTO(this.date, this.period);
    }
}
