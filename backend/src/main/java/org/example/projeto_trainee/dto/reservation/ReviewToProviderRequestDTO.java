package org.example.projeto_trainee.dto.reservation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewToProviderRequestDTO {

    @NotBlank
    private UUID reservationId;

    @NotBlank
    private String commentReviewToProvider;

    @NotNull
    @Min (1)
    @Max (5)
    private Integer noteReviewToProvider;
}
