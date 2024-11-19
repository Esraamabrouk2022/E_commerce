package com.example.E_commerce.entity;

import com.example.E_commerce.entity.Enum.User_Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(updatable = true, name = "updated_at")
    private Date updatedAt;

    private String phone;
    @Enumerated(EnumType.STRING)
    private User_Role userRole;
    private Boolean locked = false;
    private Boolean enabled = true;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart shoppingCart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)

    private List<Order> orders;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public User(String firstName,
                String lastName,
                String email,
                String password,
                Date createdAt,
                Date updatedAt,
                String phone,
                User_Role userRole,
                Boolean locked,
                Boolean enabled,
                Cart shoppingCart,
                List<Order> orders,
                List<Review> reviews) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.phone = phone;
        this.userRole = userRole;
        this.locked = locked;
        this.enabled = enabled;
        this.shoppingCart = shoppingCart;
        this.orders = orders;
        this.reviews = reviews;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
