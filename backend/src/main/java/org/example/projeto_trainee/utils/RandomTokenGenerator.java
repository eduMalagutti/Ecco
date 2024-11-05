package org.example.projeto_trainee.utils;

import java.security.SecureRandom;

public class RandomTokenGenerator {

    private static final SecureRandom random = new SecureRandom();

    public static String generateNumericToken(int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
