package org.example.projeto_trainee.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserAtrributesDTO {

    private String name;

    private String phone;

    private String email;
}
