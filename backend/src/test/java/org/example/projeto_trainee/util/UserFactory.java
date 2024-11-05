package org.example.projeto_trainee.util;


import org.example.projeto_trainee.dto.user.ChangePasswordRequestDTO;
import org.example.projeto_trainee.dto.user.ForgotPasswordCodeDTO;
import org.example.projeto_trainee.dto.user.LoginUserDTO;
import org.example.projeto_trainee.dto.user.SignUpRequestDTO;

public class UserFactory {

    public static SignUpRequestDTO newUserSignUp() {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setName("John Doe");
        signUpRequestDTO.setEmail("eduardosm2004@gmail.com");
        signUpRequestDTO.setPassword("JohnDoe@1");

        return signUpRequestDTO;
    }

    public static SignUpRequestDTO newUserInvalidPasswordSignUp() {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setName("John Doe");
        signUpRequestDTO.setEmail("eduardosm2004@gmail.com");
        signUpRequestDTO.setPassword("");

        return signUpRequestDTO;
    }

    public static SignUpRequestDTO existingUserSignUp() {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setName("Alice James");
        signUpRequestDTO.setEmail("alicejames@gmail.com");
        signUpRequestDTO.setPassword("Senha@123456");

        return signUpRequestDTO;
    }

    public static LoginUserDTO existingUserSignIn() {
        LoginUserDTO signInRequestDTO = new LoginUserDTO();
        signInRequestDTO.setEmail("alicejames@gmail.com");
        signInRequestDTO.setPassword("123456");

        return signInRequestDTO;
    }

    public static LoginUserDTO nonExistingUserSignIn() {
        LoginUserDTO signInRequestDTO = new LoginUserDTO();
        signInRequestDTO.setEmail("billysmith@gmail.com");
        signInRequestDTO.setPassword("123456");

        return signInRequestDTO;
    }

    public static ChangePasswordRequestDTO changePasswordDataValid() {
        ChangePasswordRequestDTO changePasswordRequestDTO = new ChangePasswordRequestDTO();
        changePasswordRequestDTO.setNewPassword("Password@1");
        return changePasswordRequestDTO;
    }

    public static ForgotPasswordCodeDTO forgotPasswordTokenValid() {
        ForgotPasswordCodeDTO forgotPasswordCodeDTO = new ForgotPasswordCodeDTO();
        forgotPasswordCodeDTO.setEmail("alicejames@gmail.com");
        forgotPasswordCodeDTO.setCode("123456");

        return forgotPasswordCodeDTO;
    }

    public static ForgotPasswordCodeDTO forgotPasswordTokenInvalid() {
        ForgotPasswordCodeDTO forgotPasswordCodeDTO = new ForgotPasswordCodeDTO();
        forgotPasswordCodeDTO.setEmail("thomasjefferson@gmail.com");
        forgotPasswordCodeDTO.setCode("123456");

        return forgotPasswordCodeDTO;
    }

    public static ForgotPasswordCodeDTO forgotPasswordTokenExpired() {
        ForgotPasswordCodeDTO forgotPasswordCodeDTO = new ForgotPasswordCodeDTO();
        forgotPasswordCodeDTO.setEmail("serenawilliams@gmail.com");
        forgotPasswordCodeDTO.setCode("456789");

        return forgotPasswordCodeDTO;
    }
}
