package org.example.projeto_trainee.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity (name = "message_tb")
public class Message {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    @Column (nullable = false, updatable = false)
    private LocalDateTime moment;

    //@NotBlank(message = "O conteúdo da mensagem não pode estar em branco!")
    @Column (nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn (name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn (name = "reservation_id", nullable = false)
    private Reservation reservation;


}
