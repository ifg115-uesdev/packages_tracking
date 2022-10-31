package ues.edu.sv.packages_tracking.security.service;

import java.util.Collection;
import java.util.logging.Level;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ues.edu.sv.packages_tracking.entities.Rol;
import ues.edu.sv.packages_tracking.entities.Users;
import ues.edu.sv.packages_tracking.service.UsuarioService;

@Service
public class UserDetailService implements UserDetailsService{

    @Autowired
    UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = new Users();
        try {
            user = service.getByUsername(username);
            if (user!=null) {
                System.out.println(user.getUsername());
                System.out.println(user.getPassword());
                return new User(user.getUsername(), user.getPassword(), mappingAuthorities(user.getRoles()));
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al cargar usuario por username", e);
        }
        throw new UsernameNotFoundException("Username o password incorrectos");
    }

    private Collection<? extends GrantedAuthority> mappingAuthorities(Collection<Rol> roles) {
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
    }
    
}
