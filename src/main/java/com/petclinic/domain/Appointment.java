package com.petclinic.domain;

import com.petclinic.domain.enums.AnimalType;
import com.petclinic.resource.dto.AppointmentRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "appointments")
public class Appointment {
  @Id
  private UUID id;
  private AnimalType type;
  private String doctor;
  private String guardian;
  private LocalDateTime dateTime;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public AnimalType getType() {
    return type;
  }

  public void setType(AnimalType type) {
    this.type = type;
  }

  public String getDoctor() {
    return doctor;
  }

  public void setDoctor(String doctor) {
    this.doctor = doctor;
  }

  public String getGuardian() {
    return guardian;
  }

  public void setGuardian(String guardian) {
    this.guardian = guardian;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public Appointment(UUID id, AppointmentRequest appointmentRequest) {
    this.id = id;
    this.type = appointmentRequest.type();
    this.doctor = appointmentRequest.doctor();
    this.guardian = appointmentRequest.guardian();
    this.dateTime = appointmentRequest.dateTime();
  }

  public Appointment(UUID id, AnimalType type, String doctor, String guardian, LocalDateTime dateTime) {
    this.id = id;
    this.type = type;
    this.doctor = doctor;
    this.guardian = guardian;
    this.dateTime = dateTime;
  }

  public Appointment() {
  }
}
