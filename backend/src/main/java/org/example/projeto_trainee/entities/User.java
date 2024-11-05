package org.example.projeto_trainee.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.projeto_trainee.enums.TypeDocumentEnum;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity (name = "user_tb")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    //@Lob
    @Basic (fetch = FetchType.LAZY)
    private byte[] profilePhoto;

    @Column
    private String phone;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false, unique = true)
    private String email;

    @Column (nullable = false)
    private String name;

    @Column
    @Transient
    private Double rating = 0.0;

    //@Lob
    @Basic (fetch = FetchType.LAZY)
    private byte[] photoFrontDocument;

    //@Lob
    @Basic (fetch = FetchType.LAZY)
    private byte[] photoBackDocument;

    @Enumerated (EnumType.STRING)
    private TypeDocumentEnum typeDocument;

    @Column (nullable = false)
    private Boolean verified = false;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceEntity> services = new ArrayList<>();

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @Column (updatable = false)
    private String tokenVerification;

    private String changePasswordToken;

    private Instant changePasswordTokenExpirationDate;


/*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

 */
}
