package fr.tobby.socrud.entity;

import fr.tobby.socrud.model.request.CreateAccountRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Getter
@NoArgsConstructor
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String login;

    @Setter
    private String password;

    public AdminEntity(String login, String password) {
        this.id = null;
        this.login = login;
        this.password = password;
    }

    public static AdminEntity of(String login, String password) {
        AdminEntity admin = new AdminEntity();
        admin.setLogin(login);
        admin.setPassword(password);

        return admin;
    }
}
