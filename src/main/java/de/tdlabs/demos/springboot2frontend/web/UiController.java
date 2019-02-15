package de.tdlabs.demos.springboot2frontend.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import de.tdlabs.demos.springboot2frontend.config.KeycloakLinkGenerator;
import de.tdlabs.demos.springboot2frontend.todo.Todo;
import de.tdlabs.demos.springboot2frontend.todo.TodoClient;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class UiController {

    private final TodoClient todoClient;

    private final KeycloakLinkGenerator keycloakLinkGenerator;

    UiController(TodoClient todoClient, KeycloakLinkGenerator keycloakLinkGenerator) {
        this.todoClient = todoClient;
        this.keycloakLinkGenerator = keycloakLinkGenerator;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/todos";
    }

    @GetMapping("/account")
    public String account() {

        String todoUri = linkTo(getClass()).toUriComponentsBuilder().path("/todos").toUriString();
        return "redirect:" + keycloakLinkGenerator.createAccountLinkWithBacklink(todoUri);
    }

    @GetMapping("/todos*")
    public String todos(Model model, @AuthenticationPrincipal Principal currentUser) {

        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo(1L, "Angular", true));
        todos.add(new Todo(2L, "React", false));
        todos.add(new Todo(3L, "Vue", true));
        model.addAttribute("todos", todos);

        KeycloakAuthenticationToken keycloakUser = (KeycloakAuthenticationToken) currentUser;
        System.out.printf("Current user roles: %s%n", keycloakUser.getAuthorities());

        return "todos";
    }
}