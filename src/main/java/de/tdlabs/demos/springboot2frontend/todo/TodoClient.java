package de.tdlabs.demos.springboot2frontend.todo;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

public interface TodoClient {
    Resources<Resource<Todo>> fetchTodos();
}