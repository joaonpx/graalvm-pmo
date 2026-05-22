package com.petclinic.resource;

import com.petclinic.domain.Appointment;
import com.petclinic.resource.dto.AppointmentRequest;
import com.petclinic.resource.dto.AppointmentResponse;
import com.petclinic.respository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/appointments")
public class AppointmentResource {
  private static final Logger log = LoggerFactory.getLogger(AppointmentResource.class);
  private final AppointmentRepository appointmentRepository;

  public AppointmentResource(AppointmentRepository appointmentRepository) {
    this.appointmentRepository = appointmentRepository;
  }

  @PostMapping
  public ResponseEntity<UUID> save(@RequestBody AppointmentRequest appointmentRequest) {
    Appointment savedAppointment;

    try {
      savedAppointment = appointmentRepository.save(new Appointment(UUID.randomUUID(), appointmentRequest));
      log.info("c=AppointmentResource m=save msg=Success to save appointment");
    } catch (RuntimeException e) {
      log.error("c=AppointmentResource m=save msg=Failed to save appointment e={}", e.getMessage());
      return ResponseEntity.internalServerError().build();
    }

    return ResponseEntity.ok().body(savedAppointment.getId());
  }

  @GetMapping
  public ResponseEntity<List<AppointmentResponse>> getAppointments() {
    List<AppointmentResponse> appointmentsList = appointmentRepository.findByDateTimeBefore(
            LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime().plusDays(1)
    );

    if (appointmentsList.isEmpty()) {
      log.info("c=AppointmentResource m=getAppointments msg=No appointments found in the last 7 days");
    }

    log.info("c=AppointmentResource m=getAppointments msg=Found {} appointments", appointmentsList.size());

    return ResponseEntity.ok().body(appointmentsList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AppointmentRequest> getAppointment(@PathVariable UUID id) {
    Optional<Appointment> appointment = appointmentRepository.findById(id);

    return appointment.map(
            appointmentRequest -> ResponseEntity.ok().body(
                    new AppointmentRequest(
                            appointmentRequest.getType(),
                            appointmentRequest.getDoctor(),
                            appointmentRequest.getGuardian(),
                            appointmentRequest.getDateTime()
                    )
            )
    ).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
