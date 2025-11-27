package com.fiscos.atlas.core.domain.model.aggregate;

import com.fiscos.atlas.core.domain.model.vo.Email;
import com.fiscos.atlas.core.domain.model.vo.Role;
import com.github.f4b6a3.uuid.UuidCreator;

import java.util.UUID;

public class User {
    private final UUID id;
    private String name;
    private Email email;
    private String password;
    private Role role;

    private User(UUID id, String name, Email email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static User create(String name, String emailStr, String password) {
        return new User(UuidCreator.getTimeBased(), name, new Email(emailStr), password, Role.CUSTOMER);
    }

    public static User createAdmin(String name, String emailStr, String password) {
        return new User(UuidCreator.getTimeBased(), name, new Email(emailStr), password, Role.ADMIN);
    }

    public static User restore(UUID id, String name, String emailStr, String password, Role role) {
        return new User(id, name, new Email(emailStr), password, role);
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email.value(); }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
}