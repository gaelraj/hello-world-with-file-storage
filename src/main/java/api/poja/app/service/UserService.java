package api.poja.app.service;

import api.poja.app.dto.request.CreateUserRequest;
import api.poja.app.dto.response.UserResponse;
import api.poja.app.mapper.UserMapper;
import api.poja.app.model.User;
import api.poja.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Transactional
  public UserResponse create(CreateUserRequest request) {
    User user = userMapper.toEntity(request);

    User savedUser = userRepository.save(user);

    return userMapper.toResponse(savedUser);
  }
}
