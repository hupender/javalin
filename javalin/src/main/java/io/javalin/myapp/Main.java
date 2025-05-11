package io.javalin.myapp;

import io.javalin.Javalin;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.servlet.JavalinServletContext;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.bundled.RouteOverviewPlugin;
import io.javalin.security.BasicAuthCredentials;
import kotlin.Pair;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        var app = Javalin
            .create(config -> {
                config.staticFiles.add(staticFileConfig -> {
                    staticFileConfig.hostedPath = "/";
                    staticFileConfig.directory = "/public";
                    staticFileConfig.location = Location.CLASSPATH;
                    staticFileConfig.fileRoles = Set.of(Role.USER);
                });

                config.staticFiles.add(staticFileConfig -> {
                    staticFileConfig.hostedPath = "/";
                    staticFileConfig.directory = "/private";
                    staticFileConfig.location = Location.CLASSPATH;
                    staticFileConfig.fileRoles = Set.of(Role.ADMIN);
                });
            })
            .get("/", ctx -> ctx.result("Welcome to my page"), Role.USER)
            .get("/pfile", ctx -> ctx.result("Welcome to my page"), Role.ALL)
            .start();
        Map<Pair, Role> users = Users.create_dummy_data();
        app.beforeMatched("*", ctx -> {
            System.out.println(ctx.routeRoles());
            var allowed_roles = ctx.routeRoles();
            if(allowed_roles.contains(Role.ALL)) {
                return;
            }
            BasicAuthCredentials credentials = ctx.basicAuthCredentials();
            if(credentials == null) {
                throw new UnauthorizedResponse("Authentication credentials were not provided.");
            }
            Pair p = new Pair(credentials.getUsername(), credentials.getPassword());
            Role user_role =  users.get(p);
            if(user_role == null) {
                throw new UnauthorizedResponse("Either username or password is incorrect.");
            }
            if(allowed_roles.contains(user_role)) {
                return;
            } else {
                throw new UnauthorizedResponse("You don't have access to perform this operation.");
            }
        });
    }
}
