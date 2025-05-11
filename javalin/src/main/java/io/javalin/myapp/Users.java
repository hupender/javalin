package io.javalin.myapp;

import kotlin.Pair;

import java.util.*;

public class Users {
    private String username;
    private String password;
    private Role role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<Roles> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Roles> roles) {
//        this.roles = roles;
//    }


    public Users(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static Map<Pair, Role> create_dummy_data() {
//        return Arrays.asList(
//                new Users("Mohan", "123", Role.USER),
//                new Users("Ram", "123", Role.ADMIN),
//                new Users("Bhism", "123", Role.ADMIN_READER),
//                new Users("Baldev", "123", Role.MANAGER)
//        );
//        Map<Pair, String>
        return Map.of(
            new Pair("user", "123"), Role.USER,
            new Pair("admin", "123"), Role.ADMIN
        );
    }
}
