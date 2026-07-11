package api.poja.app.endpoint.rest.controller;

import api.poja.app.dto.request.CreateCourseRequest;
import api.poja.app.dto.request.SubscribeCourseRequest;
import api.poja.app.dto.response.CourseResponse;
import api.poja.app.service.CourseService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CourseController {

  private final CourseService courseService;

  @PostMapping("/courses")
  public CourseResponse create(@Valid @RequestBody CreateCourseRequest request) {
    return courseService.create(request);
  }

  @PostMapping("/courses/{id}/subscribe")
  public CourseResponse subscribe(
      @PathVariable UUID id, @Valid @RequestBody SubscribeCourseRequest request) {
    return courseService.subscribe(id, request.userId());
  }
}
