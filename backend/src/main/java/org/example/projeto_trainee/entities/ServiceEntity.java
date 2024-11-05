package org.example.projeto_trainee.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "service_tb")
public class ServiceEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private Double fixedPrice;

    @Column
    private Double minPrice;

    @Column
    private Double maxPrice;

    @ManyToOne ()
    @JoinColumn (name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

    @ManyToOne ()
    @JoinColumn (name = "user_id", nullable = false)
    private User user;

    @OneToMany (mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceDay> serviceDays;

    @OneToMany (mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    public ServiceEntity(Double fixedPrice, Double minPrice, Double maxPrice, SubCategory subCategory, User user) {
        this.fixedPrice = fixedPrice;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.subCategory = subCategory;
        this.user = user;
    }
}
