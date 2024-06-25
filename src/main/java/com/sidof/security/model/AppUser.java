package com.sidof.security.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 01/07/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "app_user")
public class AppUser implements UserDetails {
    @SequenceGenerator(
            name = "sequence_appUser",
            sequenceName = "sequence_appUser",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "sequence_appUser",
            strategy = SEQUENCE
    )
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    @Column(unique = true,
            nullable = false)
    private String email;
    @ManyToMany(fetch = EAGER,cascade = ALL)
    private Collection<Role>  roles =new ArrayList<>();
    private Boolean locked;
    private Boolean enable;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        getRoles().forEach(
                role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                }
        );
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
