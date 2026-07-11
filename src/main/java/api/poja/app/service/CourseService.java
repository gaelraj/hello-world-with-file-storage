package api.poja.app.service;

import api.poja.app.dto.request.CreateCourseRequest;
import api.poja.app.dto.response.CourseResponse;
import api.poja.app.endpoint.event.EventProducer;
import api.poja.app.endpoint.event.model.SubscriptionConfirmed;
import api.poja.app.exception.ConflictException;
import api.poja.app.exception.NotFoundException;
import api.poja.app.mapper.CourseMapper;
import api.poja.app.model.Course;
import api.poja.app.repository.CourseRepository;
import api.poja.app.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CourseService {
  private final CourseRepository courseRepository;
  private final UserRepository userRepository;
  private final CourseMapper courseMapper;
  private final EventProducer<SubscriptionConfirmed> eventProducer;

  @Transactional
  public CourseResponse create(CreateCourseRequest request) {
    Course course = courseMapper.toEntity(request);

    Course savedCourse = courseRepository.save(course);

    return courseMapper.toResponse(savedCourse);
  }

  @Transactional
  public CourseResponse subscribe(UUID courseId, UUID userId) {
    var course =
        courseRepository
            .findById(courseId)
            .orElseThrow(() -> new NotFoundException("Course not found"));

    var user =
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

    boolean alreadySubscribed =
        course.getSubscribers().stream().anyMatch(subscriber -> subscriber.getId().equals(userId));

    if (alreadySubscribed) {
      throw new ConflictException("User already subscribed to this course");
    }

    course.getSubscribers().add(user);

    Course savedCourse = courseRepository.save(course);

    var event =
        SubscriptionConfirmed.builder()
            .studentEmail(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .courseTitle(course.getTitle())
            .build();
    eventProducer.accept(List.of(event));

    return courseMapper.toResponse(savedCourse);
  }
}
