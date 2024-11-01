package com.example.flightprices.booky.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "the username can not be null")
    @Size(max = 50, message = "max length 50 char must be less 50")
    @Column(columnDefinition = "Varchar(50) not null")
    private String username;

    @NotEmpty(message = "Email is requirement")
    @Size(max = 80, message = "max length must be less 80")
    @Email(message = "enter valid email like XXX@XX.XX")
    @Column(columnDefinition = "Varchar(50) not null unique")
    private String email;

    @NotEmpty(message = "password is requirement")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Contains at least eight characters.\n" +
            "Includes at least one uppercase letter.\n" +
            "Includes at least one lowercase letter.\n" +
            "Includes at least one numerical digit.")
    @Column(columnDefinition = "Varchar(100) not null")
    private String password;

    @Pattern(regexp = "ADMIN|USER")
    private String role;

    //A user can add multiple books to the system
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Book> books;

    //A user can create multiple reading lists
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<ReadingList> readingList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
}
