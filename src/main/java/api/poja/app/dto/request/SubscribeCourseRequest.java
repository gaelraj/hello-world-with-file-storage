package api.poja.app.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record SubscribeCourseRequest(@NotNull(message = "The user id is mandatory") UUID userId) {}
