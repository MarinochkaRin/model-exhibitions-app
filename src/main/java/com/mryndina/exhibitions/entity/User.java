package com.mryndina.exhibitions.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="USERS")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, updatable = false)
    private long id;
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;
/*    @Column(name="PHOTO_ID")
    private String photoId;*/
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="SECOND_NAME")
    private String secondName;

    @Column(name="LOCATION")
    private String locationPerson;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(String.valueOf(role)));
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
