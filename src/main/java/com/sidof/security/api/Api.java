package com.sidof.security.api;

import com.sidof.security.model.AppUser;
import com.sidof.security.model.Role;
import com.sidof.security.request.AddRoleToUserRequest;
import com.sidof.security.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @Author sidof
 * @Since 04/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/app")
public class Api {
    private final AppUserService appUserService;
    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> demo(){
        return  new ResponseEntity<>(appUserService.APP_USER_LIST(),OK);
    }

    @PostMapping("/addRoleToUser")
    public ResponseEntity<AddRoleToUserRequest> addRoleToUser(@RequestBody AddRoleToUserRequest request) {
        return new ResponseEntity<>(appUserService.addRoleToUser(request),CREATED);
    }

    @PostMapping("/addRole")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        return new ResponseEntity<>(appUserService.saveRole(role),CREATED);
    }

}
