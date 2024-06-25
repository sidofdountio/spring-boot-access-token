package com.sidof.security.service;


import com.sidof.security.mail.EmailServiceImpl;
import com.sidof.security.mail.EmailValidator;
import com.sidof.security.model.AppUser;
import com.sidof.security.model.ConfirmationToken;
import com.sidof.security.model.Role;
import com.sidof.security.repository.AppUserRepository;
import com.sidof.security.repository.ConfirmationTokenRepository;
import com.sidof.security.repository.RoleRepository;
import com.sidof.security.request.AddRoleToUserRequest;
import com.sidof.security.request.AuthenticationRequest;
import com.sidof.security.request.AuthenticationResponse;
import com.sidof.security.request.RegisterRequester;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import org.apache.coyote.BadRequestException;

/**
 * @Author sidof
 * @Since 01/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AppUserService implements AppUserServiceImplement {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailValidator emailValidator;
    private final EmailServiceImpl emailService;
    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {}", role);
        return roleRepository.save(role);
    }

    @Override
    public AuthenticationResponse register(RegisterRequester request) throws BadRequestException {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException(String.format("Email %s not in right format", request.getEmail()));
        }
        Optional<AppUser> existAppUser = appUserRepository.findByEmail(request.getEmail());
        if (existAppUser.isPresent()) {
            log.error("Email {} already taken", request.getEmail());
            throw new  BadRequestException(String.format("Email %s already taken", request.getEmail()));
        }
        var user = AppUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        appUserRepository.save(user);
        log.info("New user {} register", request);

        var token = JwtService.generateAccessToken(user);
        ConfirmationToken.builder()
                .token(token)
                .createdAt(now())
                .expiresAt(now().plusDays(1))
                .build();
        String link = "http://localhost:8081/api/v1/auth/confirm?token=" + token;
        emailService.sendSimpleEmailMessage(request.getEmail(), request.getFirstName(),link);
        return AuthenticationResponse
                .builder()
                .token(token)
                .build();
    }

    @Override
    public AddRoleToUserRequest addRoleToUser(AddRoleToUserRequest request) {
        String roleName = request.getRoleName();
        String username = request.getUsername();
        log.info("Adding new role {}, to user {}", roleName, username);
        AppUser userByName = appUserRepository.findByFirstName(username);
        Role roleByName = roleRepository.findByName(roleName);
        userByName.getRoles().add(roleByName);
        return AddRoleToUserRequest
                .builder()
                .username(username)
                .roleName(roleName)
                .build();
    }

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest request) throws Exception {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = appUserRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new IllegalStateException(String.format("username %s not found", request.getUsername())));
        var access_token = JwtService.generateAccessToken(user);
        var refresh_token = JwtService.generateRefreshToken(user);

        return AuthenticationResponse
                .builder()
                .token(access_token)
                .refresh_token(refresh_token)
                .build();
    }

    @Override
    public ConfirmationToken confirmToken(String token) throws BadRequestException {

        ConfirmationToken tokenToConfirm = confirmationTokenRepository.findByToken(token);
        if (tokenToConfirm != null) {
            throw new BadRequestException(String.format("Token %s not found", token));
        }
        if (tokenToConfirm.getConfirmedAt() != null) {
            log.error("Token already confirmed");
            throw new BadRequestException("Token already confirmed");
        }
        if (jwtService.isTokenExpired(token)) {
            throw new IllegalStateException("Token expired");
        }
        tokenToConfirm.setConfirmedAt(now());
        final String username = jwtService.extractUsername(token);
        AppUser appUserPresent = appUserRepository.findByEmail(username)
                .orElseThrow(() -> new NullPointerException(String.format("Invalid  token %s", token)));
        appUserPresent.setEnable(true);
        appUserPresent.setLocked(true);
        return confirmationTokenRepository.save(tokenToConfirm);
    }

    @Override
    public AppUser updateAppUser(AppUser appUser) {
        return null;
    }

    @Override
    public AppUser getAppUserById(Long id) {
        return null;
    }

    @Override
    public List<AppUser> APP_USER_LIST() {
        return appUserRepository.findAll();
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }


}