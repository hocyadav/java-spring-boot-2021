package com.hari.springsecurity.resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HariomYadav
 * @since 26/01/21
 */
@RestController
public class HomeResource {
    @GetMapping("/")
    public String home() {
        extracted();
        return "<h1> welcome </h1>";
    }

    @GetMapping("/user")
    public String user() {
        extracted();
        return "<h1> welcome user</h1>";
    }

    @GetMapping("/admin")
    public String admin() {
        extracted();
        return "<h1> welcome admin</h1>";
    }

    private void extracted() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("auth.getPrincipal() = " + auth.getPrincipal());
        System.out.println("auth.getName() = " + auth.getName());
        System.out.println("auth.getDetails() = " + auth.getDetails());
        System.out.println("auth.getAuthorities() = " + auth.getAuthorities());
        System.out.println("auth.getCredentials() = " + auth.getCredentials());
    }
}
