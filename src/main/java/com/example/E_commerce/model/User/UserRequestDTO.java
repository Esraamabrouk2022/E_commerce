package com.example.E_commerce.model.User;

import com.example.E_commerce.entity.Enum.User_Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotEmpty(message = "Last name cannot be empty")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    @NotNull(message = "Email cannot be null")
    @Email(message = "Please provide a valid email address")
    @Size(min = 5, max = 250, message = "Email size should be between 5 and 250 characters")
    private String email;

    @NotNull(message = "can't be null to create new user")
    @Size(min=5,max=250,
            message = "password size should be between" +
                    " 5 and 32 digit or character")
    private String password;

    private User_Role role;
}