package org.example.projeto_trainee.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.projeto_trainee.enums.RejectionReasonEnum;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RejectReservationRequestDTO {
    private UUID id;
    private RejectionReasonEnum rejectionReason;
}
