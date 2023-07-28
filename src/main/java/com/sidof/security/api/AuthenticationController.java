package com.sidof.security.api;


import com.sidof.security.model.ConfirmationToken;
import com.sidof.security.model.Role;
import com.sidof.security.request.AddRoleToUserRequest;
import com.sidof.security.request.AuthenticationRequest;
import com.sidof.security.request.AuthenticationResponse;
import com.sidof.security.request.RegisterRequester;
import com.sidof.security.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * @Author sidof
 * @Since 01/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200",originPatterns = "http://localhost:9000",maxAge = 3600)
@RequiredArgsConstructor
public class AuthenticationController {
    private final AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequester request) {
        AuthenticationResponse token = appUserService.register(request);
        return new ResponseEntity<>(token,CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        AuthenticationResponse token = appUserService.authentication(request);
        return new ResponseEntity<AuthenticationResponse>(token,CREATED);
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<ConfirmationToken> confirm(@RequestParam("token") String token) {
        return new ResponseEntity<>(appUserService.confirmToken(token),OK);
    }


}
