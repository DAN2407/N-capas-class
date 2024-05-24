package com.danarg.pncontrollerseccion01.domain.entities;

import com.danarg.pncontrollerseccion01.utils.Encrypter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Sec01_users")
@Data
public class User implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    private String username;
    private String email;
    @Convert(converter = Encrypter.class)
    private String password;
    @Column(name = "active", insertable = false)
    private Boolean active;
    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //getUsername is already overridden

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
