package org.example.projeto_trainee.controllers;

import jakarta.validation.Valid;
import org.example.projeto_trainee.dto.reservation.*;
import org.example.projeto_trainee.services.reservation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping ("/v1/reservations")
public class ReservationController {

    @Autowired
    private GetReservationService getReservationService;

    @Autowired
    private CreateReservationService createReservationService;

    @Autowired
    private DeleteReservationService deleteReservationService;

    @Autowired
    CancelReservationService cancelReservationService;

    @Autowired
    RejectReservationService rejectReservationService;

    @Autowired
    AcceptReservationService acceptReservationService;

    @Autowired
    ReviewReservationService reviewReservationService;

    @GetMapping ("/{id}")
    public ResponseEntity<GetReservationResponseDTO> getReservation(@Valid @PathVariable UUID id) {
        GetReservationResponseDTO response = getReservationService.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping ("/create")
    public ResponseEntity<CreateReservationResponseDTO> createReservation(
            @Valid @RequestBody CreateReservationRequestDTO request,
            @RequestHeader ("Authorization") String token) {
        CreateReservationResponseDTO response = createReservationService.execute(request, token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable UUID id) {
        deleteReservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping ("/cancel-reservation")
    public ResponseEntity<CancelReservationDTO> cancelReservation(@RequestParam UUID id, @RequestHeader("Authorization") String token) {
        CancelReservationDTO response = cancelReservationService.execute(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping ("/reject-reservation")
    public ResponseEntity<RejectReservationDTO> rejectReservation(@Valid @RequestBody RejectReservationRequestDTO request, @RequestHeader("Authorization") String token) {
        RejectReservationDTO response = rejectReservationService.execute(request, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping ("/accept-reservation")
    public ResponseEntity<AcceptReservationDTO> acceptReservation(@RequestParam UUID id, @RequestHeader("Authorization") String token) {
        AcceptReservationDTO response = acceptReservationService.execute(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping ("/review-reservation-customer")
    public ResponseEntity<String> reviewToCustomer(@RequestHeader ("Authorization") String token,
                                                   @RequestBody ReviewToCustomerRequestDTO requestBody) {
        reviewReservationService.reviewToCustomer(requestBody, token);
        return ResponseEntity.ok().body("Review made for customer!");
    }

    @PatchMapping ("/review-reservation-provider")
    public ResponseEntity<String> reviewToProvider(@RequestHeader ("Authorization") String token,
                                                   @RequestBody ReviewToProviderRequestDTO requestBody) {
        reviewReservationService.reviewToProvider(requestBody, token);
        return ResponseEntity.ok().body("Review made for provider!");
    }
}
