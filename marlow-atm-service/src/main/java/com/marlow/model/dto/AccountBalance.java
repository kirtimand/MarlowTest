package com.marlow.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalance {

    private BigDecimal amount;
    private String firstName;
    private String lastName;
    private String email;

}
