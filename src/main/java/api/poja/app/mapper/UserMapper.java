package api.poja.app.mapper;

import api.poja.app.dto.request.CreateUserRequest;
import api.poja.app.dto.response.UserResponse;
import api.poja.app.model.User;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User toEntity(CreateUserRequest request) {
    User user = new User();

    user.setFirstName(request.firstName());
    user.setLastName(request.lastName());
    user.setUserName(request.userName());
    user.setEmail(request.email());
    user.setCourses(new ArrayList<>());

    return user;
  }

  public UserResponse toResponse(User user) {
    return new UserResponse(
        user.getId(), user.getFirstName(), user.getLastName(), user.getUserName());
  }
}
