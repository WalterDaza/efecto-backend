package com.efecto.bar_efecto_backend.model;

import com.efecto.bar_efecto_backend.utils.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "\"user\"")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING) //Guarde en la db el nombre del rol
    private Role role;

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = role.getPermissions().stream() // obtiene la lista de permisos asociados al rol del usuario.
                //permissionEnum representa cada permiso en la lista.                //permissionEnum.name() obtiene el nombre del permiso como una cadena de texto.
                .map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.name())) // devuelve [READ_ALL_PRODUCTS, SAVE_ONE_PRODUCT] para el admin
                .collect(Collectors.toList());  //Se recolectan los permisos transformados en una lista de GrantedAuthority

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name())); //Aquí se añade el rol del usuario, "ROLE_ADMINISTRATOR"

        return authorities; //new SimpleGrantedAuthority("SAVE_ONE_PRODUCT"), new SimpleGrantedAuthority("READ_ALL_PRODUCTS"), new SimpleGrantedAuthority("ROLE_ADMINISTRATOR")
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
