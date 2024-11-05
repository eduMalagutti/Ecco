package org.example.projeto_trainee.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.projeto_trainee.enums.RejectionReasonEnum;
import org.example.projeto_trainee.enums.StatusEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "reservation_tb")
public class Reservation {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated (EnumType.STRING)
    @Column ()
    private RejectionReasonEnum rejectionReason;

    @Column (nullable = false)
    private LocalDateTime startTime;

    @Column (nullable = false)
    private LocalDateTime endTime;

    @Column (nullable = false)
    private Integer number;

    @Column (nullable = false)
    private String street;

    @Column (nullable = false)
    private String neighborhood;

    @Column (nullable = false)
    private String city;

    @Column (nullable = false)
    private String state;

    @Column (nullable = false)
    private String cep;

    @Column (nullable = false)
    private String comment;

    @Column (nullable = false)
    private LocalDate requestDate;

    @Column
    private String commentReviewToProvider;

    @Column
    private Integer noteReviewToProvider;

    @Column
    private String commentReviewToCustomer;

    @Column
    private Integer noteReviewToCustomer;

    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private StatusEnum status;

    @OneToMany (mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @ManyToOne
    @JoinColumn (name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn (name = "service_id", nullable = false)
    private ServiceEntity service;
}
