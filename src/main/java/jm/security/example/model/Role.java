package jm.security.example.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    private Long id;
    @Getter
    @Setter
    private String role;
    @Getter
    @Setter
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(){
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }

}
