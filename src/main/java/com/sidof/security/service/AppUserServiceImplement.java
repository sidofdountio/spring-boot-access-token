package com.sidof.security.service;



import com.sidof.security.model.ConfirmationToken;
import com.sidof.security.model.Role;
import com.sidof.security.request.AddRoleToUserRequest;
import com.sidof.security.request.AuthenticationRequest;
import com.sidof.security.request.AuthenticationResponse;
import com.sidof.security.request.RegisterRequester;
import com.sidof.security.model.AppUser;

import java.util.List;

/**
 * @Author sidof
 * @Since 01/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
public interface AppUserServiceImplement {
    AuthenticationResponse register (RegisterRequester registerRequester);
    AuthenticationResponse authentication (AuthenticationRequest requester) throws Exception;
    AppUser updateAppUser(AppUser appUser);
    AppUser getAppUserById(Long id);
    List<AppUser>APP_USER_LIST();
    Role saveRole(Role role);
    AddRoleToUserRequest addRoleToUser(AddRoleToUserRequest request);
    ConfirmationToken confirmToken(String token);

}
