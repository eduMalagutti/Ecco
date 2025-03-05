package org.example.projeto_trainee.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.example.projeto_trainee.dto.JWTokenDTO;
import org.example.projeto_trainee.dto.user.*;
import org.example.projeto_trainee.enums.TypeDocumentEnum;
import org.example.projeto_trainee.exceptions.BadCredentialsException;
import org.example.projeto_trainee.services.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@CrossOrigin
@Tag(name = "user-controller", description = "Controller for all user routes! " +
        "When using swagger there is no need to fill the authorization header")
@RequestMapping ("/v1/users")
public class UserController {

    @Autowired
    private SignUpUserService signUpUserService;

    @Autowired
    private VerifyUserEmailService verifyUserEmailService;

    @Autowired
    private HelloService helloService;

    @Autowired
    private UpdateUserProfilePicService updateUserProfilePicService;

    @Autowired
    private UpdateUserService updateUserService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private GetUserAttributesService getUserAttributesService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private ChangePasswordService changePasswordService;

    @Autowired
    private GetUserProfilePicService getUserProfilePicService;

    @Autowired
    private DeleteUserService deleteUserService;

    @Autowired
    private VerifyProfileService verifyProfileService;

    @Autowired
    private UserRatingService userRatingService;

    // HELLO
    @GetMapping ("/hello")
    public ResponseEntity<String> hello(@Nullable @RequestHeader ("Authorization") String token) {
        HelloResponseDTO helloResponse = helloService.execute(token);

        return ResponseEntity.ok(helloResponse.getMessage());
    }

    // GET USER ATTRIBUTES
    @GetMapping ("/attributes")
    public ResponseEntity<GetUserAtrributesDTO> getUserAttributes(@Nullable @RequestHeader ("Authorization") String token) {
        GetUserAtrributesDTO user = getUserAttributesService.execute(token);

        return ResponseEntity.ok().body(user);
    }

    // GET USER RATING
    @GetMapping ("/rating")
    public ResponseEntity<Double> getUserRating(@Nullable @RequestHeader ("Authorization") String token) {
        Double userRating = userRatingService.getUserRating(token);
        return ResponseEntity.ok().body(userRating);
    }

    // GET USER PROFILE PIC
    @GetMapping ("/profile-pic")
    public ResponseEntity<byte[]> getUserProfilePic(@Nullable @RequestHeader ("Authorization") String token) {
        var response = getUserProfilePicService.getUserProfilePic(token);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    // SIGNUP
    @PostMapping ("/signup")
    public ResponseEntity<SignUpResponseDTO> signUpUser(@Valid @RequestBody SignUpRequestDTO signUpRequest) {

        SignUpResponseDTO response = signUpUserService.execute(signUpRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // LOGIN
    @PostMapping ("/login")
    public ResponseEntity<JWTokenDTO> loginUser(@Valid @RequestBody LoginUserDTO loginUserDto) throws BadCredentialsException {
        String token = loginService.authenticate(loginUserDto);
        return new ResponseEntity<>(new JWTokenDTO(token), HttpStatus.OK);
    }

    // UPDATE ATTRIBUTES
    @PutMapping ("/update/attributes")
    public ResponseEntity<Map<String, Object>> updateUserAttributes(@Nullable @RequestHeader ("Authorization") String token, @Valid @RequestBody UpdateUserRequestDTO userDetails) {

        Map<String, Object> modifiedFields = updateUserService.execute(token, userDetails);

        return ResponseEntity.status(HttpStatus.OK).body(modifiedFields);
    }

    // UPDATE PROFILE PICTURE
    @PatchMapping (path = "/update/profile-pic", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserPic(@Nullable @RequestHeader ("Authorization") String token, @RequestBody MultipartFile newPic) {

        updateUserProfilePicService.execute(token, newPic);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // SEND FORGOT PASSWORD EMAIL
    @PatchMapping ("/forgot-password")
    public ResponseEntity<Void> sendForgotPasswordEmail(@PathParam ("email") String email) {

        forgotPasswordService.sendEmail(new ForgotPasswordRequestDTO(email));

        return ResponseEntity.ok().build();
    }

    // VERIFY CODE TO CHANGE PASSWORD
    @PatchMapping ("/forgot-password/verify")
    public ResponseEntity<JWTokenDTO> verifyChangePasswordCode(@RequestBody ForgotPasswordCodeDTO forgotPasswordCodeDTO) {
        String token = forgotPasswordService.verifyCode(forgotPasswordCodeDTO);
        return ResponseEntity.ok().body(new JWTokenDTO(token));
    }

    // CHANGE USER PASSWORD
    @PatchMapping ("/change-password")
    public ResponseEntity<ChangePasswordResponseDTO> changeUserPassword(@Nullable @RequestHeader ("Authorization") String token, @RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        changePasswordService.changePassword(token, changePasswordRequestDTO);
        ChangePasswordResponseDTO changePasswordResponseDTO = new ChangePasswordResponseDTO();
        changePasswordResponseDTO.setMessage("Password changed!");

        return ResponseEntity.ok().body(changePasswordResponseDTO);
    }

    // VERIFY USER EMAIL
    @GetMapping ("/verify-email/{verificationToken}") //TODO: MAY BE A PATCH METHOD
    public ResponseEntity<String> verifyUserEmail(@PathVariable String verificationToken) {

        verifyUserEmailService.verifyAccount(new VerifyRequestDTO(verificationToken));
        VerifyResponseDTO response = new VerifyResponseDTO("Account verified!");

        return ResponseEntity.ok().body(response.getMessage());
    }

    // VERIFY USER PROFILE BY DOCUMENT
    @PatchMapping ("/verify-profile")
    public ResponseEntity<String> verifyProfile(@Nullable @RequestHeader ("Authorization") String token,
                                                @RequestBody MultipartFile photoFrontDocument,
                                                MultipartFile photoBackDocument, TypeDocumentEnum typeDocument) {

        verifyProfileService.execute(token, photoFrontDocument, photoBackDocument, typeDocument);

        return ResponseEntity.status(HttpStatus.OK).body("Profile verified!");
    }

    // DELETE USER
    @DeleteMapping ("/delete")
    public ResponseEntity<Void> deleteUser(@Nullable @RequestHeader ("Authorization") String token) {
        deleteUserService.delete(token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
