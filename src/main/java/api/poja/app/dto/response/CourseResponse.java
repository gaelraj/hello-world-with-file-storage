package api.poja.app.dto.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record CourseResponse(
    UUID id, String title, Instant start, Instant end, List<UserResponse> subscribers) {}
