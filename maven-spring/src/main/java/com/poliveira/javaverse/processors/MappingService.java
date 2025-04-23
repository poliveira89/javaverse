package com.poliveira.javaverse.processors;

import com.poliveira.javaverse.entities.StatusEntity;
import com.poliveira.javaverse.entities.TaskEntity;
import com.poliveira.javaverse.models.Status;
import com.poliveira.javaverse.models.TaskVO;
import com.poliveira.javaverse.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MappingService {

  private final TimeUtils timeUtils;

  public Status toVO(StatusEntity statusEntity) {
    return Status.from(statusEntity.getName());
  }

  public StatusEntity toEntity(Status status) {
    return StatusEntity.builder().name(status.name()).build();
  }

  public TaskEntity toEntity(TaskVO taskVO) {
    return TaskEntity.builder()
        .id(taskVO.getId())
        .name(taskVO.getName())
        .description(taskVO.getDescription())
        .createdAt(timeUtils.toLocalDateTime(taskVO.getCreatedAt()))
        .updatedAt(timeUtils.toLocalDateTime(taskVO.getUpdatedAt()))
        .status(toEntity(taskVO.getStatus()))
        .build();
  }

  public TaskVO toVO(TaskEntity taskEntity) {
    return TaskVO.builder()
        .id(taskEntity.getId())
        .name(taskEntity.getName())
        .description(taskEntity.getDescription())
        .status(toVO(taskEntity.getStatus()))
        .createdAt(timeUtils.toEpochMilli(taskEntity.getCreatedAt()))
        .updatedAt(timeUtils.toEpochMilli(taskEntity.getUpdatedAt()))
        .build();
  }
}
