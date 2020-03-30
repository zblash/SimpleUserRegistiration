package com.simpleregistiration.demo.dtos;

import com.simpleregistiration.demo.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WritableRegister implements Serializable {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 3, max = 25)
    private String name;

    @NotBlank
    @Size(min = 3, max = 25)
    private String surname;

    @NotBlank
    @Size(min = 5, max = 90)
    private String password;

    private RoleType roleType;

}