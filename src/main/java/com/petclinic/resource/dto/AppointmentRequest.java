package com.petclinic.resource.dto;

import com.petclinic.domain.enums.AnimalType;

import java.time.LocalDateTime;

public record AppointmentRequest(
        AnimalType type,
        String doctor,
        String guardian,
        LocalDateTime dateTime
) {
}
