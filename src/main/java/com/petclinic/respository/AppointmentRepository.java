package com.petclinic.respository;

import com.petclinic.domain.Appointment;
import com.petclinic.resource.dto.AppointmentResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends MongoRepository<Appointment, UUID> {
  List<AppointmentResponse> findByDateTimeBefore(LocalDateTime dateTimeBefore);
}
