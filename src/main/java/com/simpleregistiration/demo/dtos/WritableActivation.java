package com.simpleregistiration.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WritableActivation implements Serializable {

    @NotBlank
    @Size(min = 6, max = 6)
    private String activationCode;

}
