package org.example.projeto_trainee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponseDTO {

    private Integer status;

    private String error;

    private String message;
}
