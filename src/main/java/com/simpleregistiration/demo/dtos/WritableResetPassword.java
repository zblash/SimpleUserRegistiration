package com.simpleregistiration.demo.dtos;

import com.simpleregistiration.demo.validations.FieldMatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class WritableResetPassword implements Serializable {

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}
