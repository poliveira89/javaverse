package com.poliveira.javaverse.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskVO {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  private String title;
  private String description;
  private Status status;
  private long createdAt;
  private long updatedAt;
}
