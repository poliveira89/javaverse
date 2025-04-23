package com.poliveira.javaverse.processors;

import com.poliveira.javaverse.entities.TaskEntity;
import com.poliveira.javaverse.models.TaskVO;
import com.poliveira.javaverse.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MappingService {

  private final TimeUtils timeUtils;

  public TaskEntity toEntity(TaskVO taskVO) {
    return TaskEntity.builder()
        .id(taskVO.getId())
        .title(taskVO.getTitle())
        .description(taskVO.getDescription())
        .createdAt(timeUtils.toLocalDateTime(taskVO.getCreatedAt()))
        .updatedAt(timeUtils.toLocalDateTime(taskVO.getUpdatedAt()))
        .status(taskVO.getStatus())
        .build();
  }

  public TaskVO toVO(TaskEntity taskEntity) {
    return TaskVO.builder()
        .id(taskEntity.getId())
        .title(taskEntity.getTitle())
        .description(taskEntity.getDescription())
        .status(taskEntity.getStatus())
        .createdAt(timeUtils.toEpochMilli(taskEntity.getCreatedAt()))
        .updatedAt(timeUtils.toEpochMilli(taskEntity.getUpdatedAt()))
        .build();
  }
}
