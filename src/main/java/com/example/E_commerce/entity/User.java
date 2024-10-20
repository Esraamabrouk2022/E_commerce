package com.example.E_commerce.entity;

import com.example.E_commerce.entity.Enum.User_Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    private String phone;
    private User_Role userRole;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Cart shoppingCart;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)

    private List<Order> orders;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

}
