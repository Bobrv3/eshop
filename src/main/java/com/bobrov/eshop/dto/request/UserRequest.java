package com.bobrov.eshop.dto.request;

import com.bobrov.eshop.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {
    // TODO validate
    private Long id;
    private String username;
    private String password;
    private String repeatPassword;
    private String phone;
    private String email;
}