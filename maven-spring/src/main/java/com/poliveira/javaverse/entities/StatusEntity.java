package com.poliveira.javaverse.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "status", schema = "public")
public class StatusEntity {
  @Id
  @Column(columnDefinition = "UUID")
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(
      name = "created_at",
      nullable = false,
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;
}
