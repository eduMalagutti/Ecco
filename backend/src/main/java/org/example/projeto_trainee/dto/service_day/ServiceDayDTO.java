package org.example.projeto_trainee.dto.service_day;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.projeto_trainee.entities.ServiceDay;
import org.example.projeto_trainee.enums.PeriodEnum;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDayDTO {

    private LocalDate date;

    private PeriodEnum period;

    public ServiceDay convertToServiceDay(){
        return new ServiceDay(date, period);
    }
}
