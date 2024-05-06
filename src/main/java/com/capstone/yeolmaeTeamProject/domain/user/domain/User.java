package com.capstone.yeolmaeTeamProject.domain.user.domain;

import com.capstone.yeolmaeTeamProject.global.common.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity implements UserDetails {

    @Id
    private String id;

    @JsonIgnore
    private String password;

    @Column(nullable = false)
    @Size(min = 1, max = 10, message = "이름은 1자 이상 10자 이하로 입력해 주세요.")
    private String name;

    @Column(nullable = false)
    @Email(message = "이메일 형식에 맞게 입력해 주세요.")
    @Size(min = 1, max = 50, message = "이메일은 1자 이상 50자 이하로 입력해 주세요.")
    private String email;

    @Column(nullable = false)
    @Size(min = 1, max = 10, message = "학교는 1자 이상 10자 이하로 입력해 주세요.")
    private String school;

    @Column(nullable = false)
    @Size(min = 1, max = 10, message = "전공은 1자 이상 10자 이하로 입력해 주세요.")
    private String major;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().getKey()));
    }

    private User(String id, String password, Role role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public static User createUserDetails(User user) {
        return new User(user.getId(), user.getPassword(), user.getRole());
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
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
