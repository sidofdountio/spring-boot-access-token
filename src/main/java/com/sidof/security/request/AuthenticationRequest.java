package com.sidof.security.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author sidof
 * @Since 04/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    private String username;
    private String password;
}
