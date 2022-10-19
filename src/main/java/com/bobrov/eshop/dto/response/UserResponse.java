package com.bobrov.eshop.dto.response;

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
public class UserResponse implements Serializable {
    // TODO validate
    private Long id;
    private String username;
    private String phone;
    private String email;
}