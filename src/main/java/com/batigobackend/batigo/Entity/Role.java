package com.batigobackend.batigo.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  Permission.ADMIN_READ,
                  Permission.ADMIN_UPDATE,
                  Permission.ADMIN_DELETE,
                  Permission.ADMIN_CREATE,
                  Permission.EMPLOYEUR_READ,
                  Permission.EMPLOYEUR_UPDATE,
                  Permission.EMPLOYEUR_DELETE,
                  Permission.EMPLOYEUR_CREATE
          )
  ),
  EMPLOYER(
          Set.of(
                  Permission.EMPLOYEUR_READ,
                  Permission.EMPLOYEUR_UPDATE,
                  Permission.EMPLOYEUR_DELETE,
                  Permission.EMPLOYEUR_CREATE
          )
  )

  ;

  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
