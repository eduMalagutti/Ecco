package org.example.projeto_trainee.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "sub_category_tb")
public class SubCategory {

    @Id
    private UUID id;

    @Column (nullable = false)
    private String name;

    @ManyToOne ()
    @JoinColumn (name = "category_id", nullable = false)
    private Category category;

    @OneToMany (mappedBy = "subCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceEntity> services = new ArrayList<>();
}
