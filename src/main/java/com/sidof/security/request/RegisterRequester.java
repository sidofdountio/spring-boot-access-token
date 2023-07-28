package com.sidof.security.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author sidof
 * @Since 04/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequester {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
