package com.petclinic.resource.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponse(
        UUID id,
        LocalDateTime dateTime
) {
}
