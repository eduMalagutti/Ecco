package org.example.projeto_trainee.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateUserRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Email (message = "Insira um email válido")
    private String email;

    @Pattern (regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number")
    private String phone;
}
