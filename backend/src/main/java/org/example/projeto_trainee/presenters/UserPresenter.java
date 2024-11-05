package org.example.projeto_trainee.presenters;

import org.example.projeto_trainee.dto.user.UpdateUserRequestDTO;
import org.example.projeto_trainee.entities.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserPresenter {

    public Map<String, Object> presentModifiedFields(User ogUser, UpdateUserRequestDTO newUser) {
        Map<String, Object> modifiedFields = new HashMap<>();

        if (newUser.getName() != null && !newUser.getName().equals(ogUser.getName()))
            modifiedFields.put("name", newUser.getName());
        if (newUser.getEmail() != null && !newUser.getEmail().equals(ogUser.getEmail()))
            modifiedFields.put("email", newUser.getEmail());
        if (newUser.getPhone() != null && !newUser.getPhone().equals(ogUser.getPhone()))
            modifiedFields.put("phone", newUser.getPhone());

        return modifiedFields.isEmpty() ? null : modifiedFields;
    }
}
