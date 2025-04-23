package com.poliveira.javaverse.models;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskVO {

  private UUID id;
  private String name;
  private String description;
  private Status status;
  private long createdAt;
  private long updatedAt;
}
