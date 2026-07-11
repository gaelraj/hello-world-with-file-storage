package api.poja.app.endpoint.rest.controller;

import api.poja.app.dto.request.CreateUserRequest;
import api.poja.app.dto.response.UserResponse;
import api.poja.app.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/users")
  public UserResponse create(@Valid @RequestBody CreateUserRequest request) {
    return userService.create(request);
  }
}
