package com.sidof.security.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author sidof
 * @Since 27/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class AddRoleToUserRequest {
    private String username;
    private String roleName;
}
