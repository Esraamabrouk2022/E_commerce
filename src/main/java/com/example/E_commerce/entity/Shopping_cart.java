package com.example.E_commerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="shopping_cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shopping_cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "shoppingCart",cascade = CascadeType.ALL)
    private List<Cart_Item> cartItems;

}
