package io.javalin.myapp;

import io.javalin.security.RouteRole;

public enum Role implements RouteRole {
    ALL,
    USER,
    ADMIN;
}
