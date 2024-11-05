package org.example.projeto_trainee.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.projeto_trainee.enums.RejectionReasonEnum;
import org.example.projeto_trainee.enums.StatusEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetReservationResponseDTO {
    private UUID id;

    private UUID userId;

    private UUID serviceId;

    private RejectionReasonEnum rejectionReason;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer number;

    private String street;

    private String neighborhood;

    private String city;

    private String state;

    private String cep;

    private String comment;

    private LocalDate requestDate;

    private String commentReviewToProvider;

    private Integer noteReviewToProvider;

    private String commentReviewToCustomer;

    private Integer noteReviewToCustomer;

    private StatusEnum status;

    }

