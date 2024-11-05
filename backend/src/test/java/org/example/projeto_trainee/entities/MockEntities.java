package org.example.projeto_trainee.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile ("test")
@Getter
@Setter
@NoArgsConstructor
public class MockEntities {

    private User mockUser1;

    private ServiceEntity mockServiceEntity;
}
